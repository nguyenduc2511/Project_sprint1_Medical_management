package com.codegym.dto;

import com.codegym.service.IFinancialService;

public class FinancialStatsDto {
    private Integer income;
    private Integer returnMoney;
    private Integer refund;
    private Integer cancelled;
    private Integer importMoney;

    public FinancialStatsDto() {
    }

    public FinancialStatsDto(Integer income, Integer returnMoney, Integer refund, Integer cancelled, Integer importMoney) {
        this.income = income;
        this.returnMoney = returnMoney;
        this.refund = refund;
        this.cancelled = cancelled;
        this.importMoney = importMoney;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(Integer returnMoney) {
        this.returnMoney = returnMoney;
    }

    public Integer getRefund() {
        return refund;
    }

    public void setRefund(Integer refund) {
        this.refund = refund;
    }

    public Integer getCancelled() {
        return cancelled;
    }

    public void setCancelled(Integer cancelled) {
        this.cancelled = cancelled;
    }

    public Integer getImportMoney() {
        return importMoney;
    }

    public void setImportMoney(Integer importMoney) {
        this.importMoney = importMoney;
    }

    public void setImportMoney(IFinancialService financialService) {
    }
}
