package com.enigmacamp.utils.db_constant.trx_detail;

public enum TrxQuery {
    INSERT("INSERT INTO trx_details (transaction_id,product_id,price,qty) VALUES (?,?,?,?)"),
    GET_TRX_DETAIL_BY_ID("""
        SELECT p.name AS product_name, p.price AS product_price, td.qty,
               (p.price * td.qty) AS sub_total
        FROM trx_details td
        JOIN products p ON td.product_id = p.id
        WHERE td.transaction_id = ?
    """);

    private final String query;
    TrxQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
