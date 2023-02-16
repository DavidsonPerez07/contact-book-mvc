package com.davidsonperez.contactbook.view;

import java.util.Scanner;

import com.davidsonperez.contactbook.controller.ContactBookController;

public class ContactBookView {
    
    private ContactBookController controller;
    private Scanner input;
    private boolean show;
    
    public void setController(ContactBookController controller) {
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

    public void showMenu() {
        show = true;
        while (show) {
            try {
                System.out.println("""
                        Bienvenido a tu agenda de contactos
                        ======================================
                        1. Ver todos los contactos
                        2. Añadir contacto
                        3. Eliminar contacto
                        4. Modificar registro
                        5. Ver registro
                        0. Salir de la aplicación
                        """);
                System.out.println("Ingrese su opción: ");
                var opcion = input.nextLine();
                switch (opcion.toLowerCase().strip()) {
                    case "1":
                        getContacts();
                        break;
                    case "2":
                        newContact();
                        break;
                    case "3":
                        deleteContact();
                        break;
                    case "4":
                        modifyContact();
                        break;
                    case "5":
                        showContact();
                        break;
                    case "0":
                        closeApp();
                        break;
                    default:
                        throw new IllegalArgumentException("Opcion no válida");
                }
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
                waitEnter();
            }
        }
        System.out.println("Saliendo del programa...");
    }

    private void closeApp() {
        controller.closeApp();
    }

    private void getContacts() {
        var contacts = controller.getContacts();
        System.out.println("""
                Lista de contactos
                ========================================
                """);
        contacts.forEach(System.out::println);
        waitEnter();
    }

    private void newContact() {
        System.out.println("""
                Agregar nuevo contacto
                ========================================
                """);
        System.out.println("Ingrese el nombre: ");
        var name = input.nextLine();
        System.out.println("Ingrese el apellido: ");
        var lastName = input.nextLine();
        var exists = controller.verifyExistContact(name, lastName);
        if (exists) {
            System.out.println("El contacto ya existe");
            waitEnter();
        }
        else {
            System.out.println("Ingrese la dirección: ");
            var address = input.nextLine();
            System.out.println("Ingrese el número de celular: ");
            var phoneNumber = input.nextLine();
            System.out.println("Ingrese el nombre de la empresa (opcional): ");
            var companyName = input.nextLine();
            System.out.println("Ingrese la ciudad (opcional): ");
            var city = input.nextLine();
            System.out.println("Ingrese la página web (opcional): ");
            var webSite = input.nextLine();

        controller.addContact(name, lastName, address, phoneNumber, companyName, city, webSite);

        System.out.println("Contacto guardado exitosamente!");
            waitEnter();
        } 
    }

    private void deleteContact() {
        System.out.println("Ingrese el número telefónico del contacto a eliminar: ");
        var phoneNumber = input.nextLine();
        var exists = controller.searchContact(phoneNumber);
        if (exists) {
            controller.deleteContact(phoneNumber);
            System.out.println("Contacto eliminado exitosamente!");
        }
        else {
            System.out.println("El número telefónico no está resgistrado");
        }
        waitEnter();
    }

    private void modifyContact() {
        System.out.println("Ingrese el número telefónico del contacto a modificar: ");
        var phoneNumber = input.nextLine();
        var exists = controller.searchContact(phoneNumber);
        if (exists) {
            System.out.println("Ingrese la nueva dirección: ");
            var newAddress = input.nextLine();
            System.out.println("Ingrese el nuevo número de teléfono: ");
            var newPhoneNumber = input.nextLine();
            System.out.println("Ingrese la nueva página web (opcional): ");
            var newWebSite = input.nextLine();
            controller.modifyContact(phoneNumber, newAddress, newPhoneNumber, newWebSite);
            System.out.println("Contacto modificado exitosamente!");
        }
        else {
            System.out.println("El número telefónico no está registrado");
        }
        waitEnter();
    }

    private void showContact() {
        System.out.println("Ingrese el número telefónico del contacto a consultar: ");
        var phoneNumber = input.nextLine();
        var contact = "";
        var exists = controller.searchContact(phoneNumber);
        if (exists) {
            contact = controller.showContact(phoneNumber);
            System.out.println(contact);
        }
        else {
            System.out.println("El número telefónico no está registrado");
        }
        waitEnter();
    }

    private void waitEnter() {
        System.out.println("\n Presione Enter para continuar.");
        input.nextLine();
    }

    public void stopMenu() {
        show = false;
    }
}
