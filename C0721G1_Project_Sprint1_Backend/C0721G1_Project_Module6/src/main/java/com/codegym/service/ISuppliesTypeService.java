package com.codegym.service;

import com.codegym.model.Supplies;
import com.codegym.model.SuppliesType;

import java.util.List;

public interface ISuppliesTypeService extends IGenericService<SuppliesType> {
    List<SuppliesType> getAll();
}
