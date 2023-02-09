package com.davidsonperez.contactbook.model.entity;

public class Contact implements Comparable<Contact> {
    private String name;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String companyName;
    private String city;
    private String webSite;
    
    public Contact(String name, String lastName, String address, String phoneNumber, String companyName, String city,
            String webSite) {
        this(name, lastName, address, phoneNumber);
        this.companyName = companyName;
        this.city = city;
        this.webSite = webSite;
    }

    public Contact(String name, String lastName, String address, String phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCity() {
        return city;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    @Override
    public String toString() {
        return name + " " + lastName + " | " + address + " | "
                + phoneNumber + " | " + companyName + " | " + city + " | " + webSite;
    }

    @Override
    public int compareTo(Contact c) {
        // TODO Auto-generated method stub
        return name.compareTo(c.name);
    }

    
}
