package com.tgt.igniteplus.checkoutservice.controller;

import com.tgt.igniteplus.checkoutservice.exception.CartNotFoundException;
import com.tgt.igniteplus.checkoutservice.exception.OrderNotFoundException;
import com.tgt.igniteplus.checkoutservice.model.Order;
import com.tgt.igniteplus.checkoutservice.service.CartService;
import com.tgt.igniteplus.checkoutservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @GetMapping("/order/{orderId}")
    public Order getByOrderId(@PathVariable("orderId") String orderId){
        try {
            return orderService.getByOrderId(orderId);
        }catch(OrderNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @PutMapping("/order/{orderId}/{status}")
    public Order updateStatusByOrderId(@PathVariable("orderId") String orderId,@PathVariable("status") String status){
        return orderService.updateStatusByOrderId(orderId,status);
    }

    @GetMapping("/orders/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable("userId") String userId){
        try {
            List<String> orderIds = cartService.getOrderIdsByUserId(userId);
            List<Order> orders = new ArrayList<>();
            for (String orderId : orderIds) {
                orders.add(orderService.getByOrderId(orderId));
            }
            return orders;
        }catch (CartNotFoundException | OrderNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @PostMapping("/order")
    public Order createOrderId(@RequestBody Order order){
        return orderService.createOrder(order);
    }
}