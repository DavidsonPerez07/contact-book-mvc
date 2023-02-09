package com.davidsonperez.contactbook;

import com.davidsonperez.contactbook.controller.ContactBookController;
import com.davidsonperez.contactbook.model.ContactBookModel;
import com.davidsonperez.contactbook.view.ContactBookView;

public class App 
{
    public static void main( String[] args )
    {
        var controller = new ContactBookController(new ContactBookView(), new ContactBookModel());
        controller.initApp();
    }
}
