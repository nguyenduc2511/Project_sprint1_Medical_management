package com.codegym.controller;

import com.codegym.dto.*;
import com.codegym.model.Producer;
import com.codegym.model.Supplies;
import com.codegym.model.SuppliesType;
import com.codegym.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class SuppliesController {
    @Autowired
    private ISuppliesService iSuppliesService;
    @Autowired
    private ISuppliesTypeService iSuppliesTypeService;
    @Autowired
    private IProducerService iProducerService;

    @Autowired
    private IFinancialService financialService;

    @Autowired
    private IPotentialCustomerService iPotentialCustomerService;

    /*
    Huy
     */
    @GetMapping("admin/supplies/suppliestype")
    public ResponseEntity<List<SuppliesType>> getSuppliesTypeList() {
        List<SuppliesType> suppliesTypeList = iSuppliesTypeService.getAll();
        return new ResponseEntity<>(suppliesTypeList, HttpStatus.OK);
    }

    /*
    Huy
     */
    @GetMapping("admin/supplies/producer")
    public ResponseEntity<List<Producer>> getProducerList() {
        List<Producer> producerList = iProducerService.getAll();
        return new ResponseEntity<>(producerList, HttpStatus.OK);
    }

    /*
    Huy
     */
    @GetMapping("admin/supplies")
    public ResponseEntity<Page<ISuppliesDTO>> findAllSupplies(@RequestParam String code,
                                                              @RequestParam String name,
                                                              @RequestParam String suppliesType,
                                                              @RequestParam int page,
                                                              @RequestParam int size
    ) throws ParseException {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "code");

        Page<ISuppliesDTO> suppliesPage = iSuppliesService.findAllSupplies(pageable, name, code, suppliesType);
        if (suppliesPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(suppliesPage, HttpStatus.OK);
    }


    /*
    Huy
     */
    @DeleteMapping("admin/supplies/{id}")
    public ResponseEntity<HttpStatus> deleteSupplies(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (iSuppliesService.existsByIdSupplies(id)) {
            iSuppliesService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*
    Thanh
     */
    @GetMapping("admin/supplies/code")
    public ResponseEntity<Supplies> suppliesCode() {
        Supplies supplies = new Supplies();
        supplies.setCode(getCode());
        return new ResponseEntity<>(supplies, HttpStatus.OK);
    }

    /*
    Thanh
     */
    @GetMapping("admin/supplies/findById/{id}")
    public ResponseEntity<Supplies> getSupplies(@PathVariable Long id) {
        Optional<Supplies> supplies = iSuppliesService.findById(id);
        if (supplies.isPresent()) {
            return new ResponseEntity<>(supplies.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*
    Thanh
     */
    @PostMapping("admin/supplies/create")
    public ResponseEntity<?> createSupplies(@Valid @RequestBody SuppliesDTO suppliesDTO, BindingResult bindingResult) {
        List<Supplies> supplies = iSuppliesService.findAll();
        suppliesDTO.setSuppliesList(supplies);
        suppliesDTO.validate(suppliesDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            suppliesDTO.setCode(getCode());
            Supplies supplies1 = new Supplies();
            BeanUtils.copyProperties(suppliesDTO, supplies1);
            iSuppliesService.save(supplies1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /*
    Thanh
     */
    @PatchMapping("admin/supplies/edit")
    public ResponseEntity<HttpStatus> editSupplies(@Valid @RequestBody SuppliesDTO suppliesDTO, BindingResult bindingResult1) {
        List<Supplies> suppliesList = (List<Supplies>) iSuppliesService.findAll();
        suppliesDTO.setSuppliesList(suppliesList);
        suppliesDTO.validate(suppliesDTO, bindingResult1);
        if (bindingResult1.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Supplies supplies = new Supplies();
            BeanUtils.copyProperties(suppliesDTO, supplies);
            supplies.setId(suppliesDTO.getId());
            iSuppliesService.save(supplies);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    /*
    Thanh
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    private String getCode() {
        String code = "MVT-";
        List<Integer> codeList = new ArrayList<>();
        List<Supplies> suppliesList = iSuppliesService.findAll();
        for (Supplies supplies : suppliesList) {
            String[] arrayCode = supplies.getCode().split("-");
            codeList.add(Integer.parseInt(arrayCode[1]));
        }
        Collections.sort(codeList);
        int index = 0;
        for (int i = 0; i < codeList.size(); i++) {
            if (i == codeList.size()-1) {
                index = codeList.size();
                break;
            }
            if (codeList.get(i + 1) - codeList.get(i) >= 2) {
                index = i + 1;
                break;
            }
        }
        if (index > 998) {
            return code+= (index + 1);
        }
        if (index > 98) {
            return code += "0" + (index + 1);
        }
        if (index > 8) {
            return code += "00" + (index + 1);
        }
        if (index > 0) {
            return code += "000" + (index + 1);
        }
        return code;
    }

    /*
    Bình
     */
    @GetMapping("user/stats/supplies-stats")
    public ResponseEntity<List<SuppliesDtoInterface>> getSuppliesStats() {
        List<SuppliesDtoInterface> suppliesDtoInterfaceList = iSuppliesService.getAll();
        if (!suppliesDtoInterfaceList.isEmpty()) {
            return new ResponseEntity<>(suppliesDtoInterfaceList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("user/stats/supplies-stats/trending-supplies")
    public ResponseEntity<List<TrendingSupplies>> getTrendingSupplies(){
        List<TrendingSupplies> trendingSupplies = iSuppliesService.getTrendingSupplies();
        if(!trendingSupplies.isEmpty()){
            return new ResponseEntity<>(trendingSupplies, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("user/stats/financial-stats")
    public ResponseEntity<FinancialStatsDto> getFinancialStats() {
        FinancialStatsDto financialStatsDto = new FinancialStatsDto();
        financialStatsDto.setIncome(financialService.getIncome());
        financialStatsDto.setImportMoney(financialService.getImport());
        financialStatsDto.setCancelled(financialService.getCancelled());
        financialStatsDto.setReturnMoney(financialService.getReturn());
        financialStatsDto.setRefund(financialService.getRefund());
        if (financialStatsDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(financialStatsDto, HttpStatus.OK);
    }

    @GetMapping("user/stats/financial-stats/{date}")
    public ResponseEntity<FinancialStatsDto> getFinancialStatsByTime(@PathVariable String date) {

        String[] str = date.split("-");
        String newDate = str[0]+"-"+str[1];

        FinancialStatsDto financialStatsDto = new FinancialStatsDto();
        financialStatsDto.setIncome(financialService.getMonthSales(newDate));
        financialStatsDto.setImportMoney(financialService.getMonthSales(newDate));
        financialStatsDto.setRefund(financialService.getMonthRefund(newDate));
        financialStatsDto.setCancelled(financialService.getMonthCancelled(newDate));
        financialStatsDto.setReturnMoney(financialService.getMonthReturn(newDate));
        if (financialStatsDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(financialStatsDto, HttpStatus.OK);
    }

    @GetMapping("user/stats/potential-customer")
    public ResponseEntity<List<PotentialCustomerDto>> getPotentialCustomerStats() {

        List<PotentialCustomerDto> potentialDtoList = iPotentialCustomerService.getAll();
        if (!potentialDtoList.isEmpty()) {

            return new ResponseEntity<>(potentialDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("user/stats/potential-customer/fetch")
    public ResponseEntity<List<PotentialCustomerDto>> getPotentialCustomerByTime(@RequestParam String startDate,
                                                                                 @RequestParam String endDate) {
        LocalDate ld = LocalDate.parse(startDate);
        LocalDate ld1 = LocalDate.parse(endDate);
        List<PotentialCustomerDto> potentialDtoList = iPotentialCustomerService.getPotentialCustomerByTime(ld, ld1);
        if (!potentialDtoList.isEmpty()) {
            return new ResponseEntity<>(potentialDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}