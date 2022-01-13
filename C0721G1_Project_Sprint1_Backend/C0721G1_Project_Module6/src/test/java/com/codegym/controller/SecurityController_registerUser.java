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
public class SecurityController_registerUser {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /*
    username: null
     */
    @Test
    public void registerUser_username_13() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(null);
        registerRequest.setPassword("12345678");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/register/{code}", "MNV-0009")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    username: rỗng
     */
    @Test
    public void registerUser_username_14() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("");
        registerRequest.setPassword("12345678");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/register/{code}", "MNV-0009")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    username: không đúng định dạng
     */
    @Test
    public void registerUser_username_15() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user_");
        registerRequest.setPassword("12345678");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/register/{code}", "MNV-0009")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    username: nhỏ hơn minlength
     */
    @Test
    public void registerUser_username_16() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("use");
        registerRequest.setPassword("12345678");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/register/{code}", "MNV-0009")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    username: lớn hơn maxlength
     */
    @Test
    public void registerUser_username_17() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user1234567");
        registerRequest.setPassword("12345678");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/register/{code}", "MNV-0009")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    Nhập username hợp lệ nhưng trùng với username đã có trong DB
     */
    @Test
    public void registerUser_username_17a() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user8");
        registerRequest.setPassword("12345678");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/register/{code}", "MNV-0009")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    password: null
     */
    @Test
    public void registerUser_password_13() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user10");
        registerRequest.setPassword(null);
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/register/{code}", "MNV-0009")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    password: rỗng
     */
    @Test
    public void registerUser_password_14() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user10");
        registerRequest.setPassword("");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/register/{code}", "MNV-0009")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    password: không đúng định dạng
     */
    @Test
    public void registerUser_password_15() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user10");
        registerRequest.setPassword("@()");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/register/{code}", "MNV-0009")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    password: nhỏ hơn minlength
     */
    @Test
    public void registerUser_password_16() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user10");
        registerRequest.setPassword("12");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/register/{code}", "MNV-0009")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    password: lớn hơn maxlength
     */
    @Test
    public void registerUser_password_17() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user10");
        registerRequest.setPassword("123123123123");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/register/{code}", "MNV-0009")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    đăng ký thành công
     */
    @Test
    public void registerUser_18() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user10");
        registerRequest.setPassword("123456");
        registerRequest.setRole("user");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/register/{code}", "MNV-0009")
                        .content(this.objectMapper.writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
