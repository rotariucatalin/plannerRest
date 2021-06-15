package com.example.plannerREST.controllers;

import com.example.plannerREST.dto.ActivityDTO;
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
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ActivityControllerTest {

    private String jwtToken;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtRestController response;

    @BeforeEach
    void generateToken() throws ApiRequestException {
        AuthRequest authRequest = new AuthRequest("catalin", "catalin");
        jwtToken = response.generateToken(authRequest).getJwtToken();
    }

    @Test
    void getALlActivities() throws Exception {

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/activities/")
                        .header("authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        List<ActivityDTO> activityDTOList = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ActivityDTO>>() {});
        assertThat(activityDTOList.get(0).getSubject()).isEqualTo("Test");
    }

    @Test
    void getOneActivity() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/activities/editActivity/3")
                .header("authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }
}