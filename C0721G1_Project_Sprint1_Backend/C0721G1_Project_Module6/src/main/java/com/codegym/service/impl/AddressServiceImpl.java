package com.codegym.service.impl;

import com.codegym.model.Address;
import com.codegym.repository.IAddressRepository;
import com.codegym.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private IAddressRepository iAddressRepository;

    @Override
    public Iterable<Address> findAll() {
        return iAddressRepository.findAll();
    }

    @Override
    public Optional<Address> findById(Long id) {
        return iAddressRepository.findById(id);
    }

    @Override
    public void save(Address address) {
        iAddressRepository.save(address);
    }

    @Override
    public void remove(Long id) {
        iAddressRepository.deleteById(id);
    }

    @Override
    public List<Address> getAll() {
        return iAddressRepository.findAll();
    }
}
