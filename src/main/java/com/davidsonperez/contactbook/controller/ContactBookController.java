package com.davidsonperez.contactbook.controller;

import java.util.List;

import com.davidsonperez.contactbook.model.ConnectionDB;
import com.davidsonperez.contactbook.model.ContactBookModel;
import com.davidsonperez.contactbook.model.entity.Contact;
import com.davidsonperez.contactbook.view.ContactBookView;

public class ContactBookController {
    private ContactBookView view;
    private ContactBookModel model;

    public ContactBookController(ContactBookView view, ContactBookModel model) {
        this.view = view;
        this.model = model;

        this.view.setController(this);
    }

    public void initApp() {
        ConnectionDB.createTables();
        view.showMenu();
    }

    public List<Contact> getContacts() {
        return model.getContacts();
    }

    public void addContact(String name, String lastName, String address, String phoneNumber, String companyName, String city, String webSite) {
        model.addContact(new Contact(name, lastName, address, phoneNumber, companyName, city, webSite));
    }
    
    public boolean searchContact(String phoneNumber) {
        return(model.searchContact(phoneNumber));
    }

    public boolean verifyExistContact(String name, String lastName) {
        return(model.verifyExistContact(name, lastName));
    }

    public void deleteContact(String phoneNumber) {
        model.deleteContact(phoneNumber);
    }

    public void modifyContact(String phoneNumber, String newAddress, String newPhoneNumber, String newWebSite) {
        model.modifyContact(phoneNumber, newAddress, newPhoneNumber, newWebSite);
    }

    public String showContact(String phoneNumber) {
        return model.showContact(phoneNumber);
    }

    public void closeApp() {
        ConnectionDB.closeConnection();
        view.stopMenu();
    }
}
