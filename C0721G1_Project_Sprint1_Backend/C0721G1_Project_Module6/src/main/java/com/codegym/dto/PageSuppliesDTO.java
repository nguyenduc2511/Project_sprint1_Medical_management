package com.codegym.dto;

import org.springframework.data.domain.Page;

public class PageSuppliesDTO {
    private String name;
    private String code;
    private String suppliesType;
    private int page;
    private int size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuppliesType() {
        return suppliesType;
    }

    public void setSuppliesType(String suppliesType) {
        this.suppliesType = suppliesType;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
