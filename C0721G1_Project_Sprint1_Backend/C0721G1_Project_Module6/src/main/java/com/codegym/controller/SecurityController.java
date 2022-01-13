//package com.codegym.controller;
//
//import com.codegym.jwt.JwtUtility;
//import com.codegym.model.Employee;
//import com.codegym.model.Role;
//import com.codegym.model.User;
//import com.codegym.payload.request.LoginRequest;
//import com.codegym.payload.request.RegisterRequest;
//import com.codegym.payload.response.JwtResponse;
//import com.codegym.payload.response.MessageResponse;
//import com.codegym.service.impl.EmployeeServiceImpl;
//import com.codegym.service.impl.RoleServiceImpl;
//import com.codegym.service.impl.UserDetailsImpl;
//import com.codegym.service.impl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.*;
//import java.util.stream.Collectors;
//
///*
//Creator: PhuocPD
// */
//@RestController
//@RequestMapping("api/public")
//@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
//public class SecurityController {
//    @Autowired
//    private JwtUtility jwtUtility;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserServiceImpl userServiceImpl;
//    @Autowired
//    private RoleServiceImpl roleServiceImpl;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private EmployeeServiceImpl employeeServiceImpl;
//
//    private static final String ROLE_ADMIN = "ROLE_ADMIN";
//    private static final String ROLE_USER = "ROLE_USER";
//    private static final String ROLE_ERROR = "Không tìm thấy quyền này";
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//        List<String> roles1 = new ArrayList<>();
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtility.generateJwtToken(loginRequest.getUsername());
//        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
//        Optional<User> user = userServiceImpl.findByUsername(loginRequest.getUsername());
//        if (user.isPresent()) {
//            Optional<Employee> employee = employeeServiceImpl.findByUserId(user.get().getId());
//            if (employee.isPresent()) {
//                if (roles.get(0).equals(ROLE_ADMIN)) {
//                    roles1.add(ROLE_USER);
//                    roles1.add(ROLE_ADMIN);
//                    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles1, employee.get()));
//                }
//                return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles, employee.get()));
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//
//    @GetMapping("/register/{code}")
//    public ResponseEntity<?> getRegisterUser(@PathVariable String code) {
//        Optional<Employee> employeeCurrent = employeeServiceImpl.findByCode(code);
//        if (!employeeCurrent.isPresent()) {
//            return ResponseEntity.ok(1);
//        }
//
//        if (employeeCurrent.get().getUser() != null) {
//            List<Long> roleIdList = userServiceImpl.findRoleByUserId(employeeCurrent.get().getUser().getId());
//            String role = null;
//            if (roleIdList.size() == 2) {
//                role = "admin";
//            } else {
//                role = "user";
//            }
//            return ResponseEntity.ok(new RegisterRequest(employeeCurrent.get().getUser().getUsername(), employeeCurrent.get().getUser().getPassword(), role));
//        } else {
//            return ResponseEntity.ok(2);
//        }
//    }
//
//    @PostMapping("/register/{code}")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest, @PathVariable String code, BindingResult bindingResult) {
//        if (userServiceImpl.existsByUsername(registerRequest.getUsername())) {
//            return new ResponseEntity<>("Tên đăng nhập này đã tồn tại",HttpStatus.BAD_REQUEST);
//        }
//        if (bindingResult.hasFieldErrors()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        User user = new User(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()));
//        String stringRoles = registerRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//        if (stringRoles == null) {
//            Role userRole = roleServiceImpl.findByName(ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException(ROLE_ERROR));
//            roles.add(userRole);
//        } else {
//            switch (stringRoles) {
//                case "admin":
//                    Role adminRole = roleServiceImpl.findByName(ROLE_ADMIN)
//                            .orElseThrow(() -> new RuntimeException(ROLE_ERROR));
//                    Role userRole = roleServiceImpl.findByName(ROLE_USER)
//                            .orElseThrow(() -> new RuntimeException(ROLE_ERROR));
//                    roles.add(adminRole);
//                    roles.add(userRole);
//                    break;
//                case "user":
//                    Role userRole1 = roleServiceImpl.findByName(ROLE_USER)
//                            .orElseThrow(() -> new RuntimeException(ROLE_ERROR));
//                    roles.add(userRole1);
//                    break;
//                default:
//                    break;
//            }
//        }
//        user.setRoles(roles);
//        userServiceImpl.save(user);
//        Optional<User> user1 = userServiceImpl.findByUsername(user.getUsername());
//        if (user1.isPresent()) {
//            Optional<Employee> employeeCurrent = employeeServiceImpl.findByCode(code);
//            if (employeeCurrent.isPresent()) {
//                employeeCurrent.get().setUser(user1.get());
//                employeeServiceImpl.save(employeeCurrent.get());
//                return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công"));
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//
//    @PatchMapping("/register-edit-password/{code}")
//    public ResponseEntity<?> registerEditPassword(@Valid @RequestBody RegisterRequest registerRequest, @PathVariable String code, BindingResult bindingResult) {
//        if (bindingResult.hasFieldErrors()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        String password = passwordEncoder.encode(registerRequest.getPassword());
//        Optional<Employee> employeeCurrent = employeeServiceImpl.findByCode(code);
//        if (employeeCurrent.isPresent()) {
//            employeeCurrent.get().getUser().setPassword(password);
//            userServiceImpl.save(employeeCurrent.get().getUser());
//            return ResponseEntity.ok(new MessageResponse("Cập nhật mật khẩu thành công"));
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
//}
