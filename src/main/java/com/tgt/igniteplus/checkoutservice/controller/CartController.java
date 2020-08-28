package com.tgt.igniteplus.checkoutservice.controller;

import com.tgt.igniteplus.checkoutservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart/{userId}")
    public String getCartIdByUserId( @PathVariable(name="userId", required =true ) String userId){
        return cartService.getCartIdByUserId(userId);
    }

    @GetMapping("/cart/orders/{userId}")
    public List<String> getOrderIdsByUserId(@PathVariable(name="userId", required = true) String userId){
        return cartService.getOrderIdsByUserId(userId);
    }

    @PutMapping("/cart/{userId}/{orderId}")
    public String updateOrderIdByUserId(@PathVariable("userId") String userId,@PathVariable("orderId") String orderId){
        return cartService.updateOrderIdByUserId(userId, orderId);
    }

    @PostMapping("/cart/{userId}")
    public String createCartId(@PathVariable String userId){
        return cartService.createCartId(userId);
    }
}