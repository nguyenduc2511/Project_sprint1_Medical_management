package com.codegym.controller;

import com.codegym.payload.request.RegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityController_registerEditPassword {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /*
    password: null
     */
    @Test
    public void registerEditPassword_password_19() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user");
        registerRequest.setPassword(null);
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/public/register-edit-password/{code}", "MNV-0002")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    password: rỗng
     */
    @Test
    public void registerEditPassword_password_20() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user");
        registerRequest.setPassword("");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/public/register-edit-password/{code}", "MNV-0002")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    password: sai định dạng
     */
    @Test
    public void registerEditPassword_password_21() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user");
        registerRequest.setPassword("@()");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/public/register-edit-password/{code}", "MNV-0002")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    password: nhỏ hơn minlength
     */
    @Test
    public void registerEditPassword_password_22() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user");
        registerRequest.setPassword("12");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/public/register-edit-password/{code}", "MNV-0002")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    password: lớn hơn minlength
     */
    @Test
    public void registerEditPassword_password_23() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user");
        registerRequest.setPassword("123123123123");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/public/register-edit-password/{code}", "MNV-0002")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    cập nhật password thành công
     */
    @Test
    public void registerEditPassword_password_24() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user");
        registerRequest.setPassword("123456");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/public/register-edit-password/{code}", "MNV-0002")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
