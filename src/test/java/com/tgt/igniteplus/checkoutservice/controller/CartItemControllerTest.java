package com.tgt.igniteplus.checkoutservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgt.igniteplus.checkoutservice.dao.CartItemDAO;
import com.tgt.igniteplus.checkoutservice.model.CartItem;
import com.tgt.igniteplus.checkoutservice.model.CartResponse;
import com.tgt.igniteplus.checkoutservice.service.CartItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
public class CartItemControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CartItemService cartItemService;

    @Mock
    private CartItemDAO cartItemDAO;

    @Mock
    private CartItemController cartItemController ;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartItemController).build();
    }

    @Test
    public void getByCartId() throws Exception {
        Mockito.when( cartItemService.getItemsByCartId(Mockito.anyString())).thenReturn(null);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/cartItem/{cartId}","4d6b92ac-13fc-4eb4-abac-0b90af0ca64a");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createCartItem() throws Exception{
        CartItem cartItem= new CartItem();
        cartItem.setCartId("8bffcb0d-e44e-4331-9911-7f3a5be08f0a");
        cartItem.setItemId("601a393c-18b7-4a08-b2cc-064b8d4039cc");
        cartItem.setItemSize("na");
        cartItem.setItemTitle("potatoes");
        cartItem.setItemGroup("grocery");
        cartItem.setItemCategory("vegetables");
        cartItem.setItemImageURL(" https://images.unsplash.com/photo-1518977676601-b53f82aba655?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2550&q=80");
        cartItem.setItemQuantity(1);
        cartItem.setItemPrice((float)32);
        given(cartItemController.createCartItem(cartItem)).willReturn(cartItem);

        mockMvc.perform(post("/cartItem")
                .content(asJsonString(cartItem))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getByCartIdItemIdItemSize() throws Exception{
        CartItem cartItem= new CartItem();
        cartItem.setCartId("8bffcb0d-e44e-4331-9911-7f3a5be08f0a");
        cartItem.setItemId("601a393c-18b7-4a08-b2cc-064b8d4039cc");
        cartItem.setItemSize("na");
        cartItem.setItemTitle("potatoes");
        cartItem.setItemGroup("grocery");
        cartItem.setItemCategory("vegetables");
        cartItem.setItemImageURL(" https://images.unsplash.com/photo-1518977676601-b53f82aba655?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2550&q=80");
        cartItem.setItemQuantity(1);
        cartItem.setItemPrice((float)32);

        given(cartItemController.getByCartIdItemIdItemSize(cartItem.getCartId(),cartItem.getItemId(),cartItem.getItemSize())).willReturn(cartItem);
        mockMvc.perform(get("/cartItem/8bffcb0d-e44e-4331-9911-7f3a5be08f0a/601a393c-18b7-4a08-b2cc-064b8d4039cc/na")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateItemByCartIdItemIdSize() throws Exception{
        CartItem cartItem=new CartItem();
        cartItem.setCartId("8bffcb0d-e44e-4331-9911-7f3a5be08f0a");
        cartItem.setItemId("601a393c-18b7-4a08-b2cc-064b8d4039cc");
        cartItem.setItemSize("na");
        cartItem.setItemTitle("potatoes");
        cartItem.setItemGroup("grocery");
        cartItem.setItemCategory("vegetables");
        cartItem.setItemImageURL(" https://images.unsplash.com/photo-1518977676601-b53f82aba655?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2550&q=80");
        cartItem.setItemQuantity(2);
        cartItem.setItemPrice((float)32);
        given(cartItemController.updateItemByCartIdItemIdSize(cartItem.getCartId(),cartItem.getItemId(),cartItem.getItemQuantity(),cartItem.getItemSize())).willReturn(cartItem);
        mockMvc.perform(put("/cartItem/8bffcb0d-e44e-4331-9911-7f3a5be08f0a/601a393c-18b7-4a08-b2cc-064b8d4039cc/na/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteByCartIdItemIdItemSize() throws Exception{
        CartItem cartItem=new CartItem();
        cartItem.setCartId("8bffcb0d-e44e-4331-9911-7f3a5be08f0a");
        cartItem.setItemId("601a393c-18b7-4a08-b2cc-064b8d4039cc");
        cartItem.setItemSize("na");
        cartItem.setItemTitle("potatoes");
        cartItem.setItemGroup("grocery");
        cartItem.setItemCategory("vegetables");
        cartItem.setItemImageURL(" https://images.unsplash.com/photo-1518977676601-b53f82aba655?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2550&q=80");
        cartItem.setItemQuantity(1);
        cartItem.setItemPrice((float)32);

        given(cartItemController.deleteByCartIdItemIdItemSize(cartItem.getCartId(),cartItem.getItemId(),cartItem.getItemSize())).willReturn(cartItem);
        mockMvc.perform(delete("/cartItem/8bffcb0d-e44e-4331-9911-7f3a5be08f0a/601a393c-18b7-4a08-b2cc-064b8d4039cc/na")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteItemsByCartId() throws Exception{
        CartItem cartItem=new CartItem();
        cartItem.setCartId("8bffcb0d-e44e-4331-9911-7f3a5be08f0a");
        cartItem.setItemId("601a393c-18b7-4a08-b2cc-064b8d4039cc");
        cartItem.setItemSize("na");
        cartItem.setItemTitle("potatoes");
        cartItem.setItemGroup("grocery");
        cartItem.setItemCategory("vegetables");
        cartItem.setItemImageURL(" https://images.unsplash.com/photo-1518977676601-b53f82aba655?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2550&q=80");
        cartItem.setItemQuantity(1);
        cartItem.setItemPrice((float)32);

        List<CartItem> allCart=singletonList(cartItem);
        given(cartItemController.deleteItemsByCartId(cartItem.getCartId())).willReturn(allCart);
        mockMvc.perform(delete("/cartItem/8bffcb0d-e44e-4331-9911-7f3a5be08f0a")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}