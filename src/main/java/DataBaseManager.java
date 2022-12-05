package main.java;

import java.sql.*;

import static main.java.Constants.*;

public class DataBaseManager {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static void connect() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(JDBC_URL);
    }

    public static void createDB() {
        try (Statement a = conn.createStatement()) {
            a.execute(CREATE_YEARS_TABLE_QUERY);
            a.execute(CREATE_GENRES_TABLE_QUERY);
            a.execute(CREATE_NAMES_TABLE_QUERY);
            a.execute(CREATE_PLATFORMS_TABLE_QUERY);
            a.execute(CREATE_PUBLISHERS_TABLE_QUERY);
            a.execute(CREATE_RECORDS_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataBaseManager.connect();
        DataBaseManager.createDB();
        DataBaseManager.closeDB();
    }

    public static void closeDB() throws SQLException {
        if (conn != null && !conn.isClosed()) conn.close();
        if (statmt != null && !statmt.isClosed()) statmt.close();
        if (resSet != null && !resSet.isClosed()) resSet.close();
    }
}