package com.enigmacamp.service.impl;

import com.enigmacamp.config.DBConnector;
import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.entitiy.Product;
import com.enigmacamp.service.CustomerService;
import com.enigmacamp.utils.db_constant.customer.CustomerColumn;
import com.enigmacamp.utils.db_constant.customer.CustomerQuery;
import com.enigmacamp.utils.db_constant.product.ProductColumn;
import com.enigmacamp.utils.db_constant.product.ProductQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer create(Customer payload) {
        String birthDate = payload.getBirthDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try(
                Connection connect = DBConnector.getConnection();
                PreparedStatement preparedStatement = connect.prepareStatement(
                        CustomerQuery.INSERT.getQuery()
                )
        ){
            preparedStatement.setString(1, payload.getName());
            preparedStatement.setString(2, payload.getAddress());
            preparedStatement.setString(3, payload.getPhoneNumber());

            // Penanganan ParseException
            java.sql.Date sqlDate = null;
            try {
                // Mengubah String menjadi java.util.Date
                java.util.Date utilDate = formatter.parse(birthDate);
                // Konversi java.util.Date menjadi java.sql.Date
                sqlDate = new java.sql.Date(utilDate.getTime());
            } catch (ParseException e) {
                System.out.println("Format tanggal salah: " + e.getMessage());
            }

            preparedStatement.setDate(4, sqlDate);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Integer id = resultSet.getInt(CustomerColumn.ID.getColumnName());
            payload.setId(id);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return payload;
    }

    @Override
    public Customer update(Customer paylod) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try(
                Connection connect = DBConnector.getConnection();
                PreparedStatement preparedStatement = connect.prepareStatement(
                        CustomerQuery.GET_ALL.getQuery()
                )
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getInt(CustomerColumn.ID.getColumnName()));
                customer.setName(resultSet.getString(CustomerColumn.NAME.getColumnName()));
                customer.setAddress(resultSet.getString(CustomerColumn.ADDRESS.getColumnName()));
                customer.setPhoneNumber(resultSet.getString(CustomerColumn.PHONE_NUMBER.getColumnName()));
                customer.setBirthDate(resultSet.getString(CustomerColumn.BIRTH_DATE.getColumnName()));

                customers.add(customer);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return customers;
    }

    @Override
    public Customer get(Integer id) {
        Customer customer = new Customer();
        try(
                Connection connect = DBConnector.getConnection();
                PreparedStatement preparedStatement = connect.prepareStatement(
                        CustomerQuery.GET_BY_ID.getQuery()
                )
        ){
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getInt(CustomerColumn.ID.getColumnName()));
                customer.setName(resultSet.getString(CustomerColumn.NAME.getColumnName()));
                customer.setAddress(resultSet.getString(CustomerColumn.ADDRESS.getColumnName()));
                customer.setPhoneNumber(resultSet.getString(CustomerColumn.PHONE_NUMBER.getColumnName()));
                customer.setBirthDate(resultSet.getString(CustomerColumn.BIRTH_DATE.getColumnName()));
            } else {
                System.out.println("Product with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customer;
    }

    @Override
    public Customer getByPhone(String phoneNumber) {
        Customer customer = new Customer();
        try(
                Connection connect = DBConnector.getConnection();
                PreparedStatement preparedStatement = connect.prepareStatement(
                        CustomerQuery.GET_BY_PHONE.getQuery()
                )
        ){
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                customer = new Customer();
                customer.setId(resultSet.getInt(CustomerColumn.ID.getColumnName()));
                customer.setName(resultSet.getString(CustomerColumn.NAME.getColumnName()));
                customer.setAddress(resultSet.getString(CustomerColumn.ADDRESS.getColumnName()));
                customer.setPhoneNumber(resultSet.getString(CustomerColumn.PHONE_NUMBER.getColumnName()));
                customer.setBirthDate(resultSet.getString(CustomerColumn.BIRTH_DATE.getColumnName()));
            } else {
                System.out.println("Customer with Phone Number " + phoneNumber + " not found.");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return customer;
    }
}
