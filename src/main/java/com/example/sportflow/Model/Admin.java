package com.example.sportflow.Model;

public class Admin {
    private int id;
    private String username;
    private String password;
    private String role;
    private String email;
    private String birth_date;
    private String sport; // For members
    private String speciality; // For coaches

    public Admin(int id, String username, String password, String role, String email, String birth_date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.birth_date = birth_date;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}