package com.tgt.igniteplus.checkoutservice.model;

import lombok.Data;
import java.util.List;

@Data
public class CartResponse {
    List<CartItem> cartItems;
    float total_price=0;
    int total_quantity=0;
}
