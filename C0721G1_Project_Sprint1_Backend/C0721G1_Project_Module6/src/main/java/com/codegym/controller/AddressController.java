package com.codegym.controller;
import com.codegym.model.Address;
import com.codegym.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("api/public/address")
public class AddressController {
    @Autowired
    IAddressService iAddressService;

    @GetMapping("")
    public ResponseEntity<List<Address>> getAddressList(){
        List<Address> addressList = iAddressService.getAll();
        if(!addressList.isEmpty()){
            return new ResponseEntity<>(addressList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
