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
public class SuppliesController_createSupplies {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
//    Thanh code 27-12
//    All [item] đều hợp lệ
    @Test
    public void createSupplies_name_18() throws Exception {

        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setCode("MVT-0005");
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
        suppliesDTO.setExpiryDate("2021-10-10");
        suppliesDTO.setIntroduce("Khẩu trang chống khuẩn 3 lớp nhập khẩu từ Nhật");
        suppliesDTO.setTechnicalInformation("Đạt chuẩn IOS 2000");
        suppliesDTO.setImage("img.jpg");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/admin/supplies/create")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
    //    Mã nhân viên trùng
    @Test
    public void createSupplies_name_18_1() throws Exception {

        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setCode("MVT-0005");
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
        suppliesDTO.setExpiryDate("2021-10-10");
        suppliesDTO.setIntroduce("Khẩu trang chống khuẩn 3 lớp nhập khẩu từ Nhật");
        suppliesDTO.setTechnicalInformation("Đạt chuẩn IOS 2000");
        suppliesDTO.setImage("img.jpg");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/admin/supplies/create")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    //[item] = null
    @Test
    public void createStudent_name_14() throws Exception {

        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setCode("MVT-0001");
        suppliesDTO.setName("");
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
                        .post("/api/admin/supplies/create")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    //[item] =  rỗng ("")
    @Test
    public void createStudent_name_13() throws Exception {

        SuppliesDTO suppliesDTO = new SuppliesDTO();
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
                        .post("/api/admin/supplies/create")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    //[item] sai format (phụ thuộc vào từng item cụ thể: chỉ chứa chữ, chỉ chứa số, định dạng email…)
    @Test
    public void createStudent_name_15() throws Exception {

        SuppliesDTO suppliesDTO = new SuppliesDTO();
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
                        .post("/api/admin/supplies/create")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    //[item] không >= minlength
    @Test
    public void createStudent_name_16() throws Exception {

        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setCode("MTv-001");
        suppliesDTO.setName("Khẩu trang");
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
                        .post("/api/admin/supplies/create")
                        .content(this.objectMapper.writeValueAsString(suppliesDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
   // [item] không <= maxlength
   @Test
   public void createStudent_name_17() throws Exception {

       SuppliesDTO suppliesDTO = new SuppliesDTO();
       suppliesDTO.setCode("MTV-0001");
       suppliesDTO.setName("Ki");
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
                       .post("/api/admin/supplies/create")
                       .content(this.objectMapper.writeValueAsString(suppliesDTO))
                       .contentType(MediaType.APPLICATION_JSON_VALUE))
               .andDo(print())
               .andExpect(status().is4xxClientError());
   }
}
