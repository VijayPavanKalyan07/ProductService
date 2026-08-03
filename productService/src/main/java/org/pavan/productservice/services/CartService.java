package org.pavan.productservice.services;

import org.pavan.productservice.dtos.CartDto;

import java.util.List;

public interface CartService {
    List<CartDto> getAllCarts();

    CartDto getCartById(long cartId);

    List<CartDto> getCartsByUserId(long userId);

    CartDto createCart(CartDto cartDto);

    CartDto updateCart(long cartId, CartDto cartDto);

    void deleteCart(long cartId);
}
