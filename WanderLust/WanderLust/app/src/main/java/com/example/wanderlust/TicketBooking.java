package com.example.wanderlust;

public class TicketBooking {
    String name;
    String from;
    String to;
    String contact;
    String aadhar;
    String date;

    public TicketBooking(String name, String from, String to, String contact, String aadhar, String date) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.contact = contact;
        this.aadhar = aadhar;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getContact() {
        return contact;
    }

    public String getAadhar() {
        return aadhar;
    }

    public String getDate() {
        return date;
    }
}
