package com.tgt.igniteplus.checkoutservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    List<CartItem> cartItems;
    float total_price=0;
    int total_quantity=0;
}
