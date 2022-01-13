package com.codegym.dto;

import com.codegym.model.Employee;
import com.codegym.model.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserDto {
    private Long id;

    private String username;
    @NotBlank
    @Size(min = 3 , max = 10 , message = "Mật khẩu phải từ 3-10 kí tự")
    @Pattern(regexp = "^[0-9a-zA-Z]+$" , message = "Mật khẩu chỉ được chứa chữ cái thường , chữ cái in hoa và số ")
    private String password;
    private Employee employee;
    private Set<Role> roles;

    public UserDto(Long id, String username, String password, Employee employee, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.employee = employee;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
