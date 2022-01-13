package com.codegym.service.impl;

import com.codegym.model.Warehouse;
import com.codegym.repository.IWarehouseRepository;
import com.codegym.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseServiceImpl implements IWarehouseService {
    @Autowired
    private IWarehouseRepository iWarehouseRepository;

    @Override
    public Iterable<Warehouse> findAll() {
        return iWarehouseRepository.findAll();
    }

    @Override
    public Optional<Warehouse> findById(Long id) {
        return iWarehouseRepository.findById(id);
    }

    @Override
    public void save(Warehouse warehouse) {
        iWarehouseRepository.save(warehouse);
    }

    @Override
    public void remove(Long id) {
        iWarehouseRepository.deleteById(id);
    }
}
