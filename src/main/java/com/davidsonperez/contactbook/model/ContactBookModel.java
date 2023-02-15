package com.davidsonperez.contactbook.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.davidsonperez.contactbook.model.entity.Contact;

public class ContactBookModel {

    public List<Contact> getContacts() {
        var contacts = new ArrayList<Contact>();

        try {
            var conn = ConnectionDB.getConnection();
            var stmt = conn.createStatement();
            var rset = stmt.executeQuery("""
                    SELECT phone_number, first_name, last_name, address, company_name, city, web_site
                    FROM contact
                    ORDER BY first_name, last_name
                    """);
            while (rset.next()) {
                var firstName = rset.getString("first_name");
                var last_name = rset.getString("last_name");

                var contact = new Contact(firstName, last_name,
                        rset.getString("address"),
                        rset.getString("phone_number"),
                        rset.getString("company_name"),
                        rset.getString("city"),
                        rset.getString("web_site"));

                contacts.add(contact);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error getContacts(): " + e.getMessage());
        }

        return contacts;
    }

    public void addContact(Contact contact) {
        try {
            var conn = ConnectionDB.getConnection();
            var stmt = conn.createStatement();

            var sql = new StringBuilder("INSERT INTO Contact (phone_number, first_name, last_name, address, company_name, city, web_site) VALUES (")
            .append(contact.getPhoneNumber() == null ? "NULL" : "'"+contact.getPhoneNumber()+"'").append(",")
            .append(contact.getName() == null ? "NULL" : "'"+contact.getName()+"'").append(",")
            .append(contact.getLastName() == null ? "NULL" : "'"+contact.getLastName()+"'").append(",")
            .append(contact.getAddress() == null ? "NULL" : "'"+contact.getAddress()+"'").append(",")
            .append(contact.getCompanyName() == null ? "NULL" : "'"+contact.getCompanyName()+"'").append(",")
            .append(contact.getCity() == null ? "NULL" : "'"+contact.getCity()+"'").append(",")
            .append(contact.getWebSite() == null ? "NULL" : "'"+contact.getWebSite()+"'").append(")")
            .toString();
            
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error addContact(): " + e.getMessage());
        }
    }

    public void deleteContact(String phoneNumber) {
        try {
            var conn = ConnectionDB.getConnection();
            String query = "DELETE FROM contact WHERE phone_number = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, phoneNumber);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error deleteContact(): " + e.getMessage());
        } 
    }

    public void modifyContact(String phoneNumber, String newAddress, String newPhoneNumber, String newWebSite) {
        try {
            var conn = ConnectionDB.getConnection();
            String query = "UPDATE contact SET address = '" + newAddress + "', phone_number = '" + newPhoneNumber + "', web_site = '" + newWebSite + "' WHERE phone_number = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, phoneNumber);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error modifyContact(): " + e.getMessage());
        }
    }

    public String showContact(String phoneNumber) {
        var showContact = "";
        try {
            var conn = ConnectionDB.getConnection();
            var stmt = conn.createStatement();
            var rset = stmt.executeQuery("""
                SELECT phone_number, first_name, last_name, address, company_name, city, web_site 
                FROM contact 
                WHERE phone_number = '" + phoneNumber + "'
                    """);
            var contact = new Contact(rset.getString("first_name"), 
            rset.getString("last_name"), 
            rset.getString("address"), 
            rset.getString("phone_number"), 
            rset.getString("company_name"), 
            rset.getString("city"), 
            rset.getString("web_site"));
            showContact = contact.toString();

            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error showContact(): " + e.getMessage());
        }
        return showContact;
    }

    public boolean searchContact(String phoneNumber) {
        var contacts = getContacts();
        var exists = false;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(phoneNumber)) {
                exists = true;
            }
        }
        return exists;
    }

    public boolean verifyExistContact(String name, String lastName) {
        var contacts = getContacts();
        var exists = false;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(name) && contacts.get(i).getLastName().equalsIgnoreCase(lastName)) {
                exists = true;
            }
        }
        return exists;
    }
}
