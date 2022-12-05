package main.java;

public class Constants {
    private Constants() {
    }

    public static final String JDBC_URL = "jdbc:sqlite:src/main/resources/records.sqlite3";
    public static final String CSV_PATH = "src/main/resources/games.csv";

    public static final String CREATE_YEARS_TABLE_QUERY = "create table IF NOT EXISTS 'years' (id integer primary key autoincrement, year text);";
    public static final String CREATE_GENRES_TABLE_QUERY = "create table IF NOT EXISTS 'genres'    (id integer primary key autoincrement, genre     text);";
    public static final String CREATE_NAMES_TABLE_QUERY = "create table IF NOT EXISTS 'names'     (id integer primary key autoincrement, name      text);";
    public static final String CREATE_PLATFORMS_TABLE_QUERY = "create table IF NOT EXISTS 'platforms' (id integer primary key autoincrement, platform  text);";
    public static final String CREATE_PUBLISHERS_TABLE_QUERY = "create table IF NOT EXISTS 'publishers'(id integer primary key autoincrement, publisher text);";
    public static final String CREATE_RECORDS_QUERY = """
            create table IF NOT EXISTS 'records'
            (
                id           integer primary key autoincrement,
                rank         integer,
                name         integer,
                platform     integer,
                year         integer,
                genre        integer,
                publisher    integer,
                na_Sales     real,
                eu_Sales     real,
                jp_Sales     real,
                other_Sales  real,
                global_Sales real,
                foreign key (name) references names (id),
                foreign key (platform) references platforms (id),
                foreign key (year) references years (id),
                foreign key (genre) references genres (id),
                foreign key (publisher) references publishers (id)
            );""";
}
