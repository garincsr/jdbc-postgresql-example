package com.enigmacamp.utils.db_constant.product;

public enum ProductQuery {
    INSERT("INSERT INTO products(name,price) VALUES (?,?) RETURNING id"),
    GET_ALL("SELECT * FROM products");

    private final String query;
    ProductQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
