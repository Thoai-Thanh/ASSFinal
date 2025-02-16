/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lam Thoai Thanh - CE190169
 */
public class Customer extends Person {
    private List<Customer> customer;
    private String address;
    private int age;
    public Customer() {
    }

    public Customer(String username, String password, String fullName,int age, String phone, String address) {
        super(username, password, fullName, phone);
        this.address = address;
        this.name = name;
        customer = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void displayInfo() {
        System.out.println("Customer: " + username);
    }
    public void addCustomer(Scanner sc, ArrayList<Customer> ct) {
        Customer customer = new Customer();
        sc.nextLine();
        System.out.print("Enter customer name: ");
        customer.setUsername(sc.nextLine());
        System.out.print("Enter age: ");
        customer.setAge(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter address: ");
        customer.setAddress(sc.nextLine());
        System.out.print("Enter phone number: ");
        customer.setTel(sc.nextLine());

        String account;
        while (true) {
            System.out.print("Enter account: ");
            account = sc.nextLine();
            if (!checkTK(account)) {
                break;
            } else {
                System.out.println("Account already exists. Please enter a different account.");
            }
        }
        customer.setAccount(account);

        String password;
        while (true) {
            System.out.print("Enter password: ");
            password = sc.nextLine();
            if (isValidPassword(password)) {
                break;
            }
        }
        customer.setPassword(password);

        customer.add(new Customer(username, password, fullName, age, phone, address));
        System.out.println("Customer added successfully!");
    }

    boolean checkTK(String tK) {
        for (Customer c : customerList) {
            if (c.getAccount().equals(tK)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidPassword(String mk) {
        if (mk == null || mk.length() < 8) {
            System.out.println("Password must be at least 8 characters long.");
            return false;
        }
        if (mk.contains(" ")) {
            System.out.println("Password must not contain spaces.");
            return false;
        }
        if (!mk.matches(".*[A-Z].*")) {
            System.out.println("Password must contain at least one uppercase letter.");
            return false;
        }
        if (!mk.matches(".*[a-z].*")) {
            System.out.println("Password must contain at least one lowercase letter.");
            return false;
        }
        if (!mk.matches(".*[0-9].*")) {
            System.out.println("Password must contain at least one digit.");
            return false;
        }
        return true;
    }

    public List<Customer> getCustomerList() {
        return this.customer;
    }

    public void menuCustomer(Scanner sc) {
        boolean check = true;
        while (check) {
            System.out.println("1. xem danh sách phim");
            System.out.println("select your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("hi");
                    break;
                case 2:
                    check = false;
                    break;
            }
        }

    }

    public void displayMenu(Scanner sc) {
        System.out.println("Xin chào quý khách");

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Xem Danh Sách Phim");
            System.out.println("2. Tìm Kiếm Phim");
            System.out.println("3. Nạp Tiền");
            System.out.println("4. Đặt Vé");
            System.out.println("5. exit");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    mv.displayMovieList();
                    break;
                case 2:
                 
                    break;
                case 3:
                   
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("tạm biệt");
                    return;
                default:
                    System.out.println("nhập lỗi");
            }
        }
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}
