package com.davidsonperez.contactbook.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.davidsonperez.contactbook.model.entity.Contact;

public class ContactBookModel {
    private List<Contact> contacts;

    public ContactBookModel() {
        contacts = new ArrayList<Contact>();
    }

    public List<Contact> getContacts() {
        Collections.sort(contacts);
        return contacts;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }
}
