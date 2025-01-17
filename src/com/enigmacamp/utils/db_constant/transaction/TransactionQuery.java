package com.enigmacamp.utils.db_constant.transaction;

public enum TransactionQuery {
    INSERT("INSERT INTO transaction(customer_id) VALUES (?) RETURNING id"),
    GET_CUSTOMER_TRANSACTION_BY_ID("""
        SELECT t.date, c.name AS customer_name, c.phone_number
        FROM transaction t
        JOIN customers c ON t.customer_id = c.id
        WHERE t.id = ?
    """),
    GET_ALL_CUSTOMER_TRANSACTION("""
        SELECT c.name, t.customer_id, t.id, t.date, t.is_picked
        FROM transaction t
        JOIN customers c ON t.customer_id = c.id
     """);

    private final String query;
    TransactionQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
