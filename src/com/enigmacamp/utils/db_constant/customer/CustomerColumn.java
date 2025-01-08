package com.enigmacamp.utils.db_constant.customer;

public enum CustomerColumn {
    ID("id"),
    NAME("name"),
    ADDRESS("address"),
    PHONE_NUMBER("phone_number"),
    BIRTH_DATE("birth_date");

    private final String columnName;

    CustomerColumn(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
