package com.bookstoreapplication.bookstoreapplication.user.cart.cartservice;

import com.bookstoreapplication.bookstoreapplication.user.cart.models.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {


  public   List<Cart> getCartList();

public   Cart getCartById(long cartItemId);

 public Cart addItemToCart(Cart cart);

 public Cart updateCartItem(long cartItemId, Cart cart);

 public    long removeItemFromCart(long cartItemId);
}
