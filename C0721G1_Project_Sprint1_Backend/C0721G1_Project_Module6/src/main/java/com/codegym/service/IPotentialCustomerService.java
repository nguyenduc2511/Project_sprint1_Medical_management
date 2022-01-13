package com.codegym.service;

import com.codegym.dto.PotentialCustomerDto;

import java.time.LocalDate;
import java.util.List;

public interface IPotentialCustomerService {
    List<PotentialCustomerDto> getAll();

    List<PotentialCustomerDto> getPotentialCustomerByTime(LocalDate startDate, LocalDate endDate);

}
