package com.enigmacamp.service.impl;

import com.enigmacamp.config.DBConnector;
import com.enigmacamp.entitiy.dto.request.TransactionDetailRequest;
import com.enigmacamp.entitiy.dto.request.TransactionRequest;
import com.enigmacamp.service.CustomerService;
import com.enigmacamp.service.ProductService;
import com.enigmacamp.service.TransactionService;
import com.enigmacamp.utils.db_constant.product.ProductColumn;
import com.enigmacamp.utils.db_constant.transaction.TransactionColumn;
import com.enigmacamp.utils.db_constant.transaction.TransactionQuery;
import com.enigmacamp.utils.db_constant.trx_detail.TrxQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();

            int transactionId = resultSet.getInt(TransactionColumn.ID.getColumnName());

            transactionRequest.getTrxDetails()
                    .forEach(trx -> {
                        createTrxDetails(transactionId,trx);
                    });
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

                preparedStatement.executeQuery();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
    }
}
