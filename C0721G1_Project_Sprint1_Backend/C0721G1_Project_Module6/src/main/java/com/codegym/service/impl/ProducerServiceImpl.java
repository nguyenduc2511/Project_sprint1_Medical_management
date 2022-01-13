package com.codegym.service.impl;

import com.codegym.model.Producer;
import com.codegym.repository.IProducerRepository;
import com.codegym.service.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerServiceImpl implements IProducerService {
    @Autowired
    private IProducerRepository iProducerRepository;

    @Override
    public Iterable<Producer> findAll() {
        return iProducerRepository.findAll();
    }

    @Override
    public Optional<Producer> findById(Long id) {
        return iProducerRepository.findById(id);
    }

    @Override
    public void save(Producer producer) {
        iProducerRepository.save(producer);
    }

    @Override
    public void remove(Long id) {
        iProducerRepository.deleteById(id);
    }

    @Override
    public List<Producer> getAll() {
        return iProducerRepository.findAll();
    }
}
