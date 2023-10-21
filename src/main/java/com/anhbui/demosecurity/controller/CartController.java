package com.anhbui.demosecurity.controller;

import com.anhbui.demosecurity.model.CartItem;
import com.anhbui.demosecurity.model.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {
    private final ShoppingCart shoppingCart;

    public CartController(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        List<CartItem> cartItems = shoppingCart.getItems();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", shoppingCart.getTotalPrice());
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@ModelAttribute("item") CartItem item) {
        shoppingCart.addItem(item);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam("productId") String productId) {
        shoppingCart.removeItem(productId);
        return "redirect:/cart";
    }
}
