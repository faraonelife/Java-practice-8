package com.faraonelife.Model;

import java.time.LocalDate;

public class ToDoitem {
    private String shortdes;
    private  String details;
    private LocalDate date;

    public ToDoitem(String shortdes, String details, LocalDate date){
        this.shortdes=shortdes;
        this.details=details;
        this.date=date;

    }

    public void load(ToDoitem item) {
        setDetails(item.getDetails());
        setDate(item.getDate());
        setShortdes(item.getShortdes());
    }
    public String getShortdes() {
        return shortdes;
    }

    public void setShortdes(String shortdes) {
        this.shortdes = shortdes;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.getShortdes();
    }
}
