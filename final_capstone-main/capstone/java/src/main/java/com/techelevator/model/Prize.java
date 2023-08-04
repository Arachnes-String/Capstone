package com.techelevator.model;

public class Prize {

    private int prizeId;
    private String name;
    private int creditValue;

    public Prize() {
    }

    public Prize(int prizeId, String name, int creditValue) {
        this.prizeId = prizeId;
        this.name = name;
        this.creditValue = creditValue;
    }

    public int getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(int prizeId) {
        this.prizeId = prizeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(int creditValue) {
        this.creditValue = creditValue;
    }
}
