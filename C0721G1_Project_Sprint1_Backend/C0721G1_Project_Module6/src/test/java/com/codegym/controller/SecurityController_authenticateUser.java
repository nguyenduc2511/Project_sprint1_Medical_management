package com.codegym.controller;

import ch.qos.logback.core.boolex.Matcher;
import com.codegym.payload.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityController_authenticateUser {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /*
    username: null
     */
    @Test
    public void authenticateUser_username_13() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(null);
        loginRequest.setPassword("123456");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    password: null
     */
    @Test
    public void authenticateUser_password_13() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    username: null, password: null
     */
    @Test
    public void authenticateUser_13() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(null);
        loginRequest.setPassword(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    username: rỗng
     */
    @Test
    public void authenticateUser_username_14() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("");
        loginRequest.setPassword("123456");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    password: rỗng
     */
    @Test
    public void authenticateUser_password_14() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    username: rỗng, password: rỗng
     */
    @Test
    public void authenticateUser_14() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("");
        loginRequest.setPassword("");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    Vì Login bằng phương thức post, nên không có check đúng format, minlenth, maxlength. Thay vào đó sẽ có 4 trường hợp
    (trong đó có 3 trường hợp sai (từ 15-17), và 1 trường hợp đúng (18))
     */
    /*
    Username: đúng, password: sai
     */
    @Test
    public void authenticateUser_15() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("123456789");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    Username: sai, password: đúng
     */
    @Test
    public void authenticateUser_16() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin1");
        loginRequest.setPassword("admin");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    Username: sai, password: sai
     */
    @Test
    public void authenticateUser_17() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin1");
        loginRequest.setPassword("123456789");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /*
    Username: đúng, password: đúng
     */
    @Test
    public void authenticateUser_18() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("admin");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.roles", hasSize(2)))
                .andExpect(jsonPath("$.employee.id").value(1))
                .andExpect(jsonPath("$.employee.code").value("MNV-0001"))
                .andExpect(jsonPath("$.employee.name").value("Nguyễn Văn A"))
                .andExpect(jsonPath("$.employee.birthday").value("1990-01-01"))
                .andExpect(jsonPath("$.employee.phone").value("0905141216"))
                .andExpect(jsonPath("$.employee.address").value("Đà Nẵng"))
                .andExpect(jsonPath("$.employee.position.id").value(1));
    }


}
