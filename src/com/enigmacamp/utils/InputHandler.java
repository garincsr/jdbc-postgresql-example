package com.enigmacamp.utils;

import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler(){
        scanner = new Scanner(System.in);
    }

    public int getInt(String prompt){
        System.out.print(prompt);
        while(!scanner.hasNextInt()){
            System.out.println("Input must be numeric");
            scanner.next();
            System.out.println(prompt);
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    public String getString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
