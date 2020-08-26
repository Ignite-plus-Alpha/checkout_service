package com.tgt.igniteplus.checkoutservice.service;

import com.tgt.igniteplus.checkoutservice.dao.OrderDAO;
import com.tgt.igniteplus.checkoutservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private static OrderDAO orderDAO;

    @Autowired
    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public static List<Order> getAll(){
        return orderDAO.findAll();
    }

    public Order createOrder(Order order){
        return orderDAO.save(order);
    }

    public Order getByOrderId(String orderId) {
        Optional<Order> order=orderDAO.findById(orderId);
        if(!order.isPresent()) {
            return null;
        }
        return order.get();
    }

    public Order updateStatusByOrderId(String orderId,String status){
        List<Order> orders=getAll();
        Order updatedOrder=null;
        for(Order order:orders) {
            if (order.getOrderId().equals(orderId)) {
                order.setOrderStatus(status);
                orderDAO.save(order);
                updatedOrder=order;
                break;
            }
        }
        return updatedOrder;
    }
}
