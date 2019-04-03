package com.example.alzbot;


public class Patient {
    String name, address, drname, drmobile, ename, econtact, eaddress, dob, save;

    public Patient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDrname() {
        return drname;
    }

    public void setDrname(String drname) {
        this.drname = drname;
    }

    public String getDrmobile() {
        return drmobile;
    }

    public void setDrmobile(String drmobile) {
        this.drmobile = drmobile;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEcontact() {
        return econtact;
    }

    public void setEcontact(String econtact) {
        this.econtact = econtact;
    }

    public String getEaddress() {
        return eaddress;
    }

    public void setEaddress(String eaddress) {
        this.eaddress = eaddress;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public Patient(String name, String address, String drname, String drmobile, String ename, String econtact, String eaddress, String dob, String save) {
        this.name = name;
        this.address = address;
        this.drname = drname;
        this.drmobile = drmobile;
        this.ename = ename;
        this.econtact = econtact;
        this.eaddress = eaddress;
        this.dob = dob;
        this.save = save;
    }
}
