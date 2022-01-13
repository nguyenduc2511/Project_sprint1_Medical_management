package com.codegym.service.impl;

import com.codegym.repository.FinancialDtoRepository;
import com.codegym.service.IFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialService implements IFinancialService {
    @Autowired
    private FinancialDtoRepository financialDtoRepository;

    @Override
    public Integer getIncome() {
        return financialDtoRepository.getIncome();
    }

    @Override
    public Integer getReturn() {
        return financialDtoRepository.getReturn();
    }

    @Override
    public Integer getImport() {
        return financialDtoRepository.getImport();
    }

    @Override
    public Integer getCancelled() {
        return financialDtoRepository.getCancelled();
    }

    @Override
    public Integer getRefund() {
        return financialDtoRepository.getRefund();
    }

    @Override
    public Integer getMonthSales(String date) {
        return financialDtoRepository.getMonthSales(date + "%");
    }

    @Override
    public Integer getMonthImport(String date) {

        return financialDtoRepository.getMonthImport(date + "%");
    }

    @Override
    public Integer getMonthReturn(String date) {
        return financialDtoRepository.getMonthReturn(date + "%");
    }

    @Override
    public Integer getMonthRefund(String date) {
        return financialDtoRepository.getMonthRefund(date + "%");
    }

    @Override
    public Integer getMonthCancelled(String date) {
        return financialDtoRepository.getMonthCancelled(date + "%");
    }

}
