package com.techelevator.model;

public class Bin {

    private int clientId;
    private int clearBins;
    private int greenBins;
    private int brownBins;
    private double totalWeight;
    private int credits;
    private int rejections;

    public Bin() {
    }

    public Bin(int clientId, int clearBins, int greenBins, int brownBins, double totalWeight, int credits, int rejections) {
        this.clientId = clientId;
        this.clearBins = clearBins;
        this.greenBins = greenBins;
        this.brownBins = brownBins;
        this.totalWeight = totalWeight;
        this.credits = credits;
        this.rejections = rejections;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getClearBins() {
        return clearBins;
    }

    public void setClearBins(int clearBins) {
        this.clearBins = clearBins;
    }

    public int getGreenBins() {
        return greenBins;
    }

    public void setGreenBins(int greenBins) {
        this.greenBins = greenBins;
    }

    public int getBrownBins() {
        return brownBins;
    }

    public void setBrownBins(int brownBins) {
        this.brownBins = brownBins;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getRejections() {
        return rejections;
    }

    public void setRejections(int rejections) {
        this.rejections = rejections;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

}
