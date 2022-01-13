package com.codegym.repository;

import com.codegym.dto.ISuppliesDTO;
import com.codegym.model.Supplies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ISuppliesRepository extends JpaRepository<Supplies, Long> {
    @Query(value = "select id ,`code`, expiry_date, `name`, price, production_date, producer_id, supplies_type_id, status " +
            "from `supplies` " +
            "where supplies.`name` like :name and supplies.`code` like :code " +
            "and supplies.supplies_type_id like :supplies_type_id", nativeQuery = true)
    Page<ISuppliesDTO> findAllSupplies(Pageable pageable, @Param("name") String name, @Param("code") String code, @Param("supplies_type_id") String supplies_type_id);
}
