package com.codegym.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityController_getRegisterUser {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getRegisterUser_1() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/public/register/{code}", "null"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getRegisterUser_2() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/public/register/{code}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getRegisterUser_3() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/public/register/{code}", "MNV-0100"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /*
    Code tồn tại trong DB, nhưng username không tồn tại
     */
    @Test
    public void getRegisterUser_4a() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/public/register/{code}", "MNV-0008"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /*
    Code tồn tại trong DB, nhưng username không tồn tại
     */
    @Test
    public void getRegisterUser_4b() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/public/register/{code}", "MNV-0002"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.username").value("user"))
                .andExpect(jsonPath("$.role").value("user"));
    }
}
