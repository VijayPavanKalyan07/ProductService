package org.pavan.productservice.services;

import org.pavan.productservice.dtos.CartDto;
import org.pavan.productservice.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreCartImplementation implements CartService {

    private final RestClient restClient;

    public FakeStoreCartImplementation(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<CartDto> getAllCarts() {
        CartDto[] carts = restClient.get()
                .uri("/carts")
                .retrieve()
                .body(CartDto[].class);
        return Arrays.asList(carts);
    }

    @Override
    public CartDto getCartById(long cartId) {
        try {
            return restClient.get()
                    .uri("/carts/{cartId}", cartId)
                    .retrieve()
                    .body(CartDto.class);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) {
                throw new ResourceNotFoundException("Cart", cartId);
            }
            throw ex;
        }
    }

    @Override
    public List<CartDto> getCartsByUserId(long userId) {
        CartDto[] carts = restClient.get()
                .uri("/carts/user/{userId}", userId)
                .retrieve()
                .body(CartDto[].class);
        return Arrays.asList(carts);
    }

    @Override
    public CartDto createCart(CartDto cartDto) {
        return restClient.post()
                .uri("/carts")
                .body(cartDto)
                .retrieve()
                .body(CartDto.class);
    }

    @Override
    public CartDto updateCart(long cartId, CartDto cartDto) {
        try {
            return restClient.put()
                    .uri("/carts/{cartId}", cartId)
                    .body(cartDto)
                    .retrieve()
                    .body(CartDto.class);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) {
                throw new ResourceNotFoundException("Cart", cartId);
            }
            throw ex;
        }
    }

    @Override
    public void deleteCart(long cartId) {
        try {
            restClient.delete()
                    .uri("/carts/{cartId}", cartId)
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) {
                throw new ResourceNotFoundException("Cart", cartId);
            }
            throw ex;
        }
    }
}
