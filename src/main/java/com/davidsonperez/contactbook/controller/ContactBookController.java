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

    public boolean otherVerify(String name, String lastName) {
        var contacts = model.getContacts();
        var exists = false;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equals(name) && contacts.get(i).getLastName().equals(lastName)) {
                exists = true;
            }
        }
        return exists;
    }
    
    public boolean verifyExistContact(String phoneNumber) {
        return(model.verifyExistContact(phoneNumber));
    }

    public void deleteContact(String phoneNumber) {
        model.deleteContact(phoneNumber);
    }

    public void modifyContact(String phoneNumber, String address, String newPhoneNumber, String webSite) {
        var contacts = model.getContacts();
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(phoneNumber)) {
                contacts.get(i).setAddress(address);
                contacts.get(i).setPhoneNumber(newPhoneNumber);
                contacts.get(i).setWebSite(webSite);
            }
        }
    }

    public String showContact(String phoneNumber) {
        var contacts = model.getContacts();
        var contact = "";
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(phoneNumber)) {
                contact = contacts.get(i).toString();
            }
        }
        return contact;
    }

    public void closeApp() {
        ConnectionDB.closeConnection();
        view.stopMenu();
    }
}
