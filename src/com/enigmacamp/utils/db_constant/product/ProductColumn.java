package com.enigmacamp.utils.db_constant.product;

public enum ProductColumn {
    ID("id"),
    NAME("name"),
    PRICE("price");

    private final String columnName;

    ProductColumn(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
