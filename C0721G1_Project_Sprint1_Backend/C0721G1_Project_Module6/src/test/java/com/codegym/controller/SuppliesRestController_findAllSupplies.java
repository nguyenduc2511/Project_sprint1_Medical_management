package com.codegym.controller;

import com.codegym.model.Supplies;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

@SpringBootTest
@AutoConfigureMockMvc
public class SuppliesRestController_findAllSupplies {

    @Autowired
    private SuppliesController suppliesController;

    @Test
    public void findAllSupplies_5() throws ParseException {
        ResponseEntity<Page<Supplies>> responseEntity
                = (ResponseEntity<Page<Supplies>>) this.suppliesController.findAllSupplies("", "", "", 3, 2);
        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void findAllSupplies_6() throws ParseException {
        ResponseEntity<Page<Supplies>> responseEntity
                = (ResponseEntity<Page<Supplies>>) this.suppliesController.findAllSupplies("", "", "", 0, 2);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(2, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("thuốc tây", responseEntity.getBody().getContent().get(1).getName());
    }

    @Test
    public void findAllSupplies_6_1() throws ParseException {
        ResponseEntity<Page<Supplies>> responseEntity
                = (ResponseEntity<Page<Supplies>>) this.suppliesController.findAllSupplies("", "a", "", 0, 2);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals("khẩu trang", responseEntity.getBody().getContent().get(0).getName());
    }

    @Test
    public void findAllSupplies_6_2() throws ParseException {
        ResponseEntity<Page<Supplies>> responseEntity
                = (ResponseEntity<Page<Supplies>>) this.suppliesController.findAllSupplies("MVT-002", "", "", 0, 2);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals("khẩu trang", responseEntity.getBody().getContent().get(0).getName());
    }

    @Test
    public void findAllSupplies_6_3() throws ParseException {
        ResponseEntity<Page<Supplies>> responseEntity
                = (ResponseEntity<Page<Supplies>>) this.suppliesController.findAllSupplies("", "", "1", 0, 2);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals("khẩu trang", responseEntity.getBody().getContent().get(0).getName());
    }

    @Test
    public void findAllSupplies_5_1() throws ParseException {
        ResponseEntity<Page<Supplies>> responseEntity
                = (ResponseEntity<Page<Supplies>>) this.suppliesController.findAllSupplies("fnfhre", "", "", 3, 2);
        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void findAllSupplies_5_2() throws ParseException {
        ResponseEntity<Page<Supplies>> responseEntity
                = (ResponseEntity<Page<Supplies>>) this.suppliesController.findAllSupplies("", "fndfn", "", 3, 2);
        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
    }
}
