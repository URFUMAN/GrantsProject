package com.mycompany.grantsproject;

import java.util.List;

public class GrantsProject {

    public static void main(String[] args) {

        String path = "Гранты.csv";

       List<Grant> grants = CSVReaderService.readGrants(path);

        for (int i = 0; i < 10; i++) {
            System.out.println(grants.get(i));
        }

        System.out.println("Всего записей: " + grants.size());
        
        DatabaseManager.connect();
        DatabaseManager.createTables();
    }
}