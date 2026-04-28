package com.mycompany.grantsproject;

public class Grant {

    private String companyName;
    private String street;
    private int fiscalYear;
    private String businessType;
    private double grantAmount;
    private int jobsCreated;

    public Grant(String companyName, String street, int fiscalYear,
                 String businessType, double grantAmount, int jobsCreated) {
        this.companyName = companyName;
        this.street = street;
        this.fiscalYear = fiscalYear;
        this.businessType = businessType;
        this.grantAmount = grantAmount;
        this.jobsCreated = jobsCreated;
    }

    @Override
    public String toString() {
        return companyName + " | " + businessType + " | " +
               fiscalYear + " | $" + grantAmount + " | jobs: " + jobsCreated;
    }
}