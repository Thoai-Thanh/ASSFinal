/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

/**
 *
 * @author Lam Thoai Thanh - CE190169
 */
public abstract class Person {

        protected String username;
        protected String password;
        protected String fullName;
        protected String phone;

    public Person() {
    }

        public Person(String username, String password, String fullName, String phone) {
            this.username = username;
            this.password = password;
            this.fullName = fullName;
            this.phone = phone;
        }

        public abstract void displayInfo();

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getFullName() {
            return fullName;
        }

        public String getPhone() {
            return phone;
        }
        
    }


