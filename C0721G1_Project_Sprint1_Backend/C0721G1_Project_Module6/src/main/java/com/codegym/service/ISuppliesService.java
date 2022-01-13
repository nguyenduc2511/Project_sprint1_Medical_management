package com.codegym.service;


import com.codegym.dto.ISuppliesDTO;
import com.codegym.dto.SuppliesDtoInterface;
import com.codegym.dto.TrendingSupplies;
import com.codegym.model.Supplies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

import java.time.LocalDate;
import java.util.List;

public interface ISuppliesService extends IGenericService<Supplies> {
    Page<Supplies> findAll(Pageable pageable);

    Page<ISuppliesDTO> findAllSupplies(Pageable pageable, String name, String code, String supplies_type_id) throws ParseException;


    List<Supplies> findAll();

    boolean existsByIdSupplies(Long id);


    List<SuppliesDtoInterface> getAll();

    List<SuppliesDtoInterface> getSuppliesByTime(LocalDate startDate, LocalDate endDate);

    List<TrendingSupplies> getTrendingSupplies();

}
