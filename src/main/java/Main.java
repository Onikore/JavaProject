package main.java;

import static main.java.Constants.CSV_PATH;

public class Main {
    public static void main(String[] args) {

        var parser = new Parser(CSV_PATH);
        parser.loadStaffFromFile();
        parser.generateFields();
        var years = parser.getYears();
        var genres = parser.getGenres();
        var names = parser.getNames();
        var platforms = parser.getPlatforms();
        var publishers = parser.getPublishers();
        var records = parser.getRecords();

        DBManager.createDB();

        for (var y : years)
            DBManager.insertSimples("years", y);

        for (var y : genres)
            DBManager.insertSimples("genres", y);

        for (var y : names)
            DBManager.insertSimples("names", y);

        for (var y : platforms)
            DBManager.insertSimples("platforms", y);

        for (var y : publishers)
            DBManager.insertSimples("publishers", y);

        for (Record z : records)
            DBManager.insertBig(z);
    }
}
