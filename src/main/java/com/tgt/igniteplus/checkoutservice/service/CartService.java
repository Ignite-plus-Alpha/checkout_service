package com.tgt.igniteplus.checkoutservice.service;

import com.tgt.igniteplus.checkoutservice.dao.CartDAO;
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

    //getAllCartIds
    public List<Cart> getAll(){
        return cartDAO.findAll();
    }

    //get row of cart
    public Cart getByUserId(String userId){
        Optional<Cart> cart = cartDAO.findById(userId);
        Cart rowCart=cart.get();
        if(!cart.isPresent()) {
            return null;
        }
        return rowCart;
    }

    //check if cartId is present for an user,
    //if the user does not have cartId create
    public String createCartId(String userId){
        Cart cart = new Cart();
        cart.setUserId(userId);
        Boolean hasCart = hasCart(userId);
        if(!hasCart) {
            cart = createCart(cart);
            return cart.getCartId();
        }
        cart.setUserId(userId);
        cart.setCartId(getCartIdByUserId(userId));
        cart.setOrderIds(getOrderIdsByUserId(userId));
        return cart.getCartId();
    }

    //createCart
    public Cart createCart(Cart cart){
        return cartDAO.save(cart);
    }

    //check if an user has cartId
    //return true if user has else false
    public Boolean hasCart(String userId){
        Optional<Cart> cart=cartDAO.findById(userId);
        if(!cart.isPresent()) {
            return false;
        }
        return true;
    }

    //getCartIdByUserId
    public String getCartIdByUserId(String userId) {
        Cart cart=getByUserId(userId);
        if(cart==null)
            return null;
        return cart.getCartId();
    }

    //get orderIds of an user
    public List<String> getOrderIdsByUserId(String userId) {
        Cart cart=getByUserId(userId);
        if(cart==null)
            return null;
        return cart.getOrderIds();
    }

    //to update orderIds of an user
    public String updateOrderIdByUserId(String userId,String orderId){
        Optional<Cart> cart=cartDAO.findById(userId);
        if(!cart.isPresent()) {
            return "UserId does not exist.";
        }
        Cart cart1;
        cart1=cart.get();
        List<String> orderIds=cart1.getOrderIds();
        if(orderIds==null)
            orderIds=new ArrayList<String>();
        orderIds.add(orderId);
        cart1.setOrderIds(orderIds);
        cartDAO.save(cart1);
        return "OrderId:"+orderId+"added to user"+userId;
    }
}