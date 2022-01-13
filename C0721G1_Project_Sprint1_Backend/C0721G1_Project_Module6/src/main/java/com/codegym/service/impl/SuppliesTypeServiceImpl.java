package com.codegym.service.impl;

import com.codegym.model.Supplies;
import com.codegym.model.SuppliesType;
import com.codegym.repository.ISuppliesTypeRepository;
import com.codegym.service.ISuppliesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuppliesTypeServiceImpl implements ISuppliesTypeService {
    @Autowired
    private ISuppliesTypeRepository iSuppliesTypeRepository;

    @Override
    public Iterable<SuppliesType> findAll() {
        return iSuppliesTypeRepository.findAll();
    }

    @Override
    public Optional<SuppliesType> findById(Long id) {
        return iSuppliesTypeRepository.findById(id);
    }

    @Override
    public void save(SuppliesType suppliesType) {
        iSuppliesTypeRepository.save(suppliesType);
    }

    @Override
    public void remove(Long id) {
        iSuppliesTypeRepository.deleteById(id);
    }

    @Override
    public List<SuppliesType> getAll() {
        return iSuppliesTypeRepository.findAll();
    }
}
