package com.example.plannerREST.controllers;

import com.example.plannerREST.dto.CompanyDTO;
import com.example.plannerREST.entities.AuthRequest;
import com.example.plannerREST.exception.ApiRequestException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerTest {

    private String jwtToken;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtRestController jwtRestController;

    @BeforeEach
    void generateToken() throws ApiRequestException {

        AuthRequest authRequest = new AuthRequest("catalin", "catalin");
        jwtToken = jwtRestController.generateToken(authRequest).getJwtToken();

    }

    @Test
    void getAllCompanies() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/companies/")
                .header("authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        List<CompanyDTO> companyDTOList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<CompanyDTO>>() {});

        assertThat(companyDTOList.get(0).getCity()).isEqualTo("test").isNotNull();
    }

    @Test
    void getOneCompany() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/companies/editCompany/{id}", "1")
                .header("authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();

        //ObjectMapper objectMapper = new ObjectMapper();
        //CompanyDTO companyDTO = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<CompanyDTO>() {});

        //assertThat(companyDTO).isNotNull();
    }
}