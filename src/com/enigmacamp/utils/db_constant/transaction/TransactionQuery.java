package com.enigmacamp.utils.db_constant.transaction;

public enum TransactionQuery {
    INSERT("INSERT INTO transaction(customer_id) VALUES (?) RETURNING id");

    private final String query;
    TransactionQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
