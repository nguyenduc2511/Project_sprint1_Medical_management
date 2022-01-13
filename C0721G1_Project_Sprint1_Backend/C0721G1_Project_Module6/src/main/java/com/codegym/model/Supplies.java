package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Supplies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private Long price;

    private String productionDate;

    private String expiryDate;

    @Column(columnDefinition = "TEXT")
    private String introduce;

    @Column(columnDefinition = "TEXT")
    private String technicalInformation;

    private String image;

    @ManyToOne(targetEntity = SuppliesType.class)
    @JoinColumn(name = "supplies_type_id", referencedColumnName = "id")
    private SuppliesType suppliesType;

    @ManyToOne(targetEntity = Producer.class)
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;

    @JsonBackReference(value = "supplies-back-class")
    @OneToMany(mappedBy = "supplies")
    private Set<OrderDetail> orderDetails;

    @ManyToOne(targetEntity = Warehouse.class)
    @JoinColumn(name = "warehouses_id", referencedColumnName = "id")
    private Warehouse warehouses;

    public Supplies() {
    }

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTechnicalInformation() {
        return technicalInformation;
    }

    public void setTechnicalInformation(String technicalInformation) {
        this.technicalInformation = technicalInformation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SuppliesType getSuppliesType() {
        return suppliesType;
    }

    public void setSuppliesType(SuppliesType suppliesType) {
        this.suppliesType = suppliesType;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Warehouse getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Warehouse warehouses) {
        this.warehouses = warehouses;
    }
}