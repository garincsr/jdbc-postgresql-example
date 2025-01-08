package com.enigmacamp.utils.db_constant.trx_detail;

public enum TrxColumn {
    ID("id"),
    TRANSACTION_ID("transaction_id"),
    PRODUCT_ID("product_id"),
    PRICE("price"),
    QUANTITY("qty");

    private final String columnName;

    TrxColumn(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
