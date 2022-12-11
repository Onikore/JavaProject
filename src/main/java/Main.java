package main.java;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.SwingWrapper;

import java.util.ArrayList;
import java.util.List;

import static main.java.Constants.*;

public class Main {
    public static void main(String[] args) {
        /*====== СОЗДАНИЕ ВСЕГО =======*/
        var parser = new Parser(CSV_PATH);
        parser.loadRecordsFromFile();
        parser.generateFields();
        var years = parser.getYears().stream()
                .sorted()
                .toList();

        var genres = parser.getGenres();
        var names = parser.getNames();
        var platforms = parser.getPlatforms();
        var publishers = parser.getPublishers();
        var records = parser.getRecords();

        DBManager.createDB();

        for (var y : years)
            DBManager.insertCategories("years", y);

        for (var y : genres)
            DBManager.insertCategories("genres", y);

        for (var y : names)
            DBManager.insertCategories("names", y);

        for (var y : platforms)
            DBManager.insertCategories("platforms", y);

        for (var y : publishers)
            DBManager.insertCategories("publishers", y);

        for (Record z : records)
            DBManager.insertRecords(z);

        /*========= ЗАДАНИЕ 1 =========*/
        var task1Data = DBManager.getAVGPlatforms();
        List<String> dataX = new ArrayList<>();
        List<Double> dataY = new ArrayList<>();
        for (var i : task1Data) {
            dataX.add(i.platform);
            dataY.add(i.avgSales);
        }
        CategoryChart chart = new CategoryChart(1280, 600);
        chart.addSeries("AVG_global_sales", dataX, dataY);
        new SwingWrapper<>(chart).displayChart();

        /*========= ЗАДАНИЕ 2 =========*/
        var task2data = DBManager.task2and3(TASK_2_QUERY);
        System.out.println("ЗАДАНИЕ 2: " + task2data.platform + " - " + task2data.avgSales);

        /*========= ЗАДАНИЕ 3 =========*/
        var task3data = DBManager.task2and3(TASK_3_QUERY);
        System.out.println("ЗАДАНИЕ 3: " + task3data.platform + " - " + task3data.avgSales);
    }
}
