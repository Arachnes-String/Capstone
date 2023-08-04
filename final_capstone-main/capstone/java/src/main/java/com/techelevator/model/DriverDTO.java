package com.techelevator.model;

public class DriverDTO {

    private int userId;
    private String username;
    private String firstName;
    private String lastName;
    private String zip;
    private double radius;

    public DriverDTO() {
    }

    public DriverDTO(int userId, String userName, String firstName, String lastName, String zip, double radius) {
        this.userId = userId;
        this.username = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.zip = zip;
        this.radius = radius;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
