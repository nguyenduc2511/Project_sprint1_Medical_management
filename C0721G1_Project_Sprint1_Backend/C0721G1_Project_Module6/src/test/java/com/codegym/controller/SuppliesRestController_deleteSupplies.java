package com.codegym.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SuppliesRestController_deleteSupplies {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deleteSupplies_25() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/admin/supplies/{id}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void deleteSupplies_26() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/admin/supplies/{id}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void deleteSupplies_28() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/admin/supplies/{id}", "3"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void deleteEmployee_27() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/admin/supplies/{id}", "6"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
