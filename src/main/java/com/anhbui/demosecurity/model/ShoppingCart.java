package com.anhbui.demosecurity.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class ShoppingCart {
    private final List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem item) {
        CartItem existingItem = findCartItemByProductId(item.getProductId());
        if (existingItem != null) {
            // Nếu sản phẩm đã tồn tại trong giỏ hàng, chỉ tăng số lượng
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới một mục
            items.add(item);
        }
    }

    private CartItem findCartItemByProductId(String productId) {
        return items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }


    public void removeItem(String productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }

}
