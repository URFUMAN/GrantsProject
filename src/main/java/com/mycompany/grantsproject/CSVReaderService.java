package com.mycompany.grantsproject;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервисный класс для чтения CSV файла и преобразования данных в объекты Grant.
 */
public class CSVReaderService {

    /**
     * Читает CSV файл и возвращает список объектов Grant.
     *
     * <p>Метод выполняет:</p>
     * <ul>
     *     <li>Чтение файла построчно</li>
     *     <li>Очистку данных (удаление $, запятых)</li>
     *     <li>Преобразование строк в числа</li>
     *     <li>Создание объектов Grant</li>
     * </ul>
     *
     * @param filePath путь к CSV файлу
     * @return список грантов
     */
    public static List<Grant> readGrants(String filePath) {

        List<Grant> grants = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {

            String[] line;
            reader.readNext(); // пропуск заголовка

            while ((line = reader.readNext()) != null) {

                // защита от некорректных строк
                if (line.length < 6) continue;

                String companyName = line[0];
                String street = line[1];

                // очистка денежного значения
                String amountStr = line[2]
                        .replace("$", "")
                        .replace(",", "");

                double grantAmount = amountStr.isEmpty()
                        ? 0
                        : Double.parseDouble(amountStr);

                int fiscalYear = line[3].isEmpty()
                        ? 0
                        : (int) Double.parseDouble(line[3]);

                String businessType = line[4];

                int jobsCreated = line[5].isEmpty()
                        ? 0
                        : Integer.parseInt(line[5]);

                Grant grant = new Grant(
                        companyName,
                        street,
                        fiscalYear,
                        businessType,
                        grantAmount,
                        jobsCreated
                );

                grants.add(grant);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return grants;
    }
}







