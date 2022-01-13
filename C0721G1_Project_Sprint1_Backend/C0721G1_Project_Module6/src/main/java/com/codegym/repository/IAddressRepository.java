package com.codegym.repository;

import com.codegym.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Long> {
}
