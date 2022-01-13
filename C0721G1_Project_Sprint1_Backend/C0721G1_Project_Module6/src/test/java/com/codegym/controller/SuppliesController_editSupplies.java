package com.codegym.controller;

import com.codegym.dto.SuppliesDTO;
import com.codegym.model.Producer;
import com.codegym.model.SuppliesType;
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
public class SuppliesController_editSupplies {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    //    All [item] đều hợp lệ
    @Test
    public void editSupplies_name_24() throws Exception {
        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setId((long) 1);
        suppliesDTO.setCode("MVT-0001");
        suppliesDTO.setName("Khẩu trang 3D");
        suppliesDTO.setPrice((long) 50000);
        Producer producer =new Producer();
        producer.setId((long) 1);
        producer.setName("Cty CP DƯợc Sài Gòn");
        suppliesDTO.setProducer(producer);
        SuppliesType type =new SuppliesType();
        type.setId((long) 1);
        type.setName("Khẩu trang");
        suppliesDTO.setProductionDate("2020-10-10");
        suppliesDTO.setExpiryDate("2022-05-20");
        suppliesDTO.setIntroduce("Khẩu trang chống khuẩn 3 lớp nhập khẩu từ Nhật");
        suppliesDTO.setTechnicalInformation("Đạt chuẩn IOS 2000");
        suppliesDTO.setImage("img/jpg");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/admin/supplies/edit")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
    //Mã vật tư trùng
    @Test
    public void editSupplies_name_24_1() throws Exception {
        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setId((long) 1);
        suppliesDTO.setCode("MVT-0001");
        suppliesDTO.setName("Khẩu trang 3D");
        suppliesDTO.setPrice((long) 50000);
        Producer producer =new Producer();
        producer.setId((long) 1);
        producer.setName("Cty CP DƯợc Sài Gòn");
        suppliesDTO.setProducer(producer);
        SuppliesType type =new SuppliesType();
        type.setId((long) 1);
        type.setName("Khẩu trang");
        suppliesDTO.setProductionDate("2021-10-10");
        suppliesDTO.setExpiryDate("2022-05-20");
        suppliesDTO.setIntroduce("Khẩu trang chống khuẩn 3 lớp nhập khẩu từ Nhật");
        suppliesDTO.setTechnicalInformation("Đạt chuẩn IOS 2000");
        suppliesDTO.setImage("img/jpg");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/admin/supplies/edit")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    //ngày sản xuất trùng ngày hoặc sau hiện tại
    @Test
    public void editSupplies_name_date() throws Exception {
        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setId((long) 1);
        suppliesDTO.setCode("MVT-0001");
        suppliesDTO.setName("Khẩu trang 3D");
        suppliesDTO.setPrice((long) 50000);
        Producer producer =new Producer();
        producer.setId((long) 1);
        producer.setName("Cty CP DƯợc Sài Gòn");
        suppliesDTO.setProducer(producer);
        SuppliesType type =new SuppliesType();
        type.setId((long) 1);
        type.setName("Khẩu trang");
        suppliesDTO.setProductionDate("2021-12-28");
        suppliesDTO.setExpiryDate("2022-05-20");
        suppliesDTO.setIntroduce("Khẩu trang chống khuẩn 3 lớp nhập khẩu từ Nhật");
        suppliesDTO.setTechnicalInformation("Đạt chuẩn IOS 2000");
        suppliesDTO.setImage("img/jpg");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/admin/supplies/edit")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    //hạn setIntroduce quá ngắn
    @Test
    public void editSupplies_name_ingtroduce() throws Exception {
        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setId((long) 1);
        suppliesDTO.setCode("MVT-0001");
        suppliesDTO.setName("Khẩu trang 3D");
        suppliesDTO.setPrice((long) 50000);
        Producer producer =new Producer();
        producer.setId((long) 1);
        producer.setName("Cty CP DƯợc Sài Gòn");
        suppliesDTO.setProducer(producer);
        SuppliesType type =new SuppliesType();
        type.setId((long) 1);
        type.setName("Khẩu trang");
        suppliesDTO.setProductionDate("2021-10-10");
        suppliesDTO.setExpiryDate("2022-05-20");
        suppliesDTO.setIntroduce("a");
        suppliesDTO.setTechnicalInformation("Đạt chuẩn IOS 2000");
        suppliesDTO.setImage("img/jpg");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/admin/supplies/edit")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    //[item] = null
    @Test
    public void editStudent_name_20() throws Exception {

        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setId((long) 1);
        suppliesDTO.setCode("");
        suppliesDTO.setName("");
        suppliesDTO.setPrice((long) 50000);
        Producer producer =new Producer();
        producer.setId((long) 1);
        producer.setName("Cty CP DƯợc Sài Gòn");
        suppliesDTO.setProducer(producer);
        SuppliesType type =new SuppliesType();
        type.setId((long) 1);
        type.setName("Khẩu trang");
        suppliesDTO.setProductionDate("2020-10-10");
        suppliesDTO.setExpiryDate("2022-05-20");
        suppliesDTO.setIntroduce("Khẩu trang chống khuẩn 3 lớp nhập khẩu từ Nhật");
        suppliesDTO.setTechnicalInformation("Đạt chuẩn IOS 2000");
        suppliesDTO.setImage("img/jpg");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/admin/supplies/edit")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    //[item] =  rỗng ("")
    @Test
    public void editStudent_name_19() throws Exception {

        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setId((long) 1);
        suppliesDTO.setCode("");
        suppliesDTO.setPrice((long) 50000);
        Producer producer =new Producer();
        producer.setId((long) 1);
        producer.setName("Cty CP DƯợc Sài Gòn");;
        suppliesDTO.setProducer(producer);
        SuppliesType type =new SuppliesType();
        type.setId((long) 1);
        type.setName("Khẩu trang");
        suppliesDTO.setProductionDate("2022-10-10");
        suppliesDTO.setExpiryDate("2021-10-10");
        suppliesDTO.setIntroduce("Khẩu trang chống khuẩn 3 lớp nhập khẩu từ Nhật");
        suppliesDTO.setTechnicalInformation("Đạt chuẩn IOS 2000");
        suppliesDTO.setImage("img/jpg");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/admin/supplies/edit")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    //[item] sai format (phụ thuộc vào từng item cụ thể: chỉ chứa chữ, chỉ chứa số, định dạng email…)
    @Test
    public void editStudent_name_21() throws Exception {

        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setId((long) 1);
        suppliesDTO.setCode("MTv-001");
        suppliesDTO.setName("Khẩu trang");
        suppliesDTO.setPrice((long) 50000);
        Producer producer =new Producer();
        producer.setId((long) 1);
        producer.setName("Cty CP DƯợc Sài Gòn");
        suppliesDTO.setProducer(producer);
        SuppliesType type =new SuppliesType();
        type.setId((long) 1);
        type.setName("Khẩu trang");
        suppliesDTO.setProductionDate("2020-10-10");
        suppliesDTO.setExpiryDate("2022-10-10");
        suppliesDTO.setIntroduce("Khẩu trang chống khuẩn 3 lớp nhập khẩu từ Nhật");
        suppliesDTO.setTechnicalInformation("Đạt chuẩn IOS 2000");
        suppliesDTO.setImage("img/jpg");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/admin/supplies/edit")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void editStudent_name_21_1() throws Exception {

        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setId((long) 1);
        suppliesDTO.setCode("MTv-001");
        suppliesDTO.setName("Khẩu trang");
        suppliesDTO.setPrice((long) 50);
        Producer producer =new Producer();
        producer.setId((long) 1);
        producer.setName("Cty CP DƯợc Sài Gòn");
        suppliesDTO.setProducer(producer);
        SuppliesType type =new SuppliesType();
        type.setId((long) 1);
        type.setName("Khẩu trang");
        suppliesDTO.setProductionDate("2021-10-10");
        suppliesDTO.setExpiryDate("2022-10-10");
        suppliesDTO.setIntroduce("Khẩu trang chống khuẩn 3 lớp nhập khẩu từ Nhật");
        suppliesDTO.setTechnicalInformation("Đạt chuẩn IOS 2000");
        suppliesDTO.setImage("img/jpg");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/admin/supplies/edit")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void editStudent_name_2_2() throws Exception {

        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setId((long) 1);
        suppliesDTO.setCode("MTv-001");
        suppliesDTO.setName("Khẩu trang");
        suppliesDTO.setPrice((long) 50000);
        Producer producer =new Producer();
        producer.setId((long) 1);
        producer.setName("Cty CP DƯợc Sài Gòn");
        suppliesDTO.setProducer(producer);
        SuppliesType type =new SuppliesType();
        type.setId((long) 1);
        type.setName("Khẩu trang");
        suppliesDTO.setProductionDate("2022-10-10");
        suppliesDTO.setExpiryDate("2021-10-10");
        suppliesDTO.setIntroduce("Khẩu trang chống khuẩn 3 lớp nhập khẩu từ Nhật");
        suppliesDTO.setTechnicalInformation("Đạt chuẩn IOS 2000");
        suppliesDTO.setImage("img/jpg");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/admin/supplies/edit")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    //[item] không >= minlength
    @Test
    public void editStudent_name_22() throws Exception {

        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setId((long) 1);
        suppliesDTO.setCode("MTv-001");
        suppliesDTO.setName("K");
        suppliesDTO.setPrice((long) 5000);
        Producer producer =new Producer();
        producer.setId((long) 2);
        producer.setName("Cty CP DƯợc Sài Gòn");
        suppliesDTO.setProducer(producer);
        SuppliesType type =new SuppliesType();
        type.setId((long) 1);
        type.setName("Khẩu trang");
        suppliesDTO.setProductionDate("2022-10-10");
        suppliesDTO.setExpiryDate("2021-10-10");
        suppliesDTO.setIntroduce("Khẩu trang chống khuẩn 3 lớp nhập khẩu từ Nhật");
        suppliesDTO.setTechnicalInformation("Đạt chuẩn IOS 2000");
        suppliesDTO.setImage("img/jpg");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/admin/supplies/edit")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    // [item] không <= maxlength
    @Test
    public void editStudent_name_23() throws Exception {

        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setId((long) 1);
        suppliesDTO.setCode("MTV-0001");
        suppliesDTO.setName("Kikkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        suppliesDTO.setPrice((long) 500);
        Producer producer =new Producer();
        producer.setId((long) 1);
        producer.setName("Cty CP DƯợc Sài Gòn");
        suppliesDTO.setProducer(producer);
        SuppliesType type =new SuppliesType();
        type.setId((long) 1);
        type.setName("Khẩu trang");
        suppliesDTO.setProductionDate("2022-10-10");
        suppliesDTO.setExpiryDate("2021-10-10");
        suppliesDTO.setIntroduce("Khẩu trang chống khuẩn 3 lớp nhập khẩu từ Nhật");
        suppliesDTO.setTechnicalInformation("Đạt chuẩn IOS 2000");
        suppliesDTO.setImage("img/jpg");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/admin/supplies/create")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
