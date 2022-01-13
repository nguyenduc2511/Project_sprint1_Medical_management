package com.codegym.dto;

import com.codegym.model.OrderDetail;
import com.codegym.model.Producer;
import com.codegym.model.SuppliesType;
import com.codegym.model.Warehouse;

public interface ISuppliesDTO {
    Long getId();

    String getCode();

    String getExpiry_date();

    String getName();

    Long getPrice();

    String getProduction_date();

    Long getProducer_id();

    Long getSupplies_type_id();

    int getStatus();

}
