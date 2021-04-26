package com.builder;

public class Address {

    private String street;
    private String streetNo;

    public Address() {}

    public Address(String street, String streetNo) {
        this.street = street;
        this.streetNo = streetNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    @Override
    public String toString() {
        return "Address [street=" + street + ", streetNo=" + streetNo + "]";
    }
    
}
