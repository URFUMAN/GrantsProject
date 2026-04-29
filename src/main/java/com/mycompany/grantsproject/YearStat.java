package com.mycompany.grantsproject;

/**
 * Хранит среднее количество рабочих мест по году
 */
public class YearStat {

    private int year;
    private double avgJobs;

    public YearStat(int year, double avgJobs) {
        this.year = year;
        this.avgJobs = avgJobs;
    }

    public int getYear() { return year; }
    public double getAvgJobs() { return avgJobs; }
}