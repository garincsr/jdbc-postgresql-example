package com.enigmacamp.service;

import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.entitiy.Product;

import java.util.List;

public interface CustomerService {
    Customer create(Customer payload);
    Customer update(Customer paylod);
    List<Customer> getAll();
    Customer get(Integer id);
    Customer getByPhone(String phoneNumber);
}
