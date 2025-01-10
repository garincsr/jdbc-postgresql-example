package com.enigmacamp.service;

import com.enigmacamp.entitiy.dto.request.TransactionDetailRequest;
import com.enigmacamp.entitiy.dto.request.TransactionRequest;
import com.enigmacamp.entitiy.dto.response.AllTransactionsResponse;
import com.enigmacamp.entitiy.dto.response.TransactionDetailResponse;
import com.enigmacamp.entitiy.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    void createTransactionHandler(TransactionRequest transactionRequest);
    void createTrxDetails(int transactionId, TransactionDetailRequest transactionDetailRequest);
    TransactionResponse getTransactionById(int transactionId);
    List<AllTransactionsResponse> getAllTransactionsHandler();
    List<TransactionDetailResponse> getTransactionDetailById(int transactionId);
}

