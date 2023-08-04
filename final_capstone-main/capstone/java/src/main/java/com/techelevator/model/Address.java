package com.techelevator.model;

public class Address {

    private int addressId;
    private int clientId;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;

    public Address() {
    }

    public Address(int addressId, int userId, String streetAddress, String city, String state, String zip) {
        this.addressId = addressId;
        this.clientId = userId;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Address(ClientDTO client){
        this.clientId = client.getUserId();
        this.streetAddress = client.getStreetAddress();
        this.city = client.getCity();
        this.state = client.getState();
        this.zip = client.getZip();
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
}
