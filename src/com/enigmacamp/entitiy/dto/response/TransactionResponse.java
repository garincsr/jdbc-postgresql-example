package com.enigmacamp.entitiy.dto.response;

import java.util.List;

public class TransactionResponse {
    private String date;
    private String customerName;
    private String phoneNumber;
    private List<TransactionDetailResponse> trxDetails;
    private Integer total;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<TransactionDetailResponse> getTrxDetails() {
        return trxDetails;
    }

    public void setTrxDetails(List<TransactionDetailResponse> trxDetails) {
        this.trxDetails = trxDetails;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}

