package com.enigmacamp.service.impl;

import com.enigmacamp.config.DBConnector;
import com.enigmacamp.entitiy.dto.request.TransactionDetailRequest;
import com.enigmacamp.entitiy.dto.request.TransactionRequest;
import com.enigmacamp.entitiy.dto.response.TransactionDetailResponse;
import com.enigmacamp.entitiy.dto.response.TransactionResponse;
import com.enigmacamp.service.ProductService;
import com.enigmacamp.service.TransactionService;
import com.enigmacamp.utils.db_constant.transaction.TransactionColumn;
import com.enigmacamp.utils.db_constant.transaction.TransactionQuery;
import com.enigmacamp.utils.db_constant.trx_detail.TrxQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    ProductService productService;

    public TransactionServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void createTransaction(TransactionRequest transactionRequest) {
        try(
                Connection connect = DBConnector.getConnection();
                PreparedStatement preparedStatement = connect.prepareStatement(
                        TransactionQuery.INSERT.getQuery()
                )
        ){
            preparedStatement.setInt(1, transactionRequest.getCustomerId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int transactionId = resultSet.getInt(TransactionColumn.ID.getColumnName());

            //REQUEST
            transactionRequest.getTrxDetails()
                    .forEach(trx -> {
                        createTrxDetails(transactionId,trx);
                    });

            //RESPONSE
            TransactionResponse transactionResponse = getTransactionById(transactionId);

            //PRINT STRUK
            transactionReceipt(transactionResponse);

        }catch (SQLException e){
            System.out.print(e.getMessage());
        }
    }

    @Override
    public void createTrxDetails(int transactionId, TransactionDetailRequest transactionDetailRequest) {
            try(
                    Connection connect = DBConnector.getConnection();
                    PreparedStatement preparedStatement = connect.prepareStatement(
                            TrxQuery.INSERT.getQuery()
                    )
            ){
                preparedStatement.setInt(1, transactionId);
                preparedStatement.setInt(2, transactionDetailRequest.getProductId());
                preparedStatement.setInt(3, this.productService.get(transactionDetailRequest.getProductId()).getPrice());
                preparedStatement.setInt(4, transactionDetailRequest.getQty());

                preparedStatement.executeUpdate();
                System.out.println();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
    }

    @Override
    public TransactionResponse getTransactionById(int transactionId) {
        TransactionResponse transactionResponse = null;
        try (
                Connection connect = DBConnector.getConnection();
                PreparedStatement preparedStatement = connect.prepareStatement(
                        TransactionQuery.GET_CUSTOMER_TRANSACTION_BY_ID.getQuery()
                )
        ) {
            preparedStatement.setInt(1, transactionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                transactionResponse = new TransactionResponse();
                transactionResponse.setDate(resultSet.getString("date"));
                transactionResponse.setCustomerName(resultSet.getString("customer_name"));
                transactionResponse.setPhoneNumber(resultSet.getString("phone_number"));

                // Panggil getTransactionDetailById
                List<TransactionDetailResponse> trxDetails = getTransactionDetailById(transactionId);
                transactionResponse.setTrxDetails(trxDetails);

                // Hitung Total
                Integer total = trxDetails.stream()
                        .mapToInt(TransactionDetailResponse::getSubTotal)
                        .sum();
                transactionResponse.setTotal(total);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transactionResponse;

    }

    @Override
    public List<TransactionDetailResponse> getTransactionDetailById(int transactionId) {
        List<TransactionDetailResponse> transactionDetails = new ArrayList<>();

        try (
                Connection connect = DBConnector.getConnection();
                PreparedStatement preparedStatement = connect.prepareStatement(
                        TrxQuery.GET_TRX_DETAIL_BY_ID.getQuery()
                )
        ) {
            preparedStatement.setInt(1, transactionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TransactionDetailResponse detail = new TransactionDetailResponse();
                detail.setProductName(resultSet.getString("product_name"));
                detail.setProductPrice(resultSet.getInt("product_price"));
                detail.setQty(resultSet.getInt("qty"));
                detail.setSubTotal(resultSet.getInt("sub_total"));

                transactionDetails.add(detail);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transactionDetails;
    }

    public void transactionReceipt(TransactionResponse transactionResponse){
        System.out.println("=== Transaction Receipt ===");
        System.out.println("Date          : " + transactionResponse.getDate());
        System.out.println("Customer Name : " + transactionResponse.getCustomerName());
        System.out.println("Phone Number  : " + transactionResponse.getPhoneNumber());
        System.out.println("=====================================");
        System.out.printf("%-20s %-10s %-5s %-10s%n", "Product Name", "Price", "Qty", "Subtotal");
        System.out.println("-------------------------------------");

        for (TransactionDetailResponse detail : transactionResponse.getTrxDetails()) {
            System.out.printf("%-20s %-10d %-5d %-10d%n",
                    detail.getProductName(),
                    detail.getProductPrice(),
                    detail.getQty(),
                    detail.getSubTotal());
        }

        System.out.println("-------------------------------------");
        System.out.println("Total         : " + transactionResponse.getTotal());
        System.out.println("=====================================");
    }
}
