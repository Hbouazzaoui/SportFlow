package com.example.sportflow.Model;

public class Member {
    private int memberId;
    private String name;
    private String birthDate;
    private String sport;

    // Constructors
    public Member(int member_id, String sport) {}

    public Member(String name, String birthDate, String sport) {
        this.memberId = memberId;
        this.name = name;
        this.birthDate = birthDate;
        this.sport = sport;
    }

    // Getters and Setters
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}