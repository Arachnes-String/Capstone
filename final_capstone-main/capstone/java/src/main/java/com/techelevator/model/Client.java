package com.techelevator.model;

//this Models the user_info table
public class Client {

    private int clientId;
    private String firstName;
    private String lastName;
    private int addressId;
    private String phoneNumber;
    private String emailAddress;



    public Client() {
    }

    public Client(ClientDTO client){
        this.clientId = client.getUserId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.addressId = client.getAddressId();
        this.phoneNumber = client.getPhoneNumber();
        this.emailAddress = client.getEmailAddress();
    }

    public Client(int userId, String first_name, String last_name, int addressId, String phoneNumber, String emailAddress) {
        this.clientId = userId;
        this.firstName = first_name;
        this.lastName = last_name;
        this.addressId = addressId;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int userId) {
        this.clientId = userId;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
