package com.tgt.igniteplus.checkoutservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgt.igniteplus.checkoutservice.model.Cart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class CartControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CartController cartController ;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    public void getAll() throws Exception {
        Cart cart= new Cart();
        cart.setUserId("8922b8a0-cf4c-43fa-abca-fd55c78e7d10");
        cart.setCartId("8bffcb0d-e44e-4331-9911-7f3a5be08f0a");
        cart.setOrderIds(null);

        List<Cart> allCart=singletonList(cart);
        given(cartController.getAll()).willReturn(allCart);
        mockMvc.perform(get("/cart")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getCartIdByUserId() throws Exception{
        Cart cart=new Cart();
        cart.setUserId("8922b8a0-cf4c-43fa-abca-fd55c78e7d10");
        cart.setCartId("8bffcb0d-e44e-4331-9911-7f3a5be08f0a");
        cart.setOrderIds(null);
        given(cartController.getCartIdByUserId(cart.getUserId())).willReturn(cart.getCartId());
        mockMvc.perform(get("/cart/8922b8a0-cf4c-43fa-abca-fd55c78e7d10")
        .contentType(APPLICATION_JSON))
         .andExpect(status().isOk());
    }

    @Test
    public void getOrderIdsByUserId() throws Exception {
        Cart cart=new Cart();
        String orderId= new String();
        cart.setUserId("8922b8a0-cf4c-43fa-abca-fd55c78e7d10");
        List<String> allOrderId=singletonList(orderId);
        given(cartController.getOrderIdsByUserId(cart.getUserId())).willReturn(allOrderId);
        mockMvc.perform(get("/cart/orders/8922b8a0-cf4c-43fa-abca-fd55c78e7d10")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateOrderIdByUserId() throws Exception{
        Cart cart=new Cart();
        cart.setUserId("8922b8a0-cf4c-43fa-abca-fd55c78e7d10");
        cart.setCartId("8bffcb1d-e44e-4331-9911-7f3a5be08f0a");
        cart.setOrderIds(null);
        given(cartController.updateOrderIdByUserId(cart.getUserId(),cart.getCartId())).willReturn(cart.getCartId());
        mockMvc.perform(put("/cart/8922b8a0-cf4c-43fa-abca-fd55c78e7d10/8bffcb0d-e44e-4331-9911-7f3a5be08f0a")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void createCartId() throws Exception {
        Cart cart=new Cart();
        cart.setUserId("8922b8a0-cf4c-43fa-abca-fd55c78e7d10");
        String message=new String();
        given(cartController.createCartId(cart.getUserId())).willReturn(message);

        mockMvc.perform(post("/cart/8922b8a0-cf4c-43fa-abca-fd55c78e7d10")
                .content(asJsonString(message))
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
}