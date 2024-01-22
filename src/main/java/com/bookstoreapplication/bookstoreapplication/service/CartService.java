package com.bookstoreapplication.bookstoreapplication.service;

import com.bookstoreapplication.bookstoreapplication.models.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {




public   Cart getCartById(long cartItemId);

 public Cart addItemToCart(Cart cart);

 public Cart updateCartItem(long cartItemId, Cart cart);

 public    long removeItemFromCart(long cartItemId);
}
