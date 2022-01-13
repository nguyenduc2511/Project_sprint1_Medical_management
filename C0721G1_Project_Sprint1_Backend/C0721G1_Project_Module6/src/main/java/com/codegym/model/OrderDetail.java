package com.codegym.model;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderDate;

    private Integer totalMoney;

    private Integer quantity;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(targetEntity = Supplies.class)
    @JoinColumn(name = "supplies_id", referencedColumnName = "id")
    private Supplies supplies;

    public OrderDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Supplies getSupplies() {
        return supplies;
    }

    public void setSupplies(Supplies supplies) {
        this.supplies = supplies;
    }
}