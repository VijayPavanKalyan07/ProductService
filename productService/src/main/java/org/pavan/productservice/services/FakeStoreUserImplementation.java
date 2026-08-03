package org.pavan.productservice.services;

import org.pavan.productservice.dtos.UserDto;
import org.pavan.productservice.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreUserImplementation implements UserService {

    private final RestClient restClient;

    public FakeStoreUserImplementation(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<UserDto> getAllUsers() {
        UserDto[] users = restClient.get()
                .uri("/users")
                .retrieve()
                .body(UserDto[].class);
        return Arrays.asList(users);
    }

    @Override
    public UserDto getUserById(long userId) {
        try {
            return restClient.get()
                    .uri("/users/{userId}", userId)
                    .retrieve()
                    .body(UserDto.class);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) {
                throw new ResourceNotFoundException("User", userId);
            }
            throw ex;
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return restClient.post()
                .uri("/users")
                .body(userDto)
                .retrieve()
                .body(UserDto.class);
    }

    @Override
    public UserDto updateUser(long userId, UserDto userDto) {
        try {
            return restClient.put()
                    .uri("/users/{userId}", userId)
                    .body(userDto)
                    .retrieve()
                    .body(UserDto.class);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) {
                throw new ResourceNotFoundException("User", userId);
            }
            throw ex;
        }
    }

    @Override
    public void deleteUser(long userId) {
        try {
            restClient.delete()
                    .uri("/users/{userId}", userId)
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) {
                throw new ResourceNotFoundException("User", userId);
            }
            throw ex;
        }
    }
}
