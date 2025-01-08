package com.enigmacamp.console;

import com.enigmacamp.config.DBConnector;
import com.enigmacamp.entitiy.Product;
import com.enigmacamp.service.impl.ProductServiceImpl;
import com.enigmacamp.utils.InputHandler;

import java.sql.Connection;

public class MainConsole {
    private InputHandler inputHandler;
    private ProductConsole productConsole;

    public MainConsole(InputHandler inputHandler){
        this.inputHandler = inputHandler;
        this.productConsole = new ProductConsole(new ProductServiceImpl(), this.inputHandler);
    }

    private void showMenu(){
        System.out.println("=== Welcome to Enigma Laundry ===");
        System.out.println("1. Product Management");
        System.out.println("2. Customer Management");
        System.out.println("3. Create Transaction");
        System.out.println("4. Exit");
    }

    public void run(){
        while (true){
            this.showMenu();
            int choice = inputHandler.getInt("Pilih menu: ");
            switch (choice){
                case 1:
                    this.productConsole.run();
                    break;
                case 2:
                    System.out.println("Customer Management");
                    break;
                case 3:
                    System.out.println("Transaction");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

}
