package com.example.sportflow.Model;

public class Session {
    private int sessionId;
    private int memberId;
    private int coachId;
    private String dateTime;

    // Constructors
    public Session() {}

    public Session(int sessionId, int memberId, int coachId, String dateTime) {
        this.sessionId = sessionId;
        this.memberId = memberId;
        this.coachId = coachId;
        this.dateTime = dateTime;
    }

    // Getters and Setters
    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}