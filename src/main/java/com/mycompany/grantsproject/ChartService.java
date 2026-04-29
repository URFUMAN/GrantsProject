package com.mycompany.grantsproject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;

/**
 * Построение графиков
 */
public class ChartService {

    public static void createJobsChart(List<YearStat> stats) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (YearStat stat : stats) {
            dataset.addValue(stat.getAvgJobs(), "Jobs", String.valueOf(stat.getYear()));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Среднее количество рабочих мест по годам",
                "Год",
                "Рабочие места",
                dataset
        );

        try
        {
            ChartUtils.saveChartAsPNG(new File("jobs_chart.png"), chart, 800, 600);
            System.out.println("График сохранён: jobs_chart.png");
            
            // 1. Создаем панель для графика
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

            // 2. Создаем окно
            JFrame frame = new JFrame("Просмотр графика");
            frame.setContentPane(chartPanel);
            frame.pack();
            frame.setLocationRelativeTo(null); // Окно по центру экрана
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Закрыть только это окно
            frame.setVisible(true); // Показать!
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}