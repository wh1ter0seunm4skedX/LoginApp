package com.b7av3.loginapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginAssignmentAppTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginAndDashboardAccess() throws Exception {
        // Perform a POST request to the login endpoint with valid credentials
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .param("username", "your_username")
                        .param("password", "your_password"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/dashboard"));

        // Now, perform a GET request to the dashboard endpoint to verify access
        mockMvc.perform(MockMvcRequestBuilders.get("/dashboard"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // You can add additional assertions to verify dashboard content
    }
}