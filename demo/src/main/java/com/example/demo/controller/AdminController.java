package com.example.demo.controller;


import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin";
    }

    @PostMapping("/admin/add")
    public String addProduct(@RequestParam String name,
                             @RequestParam double price,
                             @RequestParam(required = false) String description) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        productRepository.save(product);
        return "redirect:/admin";
    }


    @PostMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin";
    }
}
