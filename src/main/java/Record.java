package main.java;

public class Record {
    public String rank;
    public String name;
    public String platform;
    public String year;
    public String genre;
    public String publisher;
    public String naSales;
    public String euSales;
    public String jpSales;
    public String otherSales;
    public String globalSales;

    public Record(String rank, String name, String platform, String year, String genre, String publisher,
                  String naSales, String euSales, String jpSales, String otherSales, String globalSales) {
        this.rank = rank;
        this.name = name;
        this.platform = platform;
        this.year = year;
        this.genre = genre;
        this.publisher = publisher;
        this.naSales = naSales;
        this.euSales = euSales;
        this.jpSales = jpSales;
        this.otherSales = otherSales;
        this.globalSales = globalSales;
    }

    @Override
    public String toString() {
        return this.rank + ", " +
                this.name + ", " +
                this.platform + ", " +
                this.year + ", " +
                this.genre + ", " +
                this.publisher + ", " +
                this.naSales + ", " +
                this.euSales + ", " +
                this.jpSales + ", " +
                this.otherSales + ", " +
                this.globalSales;
    }
}
