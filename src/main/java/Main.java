package main.java;

import static main.java.Constants.CSV_PATH;

public class Main {
    public static void main(String[] args) {
        var parser = new Parser(CSV_PATH);
        parser.loadStaffFromFile();
        var recs = parser.getRecords();
        System.out.println(recs);
    }
}
