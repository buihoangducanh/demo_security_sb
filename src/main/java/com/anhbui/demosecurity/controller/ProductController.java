package com.anhbui.demosecurity.controller;

import com.anhbui.demosecurity.model.CartItem;
import com.anhbui.demosecurity.model.Product;
import com.anhbui.demosecurity.model.ShoppingCart;
import com.anhbui.demosecurity.service.impl.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final ShoppingCart shoppingCart;

    public ProductController(ProductService productService, ShoppingCart shoppingCart) {
        this.productService = productService;
        this.shoppingCart = shoppingCart;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        return "productslist";
    }

    @PostMapping("/products/add-to-cart")
    public String addToCart(@RequestParam("productId") String productId, @RequestParam("quantity") int quantity) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            CartItem cartItem = new CartItem();
            cartItem.setProductId(product.getId());
            cartItem.setProductName(product.getName());
            cartItem.setPrice(product.getPrice());
            cartItem.setQuantity(quantity);
            shoppingCart.addItem(cartItem);
        }
        return "redirect:/cart";
    }

}
