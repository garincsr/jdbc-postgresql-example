package com.enigmacamp.console;

import com.enigmacamp.service.TransactionService;
import com.enigmacamp.utils.InputHandler;

public class TransactionConsole {
    TransactionService service;
    InputHandler inputHandler;

    public TransactionConsole(TransactionService service, InputHandler inputHandler) {
        this.service = service;
        this.inputHandler = inputHandler;
    }

    private void showMenu(){
        System.out.println("=== Transaction Management ===");
        System.out.println("1. Add Transaction");
        System.out.println("2. View Product");
        System.out.println("3. Back");
    }

    public void run(){
        while (true){
            this.showMenu();
            int choice = this.inputHandler.getInt("Pilih menu: ");
            switch (choice){
                case 1:
                    this.createTransaction();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    private void createTransaction(){
        System.out.println("Apakah sudah pernah laundry disini sebelumnya");
        System.out.println("1. Sudah");
        System.out.println("2. Belum");
        System.out.println("3. Back");

        while (true){
            int choice = inputHandler.getInt("Masukkan pilihan: ");
            switch (choice){
                case 1:
                    //kalau sudah
                    break;
                case 2:
                    //kalau belum
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }



}
