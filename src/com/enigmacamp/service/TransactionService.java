package com.enigmacamp.service;

import com.enigmacamp.entitiy.dto.request.TransactionDetailRequest;
import com.enigmacamp.entitiy.dto.request.TransactionRequest;

import java.util.List;

public interface TransactionService {
    void createTransaction(TransactionRequest transactionRequest);
    void createTrxDetails(int transactionId, TransactionDetailRequest transactionDetailRequest);
}

