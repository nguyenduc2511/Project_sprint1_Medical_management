package com.codegym.repository;

import com.codegym.model.Employee;
import com.codegym.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUserId(Long id);
    Optional<Employee> findByCode(String code);

    @Query(value = "SELECT * FROM employee WHERE code LIKE :code AND `name` LIKE :name AND position_id LIKE :position_id", nativeQuery = true)
    Page<Employee> findAllEmployee(@Param("code") String code, @Param("name") String name, @Param("position_id") String position_id, Pageable pageable);
}
