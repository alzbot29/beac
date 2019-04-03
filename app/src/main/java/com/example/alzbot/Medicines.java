package com.example.alzbot;

public class Medicines {
    String name, qty;
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Medicines(String name, String qty, String time) {
        this.name = name;
        this.qty = qty;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
    public Medicines() {
    }

}

