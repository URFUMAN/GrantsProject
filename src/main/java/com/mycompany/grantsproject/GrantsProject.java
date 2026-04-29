package com.mycompany.grantsproject;

import java.util.List;

/**
 * Главный класс приложения.
 * Запускает все этапы обработки данных.
 */
public class GrantsProject {

    public static void main(String[] args) {

        // Чтение CSV
        List<Grant> grants = CSVReaderService.readGrants("Гранты.csv");

        System.out.println("Прочитано записей: " + grants.size());

        //Создание таблиц
        DatabaseManager.createTables();

        //Запись данных в БД
        DatabaseManager.insertData(grants);
        
        //Выполнение задачи 1
        DatabaseManager.getAverageJobsByYear();
    }
}