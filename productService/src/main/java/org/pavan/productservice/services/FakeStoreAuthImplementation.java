package org.pavan.productservice.services;

import org.pavan.productservice.dtos.LoginRequestDto;
import org.pavan.productservice.dtos.LoginResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class FakeStoreAuthImplementation implements AuthService {

    private final RestClient restClient;

    public FakeStoreAuthImplementation(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        return restClient.post()
                .uri("/auth/login")
                .body(loginRequest)
                .retrieve()
                .body(LoginResponseDto.class);
    }
}
