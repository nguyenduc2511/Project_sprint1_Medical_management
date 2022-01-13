package com.codegym.service.impl;

import com.codegym.dto.ISuppliesDTO;
import com.codegym.dto.SuppliesDtoInterface;
import com.codegym.dto.TrendingSupplies;
import com.codegym.model.Supplies;
import com.codegym.repository.ISuppliesRepository;
import com.codegym.repository.SuppliesDtoRepository;
import com.codegym.service.ISuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SuppliesServiceImpl implements ISuppliesService {
    @Autowired
    private ISuppliesRepository iSuppliesRepository;

    @Autowired
    SuppliesDtoRepository suppliesDtoRepository;

    @Override
    public List<Supplies> findAll() {
        return iSuppliesRepository.findAll();
    }

    @Override
    public boolean existsByIdSupplies(Long id) {
        return iSuppliesRepository.existsById(id);
    }

    @Override
    public List<SuppliesDtoInterface> getAll() {
        return suppliesDtoRepository.getAll();
    }

    @Override
    public List<SuppliesDtoInterface> getSuppliesByTime(LocalDate startDate, LocalDate endDate) {
        return suppliesDtoRepository.getSuppliesByTime(startDate,endDate);
    }

    @Override
    public List<TrendingSupplies> getTrendingSupplies() {
        return suppliesDtoRepository.getTrendingSupplies();
    }

    @Override
    public Optional<Supplies> findById(Long id) {
        return iSuppliesRepository.findById(id);
    }

    @Override
    public void save(Supplies supplies) {
        iSuppliesRepository.save(supplies);
    }

    @Override
    public void remove(Long id) {
        iSuppliesRepository.deleteById(id);
    }

    @Override
    public Page<Supplies> findAll(Pageable pageable) {
        return iSuppliesRepository.findAll(pageable);
    }

    @Override
    public Page<ISuppliesDTO> findAllSupplies(Pageable pageable, String name, String code, String supplies_type_id) throws ParseException {
        setStatus();
        return iSuppliesRepository.findAllSupplies(pageable, "%" + name + "%", "%" + code + "%", "%" + supplies_type_id + "%");
    }

    private void setStatus() throws ParseException {
        List<Supplies> suppliesList = iSuppliesRepository.findAll();
        for (int i = 0; i < suppliesList.size(); i++) {
            if (checkStatus(suppliesList.get(i).getExpiryDate())) {
                suppliesList.get(i).setStatus(1);
                System.out.println(suppliesList.get(i));
            }
            iSuppliesRepository.save(suppliesList.get(i));
        }
    }

    private boolean checkStatus(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateA = sdf.parse(date);
        Date dateB = sdf.parse(LocalDate.now().toString());
        long getDif = dateB.getTime() - dateA.getTime();

        long getDayDif = getDif / (86400 * 1000);
        long year = getDayDif / 365;

        if (year < -1) {
            return true;
        }
        return false;
    }

}