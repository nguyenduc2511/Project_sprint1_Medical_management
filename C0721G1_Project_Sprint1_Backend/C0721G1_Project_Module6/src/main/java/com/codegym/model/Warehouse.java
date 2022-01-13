package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer brokenSupplies;

    private Integer normalSupplies;

    private String importDate;

    private Integer price;

    private Integer quantity;

    private String unit;

    private Integer cancelledSupplies;

    private Integer refundSupplies;

    private Integer importQuantity;


    @JsonBackReference(value = "warehouse-back-class")
    @OneToMany(mappedBy = "warehouses")
    private Set<Supplies> supplies;

    public Warehouse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBrokenSupplies() {
        return brokenSupplies;
    }

    public void setBrokenSupplies(Integer brokenSupplies) {
        this.brokenSupplies = brokenSupplies;
    }

    public Integer getNormalSupplies() {
        return normalSupplies;
    }

    public void setNormalSupplies(Integer normalSupplies) {
        this.normalSupplies = normalSupplies;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getCancelledSupplies() {
        return cancelledSupplies;
    }

    public void setCancelledSupplies(Integer cancelledSupplies) {
        this.cancelledSupplies = cancelledSupplies;
    }

    public Integer getRefundSupplies() {
        return refundSupplies;
    }

    public void setRefundSupplies(Integer refundSupplies) {
        this.refundSupplies = refundSupplies;
    }

    public Integer getImportQuantity() {
        return importQuantity;
    }

    public void setImportQuantity(Integer importQuantity) {
        this.importQuantity = importQuantity;
    }

    public Set<Supplies> getSupplies() {
        return supplies;
    }

    public void setSupplies(Set<Supplies> supplies) {
        this.supplies = supplies;
    }
}
