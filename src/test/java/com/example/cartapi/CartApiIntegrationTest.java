package com.example.cartapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CartApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    // Test GET /cart/{userId}
    @Test
    public void testGetCartByUserId() throws Exception {
        // Assuming there's a cart for userId 1
        mockMvc.perform(get("/cart/1"))
               .andExpect(status().isOk())                    // Expect 200 OK response
               .andExpect(jsonPath("$[0].userId").value(1))    // Expect userId to be 1
               .andExpect(jsonPath("$[0].productId").exists()) // Expect productId to be present
               .andExpect(jsonPath("$[0].quantity").exists()); // Expect quantity to be present
    }

    // Test POST /cart/{userId}/add (Create a new cart item for user)
    @Test
    public void testAddItemToCart() throws Exception {
        // Create a sample cart object
        String cartJson = "{ \"productId\": 101, \"quantity\": 2 }";

        mockMvc.perform(post("/cart/1/add")  // POST to /cart/{userId}/add
                .contentType(MediaType.APPLICATION_JSON)
                .content(cartJson)) 
                .andExpect(status().isCreated())         // Expect 201 Created response
                .andExpect(jsonPath("$.userId").value(1))  // Expect the correct userId
                .andExpect(jsonPath("$.productId").value(101))  // Expect the correct productId
                .andExpect(jsonPath("$.quantity").value(2));  // Expect the correct quantity
    }
}
