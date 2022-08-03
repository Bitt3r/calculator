package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("If you want to terminate app you can do it by CTRL + C or typing Quit, quit, q");
        System.out.println("Please enter your calculation");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String eq = scanner.nextLine();
            if (eq.equals("quit") || eq.equals("Quit") || eq.equals("q")) {
                System.out.println("You just exited app");
                break;
            }
            new Compute().exec(eq);
            System.out.println("Enter new calculation: ");

        }
    }
}