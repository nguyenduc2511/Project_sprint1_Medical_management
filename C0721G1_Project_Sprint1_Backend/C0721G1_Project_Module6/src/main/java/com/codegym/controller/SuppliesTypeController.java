package com.codegym.controller;

import com.codegym.model.Supplies;
import com.codegym.model.SuppliesType;
import com.codegym.service.ISuppliesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@EnableWebMvc
@RequestMapping("api")
@CrossOrigin()
public class SuppliesTypeController {
    @Autowired
    private ISuppliesTypeService suppliesTypeService;
    //Thanh 29/12
    @GetMapping("/admin/suppliesType/list")
    public ResponseEntity<?> getSuppliesTypeList() {
        List<SuppliesType> suppliesTypes = (List<SuppliesType>) suppliesTypeService.findAll();
        return new ResponseEntity<>(suppliesTypes, HttpStatus.OK);
    }
}
