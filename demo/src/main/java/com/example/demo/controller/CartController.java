package com.example.demo.controller;


import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            cartService.addToCart(product);
        }
        return "redirect:/home";
    }

    @GetMapping("/basket")
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "basket";
    }
    @PostMapping("/remove-from-cart/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "redirect:/basket";
    }


}
