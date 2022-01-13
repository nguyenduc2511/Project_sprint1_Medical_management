package com.codegym.controller;

import com.codegym.dto.*;
import com.codegym.model.Address;
import com.codegym.model.Customer;
import com.codegym.model.Supplies;
import com.codegym.service.ICustomerService;
import com.codegym.service.ISuppliesService;
import com.codegym.service.ISuppliesTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@EnableWebMvc
@RequestMapping("api/public/")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
public class SuppliesInformationController {

    @Autowired
    ICustomerService iCustomerService;
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    ISuppliesService iSuppliesService;
    @Autowired
    ISuppliesTypeService iSuppliesTypeService;

    /*
    Tân
     */
    @PostMapping(value="home/payment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createCustomer(@Validated @RequestBody PaymentDTO paymentDTO, BindingResult bindingResult) throws MessagingException {
        CustomerTransfer customerTransfer = paymentDTO.getCustomerTransfer();
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties( customerTransfer,customerDTO);
        List<Cart> cartList = paymentDTO.getCartList();
        Iterable<Customer> customerList = iCustomerService.findAll();
        customerDTO.setCustomers(customerList);
        new CustomerDTO().validate(customerDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        customer.setAddress(new Address(customerTransfer.getAddress().getId(),customerTransfer.getAddress().getName()));
        iCustomerService.save(customer);
        sendEmail(customer,cartList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
    private void sendEmail (Customer customer,List<Cart> cartList){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customer.getEmail());
        message.setSubject("[Thông báo] Xác nhận thanh toán thành công");
        String textMessage = "Kính gửi: Quý khách" +customer.getName()+ ".\n" +
                "Vật tư y tế CodeGym xin trân trọng gửi đến Quý khách \n" +
                "THÔNG BÁO XÁC NHẬN ĐẶT HÀNG THÀNH CÔNG ! \n"
                + "Đơn hàng của quý khách bao gồm: " ;
        int totalMoney = 0;
        for (Cart cart : cartList) {
            totalMoney += (cart.getPrice() * cart.getQuantity());
            textMessage += cart.getName() + " với số lượng: " + cart.getQuantity() + " \n";
        }
        textMessage += "Tổng giá tiền đơn hàng của quý khách là: "+ totalMoney + " !";
        message.setText(textMessage);
        // Send Message!
        this.emailSender.send(message);
    }

    @GetMapping("home/detail/{id}")
    public ResponseEntity<Supplies> findById(@PathVariable Long id) {
        Optional<Supplies> supplies = iSuppliesService.findById(id);
        if(supplies.isPresent()) {
            return new ResponseEntity<>(supplies.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    /*
    Nhật
     */
    @GetMapping("home/list/{page}")
    public ResponseEntity<Page<Supplies>> findAll(@PathVariable int page) {
        Page<Supplies> suppliesList = iSuppliesService.findAll(PageRequest.of(page, 6));
        if (suppliesList != null) {
            return new ResponseEntity<>(suppliesList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("send-email")
    public ResponseEntity<?> sendEmailTo(@RequestBody RequestMail requestMail) {
        sendMail(requestMail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void sendMail (RequestMail requestMail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(requestMail.getEmail());
        message.setSubject("[DƯỢC CODEGYM] KÍNH CHÀO QUÝ KHÁCH");
        message.setText("DEAR " +requestMail.getName()+ ", \n \n \n CTY - TNHH DƯỢC CODEGYM XIN CẢM ƠN QUÝ KHÁCH " + requestMail.getName() +
                " ĐÃ GỞI YÊU CẦU XIN THÊM THÔNG TIN VỀ CÔNG TY CHÚNG TÔI. \n" +
                " XIN QUÝ KHÁCH VUI LÒNG ĐỢI SẼ CÓ NHÂN VIÊN CỦA CÔNG TY CHÚNG TÔI LIÊN LẠC VỚI QUÝ KHÁCH THÔNG QUA EMAIL NÀY \n" +
                " XIN CẢM ƠN QUÝ KHÁCH ĐÃ QUAN TÂM ĐẾN CÔNG TY CHÚNG TÔI! \n \n" +
                " TRÂN TRỌNG");
        this.emailSender.send(message);
    }

}
