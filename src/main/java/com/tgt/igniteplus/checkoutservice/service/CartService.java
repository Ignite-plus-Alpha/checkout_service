package com.tgt.igniteplus.checkoutservice.service;

import com.tgt.igniteplus.checkoutservice.dao.CartDAO;
import com.tgt.igniteplus.checkoutservice.exception.CartNotFoundException;
import com.tgt.igniteplus.checkoutservice.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    private CartDAO cartDAO;

    @Autowired
    public CartService(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    public List<Cart> getAll(){
        return cartDAO.findAll();
    }

    public Cart getByUserId(String userId) throws CartNotFoundException {
        Cart cart = cartDAO.findById(userId).get();
        if(cart==null) {
            throw new CartNotFoundException("Cart not found for the user");
        }
        return cart;
    }

    public String createCartId(String userId) throws CartNotFoundException {
        Cart cart = new Cart();
        Boolean hasCart = hasCart(userId);
        if(!hasCart) {
            cart = createCart(cart);
            return cart.getCartId();
        }
        return getCartIdByUserId(userId);
    }


    public Cart createCart(Cart cart){
        return cartDAO.save(cart);
    }


    public Boolean hasCart(String userId){
        Optional<Cart> cart=cartDAO.findById(userId);
        if(!cart.isPresent()) {
            return false;
        }
        return true;
    }


    public String getCartIdByUserId(String userId) throws CartNotFoundException {
        Cart cart=getByUserId(userId);
        if(cart==null)
            throw new CartNotFoundException("Cart not found for the user");
        return cart.getCartId();
    }


    public List<String> getOrderIdsByUserId(String userId) throws CartNotFoundException {
        Cart cart=getByUserId(userId);
        if(cart==null)
            throw new CartNotFoundException("Cart not found for the user");
        return cart.getOrderIds();
    }

    public String updateOrderIdByUserId(String userId,String orderId) throws CartNotFoundException {
        Cart cart=cartDAO.findById(userId).get();
        if(cart==null) {
            throw new CartNotFoundException("Cart not found for the user");
        }
        List<String> orderIds=cart.getOrderIds();
        if(orderIds==null)
            orderIds=new ArrayList<String>();
        orderIds.add(orderId);
        cart.setOrderIds(orderIds);
        cartDAO.save(cart);
        return "OrderId:"+orderId+"added to user"+userId;
    }
}