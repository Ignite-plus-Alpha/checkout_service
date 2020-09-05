package com.tgt.igniteplus.checkoutservice.controller;

import com.tgt.igniteplus.checkoutservice.exception.CartItemNotFoundException;
import com.tgt.igniteplus.checkoutservice.model.CartItem;
import com.tgt.igniteplus.checkoutservice.model.CartResponse;
import com.tgt.igniteplus.checkoutservice.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/cartItem/{cartId}")
    public ResponseEntity getByCartId(@PathVariable("cartId") String cartId){
        try {
            List<CartItem> cartItems = cartItemService.getItemsByCartId(cartId);
            CartResponse cartResponse = new CartResponse();
            for (CartItem item : cartItems) {
                cartResponse.setTotal_price(cartResponse.getTotal_price() + (item.getItemQuantity() * item.getItemPrice()));
                cartResponse.setTotal_quantity(cartResponse.getTotal_quantity() + item.getItemQuantity());
            }
            ;
            cartResponse.setCartItems(cartItems);
            return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
        }catch (CartItemNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @PostMapping("/cartItem")
    public CartItem createCartItem(@RequestBody CartItem cartItem){
        return cartItemService.create(cartItem);
    }

    @GetMapping("/cartItem/{cartId}/{itemId}/{itemSize}")
    public CartItem getByCartIdItemIdItemSize(@PathVariable("cartId") String cartId,
                                              @PathVariable("itemId") String itemId,
                                              @PathVariable("itemSize") String itemSize){
        return cartItemService.getItemByCartIdItemIdSize(cartId,itemId,itemSize);
    }

    @PutMapping("/cartItem/{cartId}/{itemId}/{itemSize}/{itemQuantity}")
    public CartItem updateItemByCartIdItemIdSize(@PathVariable("cartId") String cartId,
                                                 @PathVariable("itemId") String itemId,
                                                 @PathVariable("itemQuantity") Integer itemQuantity,
                                                 @PathVariable("itemSize") String itemSize){
        return cartItemService.updateItemByCartIdItemIdSize(cartId,itemId,itemQuantity,itemSize);
    }

    @DeleteMapping ("/cartItem/{cartId}/{itemId}/{itemSize}")
    public CartItem deleteByCartIdItemIdItemSize(@PathVariable("cartId") String cartId,
                                                 @PathVariable("itemId") String itemId,
                                                 @PathVariable("itemSize") String itemSize) {
        return cartItemService.deleteItemByCartIdItemIdSize(cartId, itemId, itemSize);
    }

    @DeleteMapping("/cartItem/{cartId}")
    public List<CartItem> deleteItemsByCartId(@PathVariable("cartId") String cartId){
        try{
            return cartItemService.deleteItemsByCartId(cartId);
        }catch(CartItemNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }
}
