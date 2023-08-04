package com.techelevator.model;

public class Driver {

    private int driverId;
    private String firstName;
    private String lastName;
    private String zip;
    private double radius;

    public Driver() {
    }

    public Driver(DriverDTO body){
        this.driverId = body.getUserId();
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.zip = body.getZip();
        this.radius = body.getRadius();
    }

    public Driver(int driverId, String firstName, String lastName, String zip, double radius) {
        this.driverId = driverId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.zip = zip;
        this.radius = radius;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
