package com.enigmacamp.service.impl;

import com.enigmacamp.config.DBConnector;
import com.enigmacamp.entitiy.Product;
import com.enigmacamp.service.ProductService;
import com.enigmacamp.utils.db_constant.product.ProductColumn;
import com.enigmacamp.utils.db_constant.product.ProductQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public Product create(Product payload) {
        try(
                Connection connect = DBConnector.getConnection();
                PreparedStatement preparedStatement = connect.prepareStatement(
                        ProductQuery.INSERT.getQuery()
                )
        ){
            preparedStatement.setString(1, payload.getName());
            preparedStatement.setInt(2,payload.getPrice());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Integer id = resultSet.getInt(ProductColumn.ID.getColumnName());
            payload.setId(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return payload;
    }

    @Override
    public Product update(Product paylod) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try(
                Connection connect = DBConnector.getConnection();
                PreparedStatement preparedStatement = connect.prepareStatement(
                        ProductQuery.GET_ALL.getQuery()
                );
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt(ProductColumn.ID.getColumnName()));
                product.setName(resultSet.getString(ProductColumn.NAME.getColumnName()));
                product.setPrice(resultSet.getInt(ProductColumn.PRICE.getColumnName()));
                products.add(product);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return products;
    }

    @Override
    public Product get(Integer id) {
        Product product = new Product();
        try(
                Connection connect = DBConnector.getConnection();
                PreparedStatement preparedStatement = connect.prepareStatement(
                        ProductQuery.GET_BY_ID.getQuery()
                )
        ){
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt(ProductColumn.ID.getColumnName()));
                product.setName(resultSet.getString(ProductColumn.NAME.getColumnName()));
                product.setPrice(resultSet.getInt(ProductColumn.PRICE.getColumnName()));
            } else {
                System.out.println("Product with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return product;
    }

    @Override
    public Boolean delete(Integer id) {
        Boolean status = false;
        try(
                Connection connect = DBConnector.getConnection();
                PreparedStatement preparedStatement = connect.prepareStatement(
                        ProductQuery.DELETE.getQuery()
                )
        ){
            preparedStatement.setInt(1, id);
            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted > 0){
                return true;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return status;
    }
}
