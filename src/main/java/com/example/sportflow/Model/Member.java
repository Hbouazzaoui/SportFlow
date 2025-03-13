package com.example.sportflow.Model;

public class Member {
    private int member_id;
    private String sport;

    public Member(int member_id, String sport) {
        this.member_id = member_id;
        this.sport = sport;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

}
