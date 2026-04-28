package com.mycompany.grantsproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:grants.db";

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Подключено к SQLite");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void createTables() {
    String createBusinessTypes = """
        CREATE TABLE IF NOT EXISTS business_types (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT UNIQUE
        );
    """;

    String createGrants = """
        CREATE TABLE IF NOT EXISTS grants (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            company_name TEXT,
            street TEXT,
            fiscal_year INTEGER,
            grant_amount REAL,
            jobs_created INTEGER,
            business_type_id INTEGER,
            FOREIGN KEY (business_type_id) REFERENCES business_types(id)
        );
    """;

    try (Connection conn = connect();
         var stmt = conn.createStatement()) {

        stmt.execute(createBusinessTypes);
        stmt.execute(createGrants);

        System.out.println("Таблицы созданы");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}