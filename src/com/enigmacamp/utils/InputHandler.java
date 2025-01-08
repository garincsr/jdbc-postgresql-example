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

    public String getDateString(String prompt) {
        String regex = "\\d{4}-\\d{2}-\\d{2}"; // Regex untuk format yyyy-MM-dd
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input.matches(regex)) {
                return input; // Jika format sesuai, kembalikan input
            } else {
                System.out.println("Invalid date format. Please enter in the format: yyyy-MM-dd");
            }
        }
    }
}
