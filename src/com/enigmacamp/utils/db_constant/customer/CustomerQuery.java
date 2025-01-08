package com.enigmacamp.utils.db_constant.customer;

public enum CustomerQuery {
    INSERT("INSERT INTO customers(name,address,phone_number,birth_date) VALUES (?,?,?,?) RETURNING id"),
    GET_ALL("SELECT * FROM customers"),
    GET_BY_ID("SELECT * FROM customers WHERE id = ?"),
    GET_BY_PHONE("SELECT * FROM customers WHERE phone_number = ?");

    private final String query;
    CustomerQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
