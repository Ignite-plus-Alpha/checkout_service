package com.tgt.igniteplus.checkoutservice.controller;

import com.tgt.igniteplus.checkoutservice.model.Cart;
import com.tgt.igniteplus.checkoutservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    //getAllCartDetails
    @GetMapping("/cart")
    public List<Cart> getAll(){
        return cartService.getAll();
    }

    //get an cartId by user id
    @GetMapping("/cart/{userId}")
    public String getCartIdByUserId(@PathVariable("userId") String userId){
        return cartService.getCartIdByUserId(userId);
    }

    //get all orderIds by user id
    @GetMapping("/cart/orders/{userId}")
    public List<String> getOrderIdsByUserId(@PathVariable("userId") String userId){
        return cartService.getOrderIdsByUserId(userId);
    }

    //update orderId of an user
    @PutMapping("/cart/{userId}/{orderId}")
    public String updateStatusByOrderId(@PathVariable("userId") String userId,@PathVariable("orderId") String orderId){
        return cartService.updateOrderIdByUserId(userId, orderId);
    }

    //create a cartId for an user if already does not exist.
    @PostMapping("/cart/{userId}")
    public String createCartId(@PathVariable String userId){
        String message =cartService.createCartId(userId);
        return message;
    }

}
