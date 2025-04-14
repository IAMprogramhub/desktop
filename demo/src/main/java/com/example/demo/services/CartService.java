package com.example.demo.services;


import com.example.demo.models.CartItem;
import com.example.demo.models.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    private Map<Long, CartItem> items = new HashMap<>();

    public void addToCart(Product product) {
        if (items.containsKey(product.getId())) {
            items.get(product.getId()).incrementQuantity();
        } else {
            items.put(product.getId(), new CartItem(product));
        }
    }

    public Collection<CartItem> getCartItems() {
        return items.values();
    }

    public double getTotalPrice() {
        return items.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
    public void removeFromCart(Long productId) {
        items.remove(productId);
    }


    public void clearCart() {
        items.clear();
    }
}
