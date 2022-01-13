package com.codegym.service;

public interface IFinancialService {
    Integer getIncome();
    Integer getReturn();
    Integer getImport();
    Integer getCancelled();
    Integer getRefund();
    Integer getMonthSales(String date);
    Integer getMonthImport(String date);
    Integer getMonthReturn(String date);
    Integer getMonthRefund(String date);
    Integer getMonthCancelled(String date);

}
