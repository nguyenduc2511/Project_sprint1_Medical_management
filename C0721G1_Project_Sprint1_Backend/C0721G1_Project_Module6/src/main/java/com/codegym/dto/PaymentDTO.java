package com.codegym.dto;

import java.util.List;

public class PaymentDTO {
    private List<Cart> cartList;
    private CustomerTransfer customerTransfer;

    public PaymentDTO() {
    }

    public PaymentDTO(List<Cart> cartList, CustomerTransfer customerTransfer) {
        this.cartList = cartList;
        this.customerTransfer = customerTransfer;
    }

    public CustomerTransfer getCustomerTransfer() {
        return customerTransfer;
    }

    public void setCustomerTransfer(CustomerTransfer customerTransfer) {
        this.customerTransfer = customerTransfer;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

}
