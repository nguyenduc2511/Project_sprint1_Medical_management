package com.codegym.controller;

import com.codegym.model.Producer;
import com.codegym.model.SuppliesType;
import com.codegym.service.IProducerService;
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
@CrossOrigin
public class ProducerController {
    @Autowired
    private IProducerService producerService;
    //Thanh 29/12
    @GetMapping("admin/producer/list")
    public ResponseEntity<?> getProducerList() {
        List<Producer> producers = (List<Producer>) producerService.findAll();
        return new ResponseEntity<>(producers, HttpStatus.OK);
    }

}
