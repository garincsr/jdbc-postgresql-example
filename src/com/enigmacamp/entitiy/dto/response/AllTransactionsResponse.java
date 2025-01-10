package com.enigmacamp.entitiy.dto.response;

public class AllTransactionsResponse {
    private String customerName;
    private Integer TransactionCustomerId;
    private Integer transactionId;
    private String transactionDate;
    private String transactionIsPicked;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getTransactionCustomerId() {
        return TransactionCustomerId;
    }

    public void setTransactionCustomerId(Integer transactionCustomerId) {
        TransactionCustomerId = transactionCustomerId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionIsPicked() {
        return transactionIsPicked;
    }

    public void setTransactionIsPicked(String transactionIsPicked) {
        this.transactionIsPicked = transactionIsPicked;
    }
}
