package com.tgt.igniteplus.checkoutservice.controller;

import com.tgt.igniteplus.checkoutservice.model.Order;
import com.tgt.igniteplus.checkoutservice.service.CartService;
import com.tgt.igniteplus.checkoutservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    //getAllOrderItem
    @GetMapping("/order")
    public List<Order> getAll(){
        return orderService.getAll();
    }

    //get an details by order id
    @GetMapping("/order/{orderId}")
    public Order getByOrderId(@PathVariable("orderId") String orderId){
        return orderService.getByOrderId(orderId);
    }

    //update status of an order by order id
    @PutMapping("/order/{orderId}/{status}")
    public Order updateStatusByOrderId(@PathVariable("orderId") String orderId,@PathVariable("status") String status){
        return orderService.updateStatusByOrderId(orderId,status);
    }

    //to get details of all orders of an user
    @GetMapping("/orders/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable("userId") String userId){
        List<String> orderIds = cartService.getOrderIdsByUserId(userId);
        List<Order> orders = new ArrayList<>();
        for(String orderId:orderIds){
            orders.add(orderService.getByOrderId(orderId));
        }
        return orders;
    }

    //createOrder
    @PostMapping("/order")
    public Order createOrderId(@RequestBody Order order){
        return orderService.createOrder(order);
    }

}