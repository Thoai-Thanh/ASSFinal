/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lam Thoai Thanh - CE190169
 */
public class ASS {
    private static List<Customer> customers = new ArrayList<>();
    private static Manager manager;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        manager = new Manager("thanh", "thanh123", "System Admin", "0909");
        
        boolean check = true;
        while (check) {
            System.out.println("\n----- SmartCinema -----");
            System.out.println("1. Login as Manager");
            System.out.println("2. Login as Customer");
            System.out.println("3. Register New Customer");
            System.out.println("4. Exit");
            System.out.print("Please select: ");
            
            int choice = getValidChoice(sc);
            sc.nextLine(); 
            
            switch (choice) {
                case 1:
                    loginManager(sc);
                    break;
                case 2:
                    loginCustomer(sc);
                    break;
                case 3:
                    registerCustomer(sc);
                    break;
                case 4:
                    System.out.println("Thank you for using SmartCinema!");
                    check = false;
                    break;
                default:
                    System.out.println("Please select a number from 1 to 4!");
            }
        }
    }
    
    private static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            System.out.println("Password must be at least 8 characters long.");
            return false;
        }
        if (password.contains(" ")) {
            System.out.println("Password cannot contain spaces.");
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password must contain at least one uppercase letter.");
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            System.out.println("Password must contain at least one lowercase letter.");
            return false;
        }
        if (!password.matches(".*[0-9].*")) {
            System.out.println("Password must contain at least one number.");
            return false;
        }
        return true;
    }
    
    private static void registerCustomer(Scanner sc) {
        System.out.println("\n=== Customer Registration ===");
        
        System.out.print("Username: ");
        String username = sc.nextLine();
        
        if (findCustomer(username) != null) {
            System.out.println("Username already exists!");
            return;
        }
        
        String password;
        do {
            System.out.println("\nPassword requirements:");
            System.out.println("- At least 8 characters long!");
            System.out.println("- Must contain at least one uppercase letter!");
            System.out.println("- Must contain at least one lowercase letter!");
            System.out.println("- Must contain at least one number!");
            System.out.println("- Cannot contain spaces!");
            System.out.print("\nPassword: ");
            password = sc.nextLine();
        } while (!isValidPassword(password));
        
        System.out.print("Full Name: ");
        String fullName = sc.nextLine();
        
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        
        System.out.print("Address: ");
        String address = sc.nextLine();
        
        int age = 0;
        while (true) {
            try {
                System.out.print("Age: ");
                age = Integer.parseInt(sc.nextLine());
                if (age > 0) break;
                System.out.println("Age must be greater than 0!");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid age!");
            }
        }
        
        Customer newCustomer = new Customer(username, password, fullName, age, phone, address, manager);
        customers.add(newCustomer);
        System.out.println("Registration successful! Please login using option 2 (Login as Customer).");
    }
    
    private static void loginManager(Scanner sc) {
        System.out.println("\n=== Manager Login ===");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        
        if (username.equals(manager.getUsername()) && password.equals(manager.getPassword())) {
            System.out.println("Login successful!");
            manager.displayMenu(sc);
        } else {
            System.out.println("Invalid manager credentials!");
        }
    }
    
    private static void loginCustomer(Scanner sc) {
        if (customers.isEmpty()) {
            System.out.println("No registered customers. Please register first!");
            return;
        }
        
        System.out.println("\n=== Customer Login ===");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        
        Customer customer = findCustomer(username);
        if (customer != null && customer.getPassword().equals(password)) {
            System.out.println("Login successful!");
            customer.displayMenu(sc);
        } else {
            System.out.println("Invalid customer credentials!");
        }
    }
    
    private static Customer findCustomer(String username) {
        return customers.stream()
                .filter(c -> c.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
    
    private static int getValidChoice(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                sc.next();
            }
        }
    }
}