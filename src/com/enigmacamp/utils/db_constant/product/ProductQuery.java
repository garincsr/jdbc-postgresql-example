package com.enigmacamp.utils.db_constant.product;

public enum ProductQuery {
    INSERT("INSERT INTO products(name,price) VALUES (?,?) RETURNING id"),
    GET_ALL("SELECT * FROM products"),
    GET_BY_ID("SELECT * FROM Products WHERE id = ?"),
    UPDATE("UPDATE products WHERE id = ?"),
    DELETE("DELETE FROM products WHERE id = ?");

    private final String query;
    ProductQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
