package org.pavan.productservice.controllers;

import org.pavan.productservice.dtos.CartDto;
import org.pavan.productservice.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartById(@PathVariable long cartId) {
        return ResponseEntity.ok(cartService.getCartById(cartId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartDto>> getCartsByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(cartService.getCartsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto) {
        CartDto created = cartService.createCart(cartDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<CartDto> updateCart(
            @PathVariable long cartId,
            @RequestBody CartDto cartDto) {
        return ResponseEntity.ok(cartService.updateCart(cartId, cartDto));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
