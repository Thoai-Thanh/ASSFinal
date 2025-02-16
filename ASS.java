/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Lam Thoai Thanh - CE190169
 */
public class ASS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Manager mn = new Manager("Thanh", "1", "Lam Thoai Thanh", "090909");
        boolean check = true;
        while (check) {
            System.out.println("-----SmartCinema-----");
            System.out.println("1. login");
            System.out.println("2. register");
            System.out.println("3. Exit");
            System.out.print("Please select: ");
            boolean validInput = false;
            int choice = getValidChoice(sc);
            switch (choice) {
                case 1:
                  
                    break;
                case 2:
                   
                    break;
                case 3:
                    check = false;
                    break;
                default:
                    System.out.println("Please select a number from 1 to 3!");
            }
        }

    }

    private static int getValidChoice(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer!");
                sc.next();
            }
        }
    }
}
