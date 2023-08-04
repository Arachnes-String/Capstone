package com.techelevator.model;

import java.time.LocalDateTime;

public class PickUp {

    private int pickUpId;

    //this is the timestamp for when the request itself was created...
    private LocalDateTime dateTimeCreated;

    //this is the timestamp for the time the user has requested the pick up...
    private LocalDateTime dateTimeRequested;

    //this is the timestamp for when the pickup has been completed...
    private LocalDateTime dateTimeCompleted;

    private int addressId;
    private int clientId;
    private int driverId;
    private String status;

    private boolean clearBinFull;
    private boolean greenBinFull;
    private boolean brownBinFull;

    private double weight;


    public PickUp() {
    }

    public PickUp(LocalDateTime dateTimeRequested, LocalDateTime dateTimeCompleted, int addressId, int clientId, boolean clearBinFull, boolean greenBinFull, boolean brownBinFull, double weight) {
        this.dateTimeRequested = dateTimeRequested;
        this.dateTimeCompleted = dateTimeCompleted;
        this.addressId = addressId;
        this.clientId = clientId;
        this.clearBinFull = clearBinFull;
        this.greenBinFull = greenBinFull;
        this.brownBinFull = brownBinFull;
        this.weight = weight;
    }

    public PickUp(int pickUpId, LocalDateTime dateTimeCreated, LocalDateTime dateTimeRequested, LocalDateTime dateTimeCompleted, int addressId, int clientId, int driverId, String status, boolean clearBinFull, boolean greenBinFull, boolean brownBinFull, double weight) {
        this.pickUpId = pickUpId;
        this.dateTimeCreated = dateTimeCreated;
        this.dateTimeRequested = dateTimeRequested;
        this.dateTimeCompleted = dateTimeCompleted;
        this.addressId = addressId;
        this.clientId = clientId;
        this.driverId = driverId;
        this.status = status;
        this.clearBinFull = clearBinFull;
        this.greenBinFull = greenBinFull;
        this.brownBinFull = brownBinFull;
        this.weight = weight;
    }


    public int getPickUpId() {
        return pickUpId;
    }

    public void setPickUpId(int pickUpId) {
        this.pickUpId = pickUpId;
    }

    public LocalDateTime getDateTimeCreated() {
        return dateTimeCreated;
    }

    public void setDateTimeCreated(LocalDateTime dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
