package com.tanay.thundercipher.leaveapplications;

public class Application {

    String name, rollNumber, startDate, endDate, place, purpose;
    boolean wardenApprove, securityApprove;

    public Application(String name, String rollNumber, String startDate, String endDate, String place, String purpose, boolean wardenApprove, boolean securityApprove)
    {
        this.name = name;
        this.rollNumber = rollNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.purpose = purpose;
        this.wardenApprove = wardenApprove;
        this.securityApprove = securityApprove;
    }
}
