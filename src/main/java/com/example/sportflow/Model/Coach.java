package com.example.sportflow.Model;

public class Coach {
    private int coachId;
    private String name;
    private String speciality;

    public Coach(String name, String speciality) {
        this.coachId = coachId;
        this.name = name;
        this.speciality = speciality;
    }

    public Coach(int coach_id, String speciality) {

    }

    // Getters and Setters
    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}