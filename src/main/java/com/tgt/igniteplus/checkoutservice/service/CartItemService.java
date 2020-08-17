package com.tgt.igniteplus.checkoutservice.service;

import com.tgt.igniteplus.checkoutservice.dao.CartItemDAO;
import com.tgt.igniteplus.checkoutservice.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService {

    private static CartItemDAO cartItemDAO;

    @Autowired
    public CartItemService(CartItemDAO cartItemDAO) {
        this.cartItemDAO = cartItemDAO;
    }

    //getAllItemCartItem
    public static List<CartItem> getAll(){
        return cartItemDAO.findAll();
    }

    //createCartItem
    public CartItem create(CartItem cartItem){
        return cartItemDAO.save(cartItem);
    }

    //get Item list for a cartId
    public List<CartItem> getItemsByCartId(String cartId) {
        List<CartItem>  items = cartItemDAO.findByCartId(cartId);
        return items;
    }

    //get Item of particular cartId itemId and size
    public CartItem getItemByCartIdItemIdSize(String cartId,String itemId,String itemSize){
        List<CartItem>  items = cartItemDAO.findByCartId(cartId);
        CartItem filteredItem = null;
        for (CartItem item:items) {
            if(item.getItemId().equals(itemId) && item.getItemSize().equals(itemSize))
            {
                filteredItem = item;
                break;
            }
        }
        return filteredItem;
    }

    //update quantity of an item of particular cartId of particular size
    public CartItem updateItemByCartIdItemIdSize(String cartId,String itemId,
                                                 Integer itemQuantity,String itemSize){
        List<CartItem>  items = cartItemDAO.findByCartId(cartId);
        CartItem updatedItem = null;
        for (CartItem item:items) {
            if(item.getItemId().equals(itemId) && item.getItemSize().equals(itemSize))
            {
                updatedItem = item;
                updatedItem.setItemQuantity(itemQuantity);
                cartItemDAO.save(updatedItem);
                break;
            }
        }
        return updatedItem;
    }

    //delete an item of particular size using the itemId and itemSize of particular cartId
    public CartItem deleteItemByCartIdItemIdSize(String cartId,String itemId,String itemSize){
        List<CartItem>  items = cartItemDAO.findByCartId(cartId);
        CartItem filteredItem = null;
        for (CartItem item:items) {
            if(item.getItemId().equals(itemId) && item.getItemSize().equals(itemSize))
            {
                filteredItem = item;
                cartItemDAO.delete(filteredItem);
                break;
            }
        }
        return filteredItem;
    }

    //delete all items belonging to a cartId
    public List<CartItem> deleteItemsByCartId(String cartId){
        List<CartItem> cartItems = getItemsByCartId(cartId);
        List<CartItem> deletedItems = new ArrayList<>();
        for(CartItem item:cartItems){

                deletedItems.add(item);
                cartItemDAO.delete(item);

        }
        return deletedItems;
    }
}
