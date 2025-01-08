package com.enigmacamp.console;

import com.enigmacamp.entitiy.Product;
import com.enigmacamp.service.ProductService;
import com.enigmacamp.service.impl.ProductServiceImpl;
import com.enigmacamp.utils.InputHandler;

import java.util.List;
import java.util.Scanner;

public class ProductConsole {
    private ProductService service;
    private InputHandler inputHandler;

    public ProductConsole(ProductService service, InputHandler inputHandler){
        this.service = service;
        this.inputHandler = inputHandler;
    }

    private void showMenu(){
        System.out.println("=== Product Management ===");
        System.out.println("1. Add Product");
        System.out.println("2. List Product");
        System.out.println("3. Update Product");
        System.out.println("4. Back");
    }

    public void run(){
        while (true){
            this.showMenu();
            int choice = inputHandler.getInt("Pilih menu: ");
            switch (choice){
                case 1:
                    this.createNewProduct();
                    break;
                case 2:
                    this.getAllProducts();
                    break;
                case 3:
                    System.out.println("Update Product");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    private void createNewProduct(){
        String productName = this.inputHandler.getString("Name: ");
        Integer productPrice = this.inputHandler.getInt("Price : ");
        Product result = this.service.create(new Product(productName,productPrice));
        System.out.println("Success Create New Project : " + result);
    }

    private void getAllProducts(){
        List<Product> products = service.getAll();

        if (products.isEmpty()){
            System.out.println("No Products Available");
            return;
        }
        // header
        System.out.printf("|%-5s |%-20s |%-10s\n", "ID","Name","Price");
        System.out.println("-------------------------------------------");
        for (Product product : products){
            System.out.printf("|%-5s |%-20s |Rp%,d%n\n",
                    product.getId(),
                    product.getName(),
                    product.getPrice()
            );
        }
    }
}
