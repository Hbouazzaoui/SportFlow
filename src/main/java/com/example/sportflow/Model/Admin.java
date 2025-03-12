package com.example.sportflow.Model;

public class Admin {
    private int admin_id ;
    private String username;
    private String password;
    private String role;
    private String email;
    private String birth_date;


    public Admin(int admin_id, String username, String password, String role, String email, String birth_date) {
        this.admin_id = admin_id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.birth_date = birth_date;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return admin_id;
    }

    public void setId(int id) {
        this.admin_id = admin_id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
