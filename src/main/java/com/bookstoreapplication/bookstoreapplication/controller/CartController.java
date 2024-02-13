package com.bookstoreapplication.bookstoreapplication.controller;

import com.bookstoreapplication.bookstoreapplication.service.CartService;
import com.bookstoreapplication.bookstoreapplication.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cartItems")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping
    public Cart addItemToCart(Cart cart)
    {
        return cartService.addItemToCart(cart);
    }

    @GetMapping
    public Cart getCartById(@PathVariable("cartItemId") long cartItemId) {
        Cart cart = cartService.getCartById(cartItemId);
        return cart;
    }
    @PutMapping("/{cartItemId}")
    public Cart updateCartItem(@PathVariable long cartItemId, @RequestBody Cart cart) {
        return cartService.updateCartItem(cartItemId, cart);
    }
    @DeleteMapping
    public long removeItemFromCart(@PathVariable("cartItemId") long cartItemId) {
        return cartService.removeItemFromCart(cartItemId);
    }
}



