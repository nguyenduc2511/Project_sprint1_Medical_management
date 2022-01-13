package com.codegym.controller;

import com.codegym.dto.EmployeeDto;;
import com.codegym.model.Employee;
import com.codegym.model.Position;
import com.codegym.model.User;
import com.codegym.service.IEmployeeService;
import com.codegym.service.IPositionService;
import com.codegym.service.IUserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private IUserService iUserService;



//    @Autowired
//    private PasswordEncoder passwordEncoder;

    /*
    Creator: SangDN
     */
    @GetMapping("/admin/employee")
    public ResponseEntity<Page<Employee>> findAllEmployee(@RequestParam String code,
                                             @RequestParam String name,
                                             @RequestParam String positionId,
                                             @RequestParam int page,
                                             @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC,"name");

        Page<Employee> employeePage = employeeService.findAllEmployee(code, name , positionId, pageable);
        if(employeePage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeePage, HttpStatus.OK);
    }

    /*
    Creator: SangDN
     */
    @DeleteMapping("/admin/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(employeeService.existsByIdEmployee(id)) {
            employeeService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/user/position")
    public ResponseEntity<List<Position>> findAllPosition() {
        List<Position> positionList = positionService.findAll();
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }

    @PostMapping("/admin/employee/create")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult) {
        List<Employee> employees = employeeService.getAll();
        employeeDto.setEmployeeList(employees);
        employeeDto.validate(employeeDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            employeeDto.setCode(getCode());
            String name = employeeDto.getName().trim();
            String address = employeeDto.getName().trim();
            employeeDto.setName(name);
            employeeDto.setAddress(address);
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDto, employee);
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    private String getCode() {
        String code = "MNV-";
        List<Integer> codeList = new ArrayList<>();
        List<Employee> employeeList = employeeService.getAll();
        if (employeeList.isEmpty()) {
            return ("MNV-0001");
        } else {
            for (Employee employee : employeeList) {
                String[] arrayCode = employee.getCode().split("-");
                codeList.add(Integer.parseInt(arrayCode[1]));
            }
            Collections.sort(codeList);
            int index = 0;
            for (int i = 0; i < codeList.size(); i++) {
                if (i == codeList.size() - 1) {
                    index = codeList.size();
                    break;
                }
                if (codeList.get(i + 1) - codeList.get(i) >= 2) {
                    index = i + 1;
                    break;
                }
            }
            if (index > 999) {
                code += (index + 1);
            } else if (index > 99) {
                code += "0" + (index + 1);
            } else if (index > 9) {
                code += "00" + (index + 1);
            } else if (index > 0) {
                code += "000" + (index + 1);
            }
            return (code);
        }
    }

    /*
    Đức
     */
    @PatchMapping("/admin/employee/update")
    public ResponseEntity<HttpStatus> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult) {
        List<Employee> employees = employeeService.getAll();
        employeeDto.setEmployeeList(employees);
        employeeDto.validate(employeeDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            String name = employeeDto.getName().trim();
            String address = employeeDto.getName().trim();
            employeeDto.setName(name);
            employeeDto.setAddress(address);
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDto, employee);
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    /*
    Đức
     */
    @GetMapping("/admin/employee/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Employee employee = employeeService.findById(id).get();
            EmployeeDto employeeDto = new EmployeeDto();
            BeanUtils.copyProperties(employee, employeeDto);
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin/employee/code")
    public ResponseEntity<Employee> getEmployeeCode() {
        Employee employeeC = new Employee();
        employeeC.setCode(getCode());
        return new ResponseEntity<>(employeeC, HttpStatus.OK);
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

    /*
    Tính
     */
    @GetMapping("/user/employee/detail/{id}")
    public ResponseEntity<Employee> findDetailEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PatchMapping("/user/change-password/{id}/{oldPassword}/{newPassword}")
//    public ResponseEntity<?> changePassword(@PathVariable Long id, @PathVariable String oldPassword, @PathVariable String newPassword) {
//        if (oldPassword.equals(newPassword)) {
//            return ResponseEntity.ok(1);
//        }
//        Optional<Employee> employeeCurrent = employeeService.findById(id);
//        if (employeeCurrent.isPresent()) {
//            if (passwordEncoder.matches(oldPassword, employeeCurrent.get().getUser().getPassword())) {
//                employeeCurrent.get().getUser().setPassword(passwordEncoder.encode(newPassword));
//                iUserService.save(employeeCurrent.get().getUser());
//                return new ResponseEntity<>(HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }

    /*
    Hùng
     */
    @PatchMapping("/user/employee/edit-detail/update/{id}")
    public ResponseEntity<?> updateDetailEmployee(@Valid @RequestBody EmployeeDto employeeDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDTO, employee);
            employee.setId(employeeDTO.getId());
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /*
    Hùng
     */
    @GetMapping("/user/employee/edit-detail/{id}")
    public ResponseEntity<?> searchDetailEmployeeById(@PathVariable(name = "id") Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user/all-user")
    public ResponseEntity<Iterable<User>> getAllUser() {
        Iterable<User> userList = iUserService.findAll();
        return new ResponseEntity<Iterable<User>>(userList, HttpStatus.OK);
    }

//    @PatchMapping("/user/change-password/{id}/{newPassword}")
//    public ResponseEntity<?> changePassword(@PathVariable Long id, @PathVariable String newPassword) {
//        Optional<Employee> employeeCurrent = employeeService.findById(id);
//        if (employeeCurrent.isPresent()) {
//            employeeCurrent.get().getUser().setPassword(passwordEncoder.encode(newPassword));
//                iUserService.save(employeeCurrent.get().getUser());
//                return new ResponseEntity<>(HttpStatus.OK);
//            }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//    }

}
