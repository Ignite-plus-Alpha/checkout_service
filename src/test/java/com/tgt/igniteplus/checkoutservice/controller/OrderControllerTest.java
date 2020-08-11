package com.tgt.igniteplus.checkoutservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgt.igniteplus.checkoutservice.dao.OrderDAO;
import com.tgt.igniteplus.checkoutservice.model.CartItem;
import com.tgt.igniteplus.checkoutservice.model.Order;
import com.tgt.igniteplus.checkoutservice.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderDAO orderDAO;

    @Mock
    private OrderController orderController ;  
    
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void getAll() throws Exception {
        Order order=new Order();
        order.setOrderId("787a393c-18b7-4a08-b2cc-064b7a3039bb");
        order.setOrderItems("potatoes");
        order.setOrderPrice((float)32);
        order.setOrderQuantity(1);
        order.setDeliveryAddress("#57,3rd cross,1st stage,Hebbal-kempapura,Bangalore");
        order.setOrderStatus("shipped");

        List<Order> allOrder=singletonList(order);
        given(orderController.getAll()).willReturn(allOrder);
        mockMvc.perform(get("/order")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getByOrderId() throws Exception {
        Order order=new Order();
        order.setOrderId("787a393c-18b7-4a08-b2cc-064b7a3039bb");
        order.setOrderItems("potatoes");
        order.setOrderPrice((float)32);
        order.setOrderQuantity(1);
        order.setDeliveryAddress("#57,3rd cross,1st stage,Hebbal-kempapura,Bangalore");
        order.setOrderStatus("shipped");

        given(orderController.getByOrderId(order.getOrderId())).willReturn(order);
        mockMvc.perform(get("/order/787a393c-18b7-4a08-b2cc-064b7a3039bb")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateStatusByOrderId() throws Exception{
        Order order=new Order();
        order.setOrderId("787a393c-18b7-4a08-b2cc-064b7a3039bb");
        order.setOrderItems("potatoes");
        order.setOrderPrice((float)32);
        order.setOrderQuantity(1);
        order.setDeliveryAddress("#57,3rd cross,1st stage,Hebbal-kempapura,Bangalore");
        order.setOrderStatus("delivered");
        given(orderController.updateStatusByOrderId(order.getOrderId(),order.getOrderStatus())).willReturn(order);
        mockMvc.perform(put("/order/787a393c-18b7-4a08-b2cc-064b7a3039bb/shipped")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getOrdersByUserId() throws Exception {
        Order order=new Order();
        String userId="8922b8a0-cf4c-43fa-abca-fd55c78e7d10";
        order.setOrderId("787a393c-18b7-4a08-b2cc-064b7a3039bb");
        order.setOrderItems("potatoes");
        order.setOrderPrice((float)32);
        order.setOrderQuantity(1);
        order.setDeliveryAddress("#57,3rd cross,1st stage,Hebbal-kempapura,Bangalore");
        order.setOrderStatus("shipped");

        List<Order> allOrder=singletonList(order);
        given(orderController.getOrdersByUserId(userId)).willReturn(allOrder);
        mockMvc.perform(get("/order/8922b8a0-cf4c-43fa-abca-fd55c78e7d10")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createOrderId() throws Exception{
        Order order= new Order();
        order.setOrderId("787a393c-18b7-4a08-b2cc-064b7a3039bb");
        order.setOrderItems("potatoes");
        order.setOrderPrice((float)32);
        order.setOrderQuantity(1);
        order.setDeliveryAddress("#57,3rd cross,1st stage,Hebbal-kempapura,Bangalore");
        order.setOrderStatus("shipped");
        given(orderController.createOrderId(order)).willReturn(order);

        mockMvc.perform(post("/order")
                .content(asJsonString(order))
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