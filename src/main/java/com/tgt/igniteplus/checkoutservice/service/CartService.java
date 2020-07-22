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

    //check if cartId is present for an user,
    //if the user does not have cartId create

    public String createCartId(Cart cart){
        String userId = cart.getUserId();
        Boolean hasCart = hasCart(userId);
        if(!hasCart) {
            cart = createCart(cart);
            return "Created cartId "+cart.getCartId()+" for the User";
        }
        return "User already has cartId";
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
    public Optional<Cart> getByUserId(String userId) {
        Optional<Cart> cart=cartDAO.findById(userId);
        if(!cart.isPresent()) {
            return null;
        }
        return cart;
    }

}
