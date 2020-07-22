package com.tgt.igniteplus.checkoutservice.controller;

import com.tgt.igniteplus.checkoutservice.model.CartItem;
import com.tgt.igniteplus.checkoutservice.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    //get all Cart Items
    @GetMapping("/cartItem")
    public List<CartItem> getAll(){
        return cartItemService.getAll();
    }

    //get an item by item id
    @GetMapping("/cartItem/{cartId}")
    public List<CartItem> getByCartId(@PathVariable("cartId") String cartId){
        return cartItemService.getItemsByCartId(cartId);
    }

    //create Group-cart
    @PostMapping("/cartItem")
    public CartItem createCartItem(@RequestBody CartItem cartItem){
        return cartItemService.create(cartItem);
    }

    //to get a particular item from cart using itemId and itemSize
    @GetMapping("/cartItem/{cartId}/{itemId}/{itemSize}")
    public CartItem getByCartIdItemIdItemSize(@PathVariable("cartId") String cartId,
                                              @PathVariable("itemId") String itemId,
                                              @PathVariable("itemSize") String itemSize){
        return cartItemService.getItemByCartIdItemIdSize(cartId,itemId,itemSize);
    }

    //to delete an item of particular quantity , itemSize with the help of itemId and cartId
    @PutMapping("/cartItem/{cartId}/{itemId}/{itemSize}/{itemQuantity}")
    public CartItem getItemByCartIdItemIdSize(@PathVariable("cartId") String cartId,
                                              @PathVariable("itemId") String itemId,
                                              @PathVariable("itemQuantity") Integer itemQuantity,
                                              @PathVariable("itemSize") String itemSize){
        return cartItemService.updateItemByCartIdItemIdSize(cartId,itemId,itemQuantity,itemSize);
    }

    //to delete an item of particular itemSize based on cartId and itemId
    @DeleteMapping ("/cartItem/{cartId}/{itemId}/{itemSize}")
    public CartItem deleteByCartIdItemIdItemSize(@PathVariable("cartId") String cartId,
                                              @PathVariable("itemId") String itemId,
                                              @PathVariable("itemSize") String itemSize) {
        return cartItemService.deleteItemByCartIdItemIdSize(cartId, itemId, itemSize);
    }
}
