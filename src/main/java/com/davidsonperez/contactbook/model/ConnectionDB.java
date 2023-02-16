package com.davidsonperez.contactbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String DATABASE_URL_H2 = "jdbc:h2:./contactbook";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/contactbook";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "Trosquielpro07";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException("No puedo conectarme a la base de datos: " + e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("No puedo cerrar conexión a la base de datos: " + e.getMessage());
        }
    }

    public static void createTables() {
        try {
            var conn = getConnection();
            var stmt = conn.createStatement();
            stmt.execute("""
                CREATE TABLE Contact (
                    phone_number varchar(15) PRIMARY KEY, 
                    first_name varchar(100) not null, 
                    last_name varchar(100) not null, 
                    address varchar(100), 
                    company_name varchar(100), 
                    city varchar(100), 
                    web_site varchar(100)
                )
                """);
            stmt.close();
            closeConnection();

        } catch (SQLException e) {
            
        }
    }

}
