package com.tgt.igniteplus.checkoutservice.controller;

import com.tgt.igniteplus.checkoutservice.model.Cart;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CartControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CartController cartController = new CartController();
    Cart mockCart=new Cart("4d6b92ac-13fc-4eb4-abac-0b90af0ca64a","aa64f5f0-55a1-4cfc-b9a5-2b93be904e34");

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    public void hello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello")).andExpect(MockMvcResultMatchers.status().isBadGateway());
    }

//    @Test
//    public void getAll() throws Exception {
//        //Mockito.when(cartController.getAll().t)
//
//
//    }
//
    @Test
    public void getByUserId() throws Exception{
        Mockito.when(cartController.getByUserId(Mockito.anyString())).thenReturn(mockCart);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/cart/4d6b92ac-13fc-4eb4-abac-0b90af0ca64a")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mvcResult.getResponse());
        String expected="{\n" +
                "    \"userId\": \"4d6b92ac-13fc-4eb4-abac-0b90af0ca64a\",\n" +
                "    \"cartId\": \"aa64f5f0-55a1-4cfc-b9a5-2b93be904e34\"\n" +
                "  }";
        JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(),false);
   }
//
//    @Test
//    public void createCartId() {
//    }
//}
}