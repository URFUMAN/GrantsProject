package com.mycompany.grantsproject;

/**
 * Класс-модель, представляющий один грант.
 * Используется для хранения данных, полученных из CSV файла.
 */
public class Grant {

    private String companyName;
    private String street;
    private int fiscalYear;
    private String businessType;
    private double grantAmount;
    private int jobsCreated;

    /**
     * Конструктор объекта Grant.
     *
     * @param companyName название компании
     * @param street улица
     * @param fiscalYear фискальный год
     * @param businessType тип бизнеса
     * @param grantAmount размер гранта
     * @param jobsCreated количество рабочих мест
     */
    public Grant(String companyName, String street, int fiscalYear,
                 String businessType, double grantAmount, int jobsCreated) {
        this.companyName = companyName;
        this.street = street;
        this.fiscalYear = fiscalYear;
        this.businessType = businessType;
        this.grantAmount = grantAmount;
        this.jobsCreated = jobsCreated;
    }

    /** @return название компании */
    public String getCompanyName() { return companyName; }

    /** @return улица */
    public String getStreet() { return street; }

    /** @return фискальный год */
    public int getFiscalYear() { return fiscalYear; }

    /** @return тип бизнеса */
    public String getBusinessType() { return businessType; }

    /** @return размер гранта */
    public double getGrantAmount() { return grantAmount; }

    /** @return количество рабочих мест */
    public int getJobsCreated() { return jobsCreated; }

    /**
     * Переопределение метода toString для удобного вывода в консоль.
     */
    @Override
    public String toString() {
        return companyName + " | " + businessType + " | " +
               fiscalYear + " | $" + grantAmount + " | jobs: " + jobsCreated;
    }
}