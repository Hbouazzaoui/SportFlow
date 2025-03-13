package com.example.sportflow.Model;

public class Coach {
    private int coach_id;
    private String speciality;


    public Coach(String coach_id, String speciality, String s) {
        this.coach_id = coach_id;
        this.speciality = speciality;
    }

    public int getCoach_id() {
        return coach_id;
    }

    public void setCoach_id(int coach_id) {
        this.coach_id = coach_id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
