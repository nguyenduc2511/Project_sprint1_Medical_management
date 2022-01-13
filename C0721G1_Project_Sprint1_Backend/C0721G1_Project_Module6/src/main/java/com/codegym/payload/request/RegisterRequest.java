package com.codegym.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/*
Creator: PhuocPD
 */
public class RegisterRequest{
    @NotBlank(message = "Vui lòng nhập Tên đăng nhập")
    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){2,8}[a-zA-Z0-9]$",
            message = "Vui lòng nhập đúng định dạng của Tên đăng nhập")
    private String username;

    @NotBlank(message = "Vui lòng nhập Mật khẩu")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,10}$",
            message = "Vui lòng nhập đúng định dạng của Mật khẩu")
    private String password;
    private String role;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
