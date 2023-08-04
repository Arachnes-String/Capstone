package com.techelevator.model;

import java.time.LocalDateTime;

public class DriverPickUpDTO {


    private int pickUpId;
    private int driverId;
    private int clientId;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private LocalDateTime dateTimeRequested;
    private LocalDateTime dateTimeCompleted;
    private boolean clearBinFull;
    private boolean greenBinFull;
    private boolean brownBinFull;
    private String status;
    private double weight;

    public DriverPickUpDTO() {
    }

    public DriverPickUpDTO(int pickUpId, int driverId, int clientId, String streetAddress, String city, String state, String zip, LocalDateTime dateTimeRequested, LocalDateTime dateTimeCompleted, boolean clearBinFull, boolean greenBinFull, boolean brownBinFull, String status, double weight) {
        this.pickUpId = pickUpId;
        this.driverId = driverId;
        this.clientId = clientId;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.dateTimeRequested = dateTimeRequested;
        this.dateTimeCompleted = dateTimeCompleted;
        this.clearBinFull = clearBinFull;
        this.greenBinFull = greenBinFull;
        this.brownBinFull = brownBinFull;
        this.status = status;
        this.weight = weight;
    }

    public int getPickUpId() {
        return pickUpId;
    }

    public void setPickUpId(int pickUpId) {
        this.pickUpId = pickUpId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public LocalDateTime getDateTimeRequested() {
        return dateTimeRequested;
    }

    public void setDateTimeRequested(LocalDateTime dateTimeRequested) {
        this.dateTimeRequested = dateTimeRequested;
    }

    public LocalDateTime getDateTimeCompleted() {
        return dateTimeCompleted;
    }

    public void setDateTimeCompleted(LocalDateTime dateTimeCompleted) {
        this.dateTimeCompleted = dateTimeCompleted;
    }

    public boolean isClearBinFull() {
        return clearBinFull;
    }

    public void setClearBinFull(boolean clearBinFull) {
        this.clearBinFull = clearBinFull;
    }

    public boolean isGreenBinFull() {
        return greenBinFull;
    }

    public void setGreenBinFull(boolean greenBinFull) {
        this.greenBinFull = greenBinFull;
    }

    public boolean isBrownBinFull() {
        return brownBinFull;
    }

    public void setBrownBinFull(boolean brownBinFull) {
        this.brownBinFull = brownBinFull;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
