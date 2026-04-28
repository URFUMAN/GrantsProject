package com.mycompany.grantsproject;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderService {

    public static List<Grant> readGrants(String filePath) {
        List<Grant> grants = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {

            String[] line;
            reader.readNext(); // пропускаем заголовок

            while ((line = reader.readNext()) != null) {

                String companyName = line[0];
                String street = line[1];

                String amountStr = line[2]
                        .replace("$", "")
                        .replace(",", "");

                double grantAmount = Double.parseDouble(amountStr);

                int fiscalYear = (int) Double.parseDouble(line[3]);

                String businessType = line[4];

                int jobsCreated = Integer.parseInt(line[5]);

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