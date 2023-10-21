package com.anhbui.demosecurity.service.impl;

import com.anhbui.demosecurity.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> productList;

    public ProductService() {
        // Khởi tạo danh sách các sản phẩm mẫu (mock data)
        productList = new ArrayList<>();
        productList.add(new Product("p1", "Product 1", 10.0));
        productList.add(new Product("p2", "Product 2", 20.0));
        productList.add(new Product("p3", "Product 3", 30.0));
        // Thêm các sản phẩm khác tùy ý vào đây
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getProductById(String id) {
        return productList.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
    }
}
