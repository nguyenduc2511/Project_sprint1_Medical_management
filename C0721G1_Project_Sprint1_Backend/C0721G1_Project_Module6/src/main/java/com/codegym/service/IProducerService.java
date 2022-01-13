package com.codegym.service;

import com.codegym.model.Producer;
import com.codegym.model.SuppliesType;

import java.util.List;

import java.util.List;

public interface IProducerService extends IGenericService<Producer> {
    List<Producer> getAll();
}
