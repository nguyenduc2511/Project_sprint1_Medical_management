package com.codegym.service;

import com.codegym.model.Employee;
import com.codegym.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService extends IGenericService<Employee> {
    Optional<Employee> findByUserId(Long id);
    Optional<Employee> findByCode(String code);

    Page<Employee> findAllEmployee(String code, String name, String positionId, Pageable pageable);
    boolean existsByIdEmployee(Long id);
    List<Employee> getAll();
}
