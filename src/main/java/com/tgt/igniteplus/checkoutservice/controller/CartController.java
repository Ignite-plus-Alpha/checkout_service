package com.tgt.igniteplus.checkoutservice.controller;

import com.tgt.igniteplus.checkoutservice.model.Cart;
import com.tgt.igniteplus.checkoutservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    //getAllCartItem
    @GetMapping("/cart")
    public List<Cart> getAll(){
        return cartService.getAll();
    }

    //get an item by item id
    @GetMapping("/cart/{userId}")
    public Optional<Cart> getByUserId(@PathVariable("userId") String userId){
        return cartService.getByUserId(userId);
    }

    //createGroup-cart
    @PostMapping("/cart")
    public String createCartId(@RequestBody Cart cart){
        String message =cartService.createCartId(cart);
        return message;
    }

}
