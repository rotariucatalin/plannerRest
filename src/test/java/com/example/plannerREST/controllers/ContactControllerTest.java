package com.example.plannerREST.controllers;

import com.example.plannerREST.entities.AuthRequest;
import com.example.plannerREST.exception.ApiRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

    private String jwtToken;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtRestController jwtRestController;

    @BeforeEach
    void generateToken() throws ApiRequestException {

        AuthRequest  authRequest = new AuthRequest("catalin", "catalin");
        jwtToken = jwtRestController.generateToken(authRequest).getJwtToken();

    }

    @Test
    void getAllContacts() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/contacts/").header("authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

}