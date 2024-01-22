package com.bookstoreapplication.bookstoreapplication.service.serviceImpl;
import com.bookstoreapplication.bookstoreapplication.models.Cart;
import com.bookstoreapplication.bookstoreapplication.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
@Slf4j
@Service
public class CartServiceImpl implements CartService {




    @Override
    public Cart getCartById(long cartItemId) {
        return new Cart();
    }

    @Override
    public Cart addItemToCart(Cart cart) {
        log.info(cart.toString());
        return cart;
    }

    @Override
    public Cart updateCartItem(long cartItemId, Cart cart) {
        log.info(cart.toString());
        return cart;
    }

    @Override
    public long removeItemFromCart(long cartItemId) {
        return cartItemId;
    }
}
