package com.enigmacamp.utils.db_constant.transaction;

public enum TransactionColumn {
    ID("id"),
    CUSTOMER_ID("customer_id"),
    DATE("date"),
    IS_PICKED("is_Picked");

    private final String columnName;

    TransactionColumn(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
