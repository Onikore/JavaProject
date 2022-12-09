package main.java;

public class Constants {
    //  PATHs
    public static final String JDBC_URL = "jdbc:sqlite:src/main/resources/records.sqlite3";
    public static final String CSV_PATH = "src/main/resources/games.csv";
    //  SQLITE QUERIES
    public static final String CREATE_YEARS_TABLE = "create table IF NOT EXISTS 'years' (id integer primary key autoincrement, year text);";
    public static final String CREATE_GENRES_TABLE = "create table IF NOT EXISTS 'genres'    (id integer primary key autoincrement, genre     text);";
    public static final String CREATE_NAMES_TABLE = "create table IF NOT EXISTS 'names'     (id integer primary key autoincrement, name      text);";
    public static final String CREATE_PLATFORMS_TABLE = "create table IF NOT EXISTS 'platforms' (id integer primary key autoincrement, platform  text);";
    public static final String CREATE_PUBLISHERS_TABLE = "create table IF NOT EXISTS 'publishers'(id integer primary key autoincrement, publisher text);";
    public static final String CREATE_RECORDS_TABLE = """
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
            );
            """;
    public static final String INSERT_RECORDS = "insert into records values (?,?,?,?,?,?,?,?,?,?,?,?);";
    public static final String INSERT_NAMES = "select id from names where name=?;";
    public static final String INSERT_PLATFORMS = "select id from platforms where platform=?;";
    public static final String INSERT_YEARS = "select id from years where year=?;";
    public static final String INSERT_GENRES = "select id from genres where genre=?;";
    public static final String INSERT_PUBLISHERS = "select id from publishers where publisher=?;";
    public static final String TASK_1_QUERY = "select (select platform from platforms " +
                                              "where records.platform = platforms.id) as platform," +
                                              "round(avg(global_Sales), 2) as AVG_global_sales " +
                                              "from records " +
                                              "group by platform\n" +
                                              "order by AVG_global_sales DESC;";
    public static final String TASK_2_QUERY = "select (select name " +
                                              "        from names " +
                                              "        where names.id = records.name) as game_name, " +
                                              "       max(eu_Sales) as max_eu_sale " +
                                              "from records " +
                                              "where records.year = " +
                                              "      (select id " +
                                              "       from years " +
                                              "       where year = 2000);";
    public static final String TASK_3_QUERY = "select (select name " +
                                              "        from names " +
                                              "        where names.id = records.name) as game_name, " +
                                              "       max(jp_Sales)                   as max_jp_sale " +
                                              "from records " +
                                              "where (records.year BETWEEN " +
                                              "    (select id from years where year = 2006) " +
                                              "    and " +
                                              "    (select id from years where year = 2000)) " +
                                              "  and genre = (select id " +
                                              "               from genres " +
                                              "               where genres.genre = 'Sports');";

    private Constants() {
    }
}
