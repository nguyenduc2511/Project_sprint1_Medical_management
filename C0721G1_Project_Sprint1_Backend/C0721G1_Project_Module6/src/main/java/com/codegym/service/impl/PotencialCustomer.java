package com.codegym.service.impl;

import com.codegym.dto.PotentialCustomerDto;
import com.codegym.repository.PotentialCustomerRepository;
import com.codegym.service.IPotentialCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PotencialCustomer implements IPotentialCustomerService {
    @Autowired
    PotentialCustomerRepository potentialCustomerRepository;

    @Override
    public List<PotentialCustomerDto> getAll() {
        return potentialCustomerRepository.getAll();
    }


    @Override
    public List<PotentialCustomerDto> getPotentialCustomerByTime(LocalDate startDate, LocalDate endDate) {
        return potentialCustomerRepository.getPotentialCustomerByTime(startDate,endDate);
    }


}
