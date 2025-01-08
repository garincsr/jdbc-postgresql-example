package com.enigmacamp.entitiy.dto.response;

import java.util.List;

public class TransactionResponse {
    private String date;
    private String customerName;
    private String phoneNumber;
    private List<TransactionDetailResponse> trxDetails;
    private Integer total;
}

