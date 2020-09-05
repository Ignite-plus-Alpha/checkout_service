package com.tgt.igniteplus.checkoutservice.controller;

import com.tgt.igniteplus.checkoutservice.exception.CartNotFoundException;
import com.tgt.igniteplus.checkoutservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart/{userId}")
    public String getCartIdByUserId( @PathVariable(name="userId", required =true ) String userId){
        try{
            return cartService.getCartIdByUserId(userId);
        }catch (CartNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }

    }

    @GetMapping("/cart/orders/{userId}")
    public List<String> getOrderIdsByUserId(@PathVariable(name="userId", required = true) String userId){
        try {
            return cartService.getOrderIdsByUserId(userId);
        }catch (CartNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @PutMapping("/cart/{userId}/{orderId}")
    public String updateOrderIdByUserId(@PathVariable("userId") String userId,@PathVariable("orderId") String orderId){
        try{ return cartService.updateOrderIdByUserId(userId, orderId);}
        catch(CartNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @PostMapping("/cart/{userId}")
    public String createCartId(@PathVariable String userId){
        try {
            return cartService.createCartId(userId);
        }catch(CartNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }
}