package com.enigmacamp.entitiy;

public class Transaction {
    private Integer id;
    private Integer customerId;
    private String date;
    private Boolean isPicked;

    public Transaction(Integer customerId, String date, Boolean isPicked) {
        this.customerId = customerId;
        this.date = date;
        this.isPicked = isPicked;
    }

    public Transaction() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getPicked() {
        return isPicked;
    }

    public void setPicked(Boolean picked) {
        isPicked = picked;
    }
}
