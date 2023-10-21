package com.anhbui.demosecurity.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    // Constructors, getters, setters, and other methods
}
