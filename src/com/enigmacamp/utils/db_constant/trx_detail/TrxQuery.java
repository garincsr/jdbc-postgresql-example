package com.enigmacamp.utils.db_constant.trx_detail;

public enum TrxQuery {
    INSERT("INSERT INTO trx_details(transaction_id,products_id,price,qty) VALUES (?,?,?,?) RETURNING id");

    private final String query;
    TrxQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
