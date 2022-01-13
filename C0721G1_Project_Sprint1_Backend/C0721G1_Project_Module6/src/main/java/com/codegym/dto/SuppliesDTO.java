package com.codegym.dto;

import com.codegym.model.Producer;
import com.codegym.model.Supplies;
import com.codegym.model.SuppliesType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class SuppliesDTO implements Validator {
    private Long id;
    private String code;

    @NotBlank(message = "Trường này không được để trống!")
    private String name;

    @Min(0)
    private Long price;

    @NotBlank(message = "Trường này không được để trống!")
    @Pattern(regexp = "^(?:19\\d{2}|20\\d{2})[-/.](?:0[1-9]|1[012])[-/.](?:0[1-9]|[12][0-9]|3[01])$",
            message = "Phải đúng định dạng: yyyy-MM-dd.")
    private String productionDate;

    @NotBlank(message = "Trường này không được để trống!")
    @Pattern(regexp = "^(?:19\\d{2}|20\\d{2})[-/.](?:0[1-9]|1[012])[-/.](?:0[1-9]|[12][0-9]|3[01])$",
            message = "Phải đúng định dạng: yyyy-MM-dd!")
    private String expiryDate;

    @NotBlank(message = "Trường này không được để trống!")
    private String introduce;

    @NotBlank(message = "Trường này không được để trống!")
    private String technicalInformation;

    private String image;

    private SuppliesType suppliesType;

    private Producer producer;

    private List<Supplies> suppliesList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTechnicalInformation() {
        return technicalInformation;
    }

    public void setTechnicalInformation(String technicalInformation) {
        this.technicalInformation = technicalInformation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SuppliesType getSuppliesType() {
        return suppliesType;
    }

    public void setSuppliesType(SuppliesType suppliesType) {
        this.suppliesType = suppliesType;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public List<Supplies> getSuppliesList() {
        return suppliesList;
    }

    public void setSuppliesList(List<Supplies> suppliesList) {
        this.suppliesList = suppliesList;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SuppliesDTO suppliesDTO = (SuppliesDTO) target;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sdf.parse(suppliesDTO.productionDate);
            Date endDate = sdf.parse(suppliesDTO.expiryDate);
            Date now = sdf.parse(String.valueOf(LocalDate.now()));
            if (startDate.compareTo(now) >= 0) {
                errors.rejectValue("productionDate", "productionDate", "Ngày sản xuất phải trước ngày hiện tại!");
            }
            if (endDate.compareTo(startDate) <= 0) {
                errors.rejectValue("productionDate", "productionDate", "Ngày sản xuất phải trước hán sử dụng!");
                errors.rejectValue("expiryDate", "expiryDate", "Hạn sử dụng phải sau ngày sản xuất !");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}