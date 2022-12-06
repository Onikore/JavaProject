package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static main.java.Constants.*;

public class DBManager {
    private DBManager(){
    }

    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(JDBC_URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void createDB() {
        try (var conn = getConnection()) {
            var statement = conn.createStatement();
            statement.execute(CREATE_YEARS_TABLE);
            statement.execute(CREATE_GENRES_TABLE);
            statement.execute(CREATE_NAMES_TABLE);
            statement.execute(CREATE_PLATFORMS_TABLE);
            statement.execute(CREATE_PUBLISHERS_TABLE);
            statement.execute(CREATE_RECORDS_TABLE);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertSimples(String table, String value) {
        var sql = "insert into " + table + " values (?,?);";
        try (var conn = getConnection()) {
            var preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(2, value);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("INCORRECT " + value);
            e.printStackTrace();
        }
    }

    public static void insertBig(Record value) {
        try (var conn = getConnection()) {
            var preparedStatement = conn.prepareStatement(INSERT_RECORDS);
            preparedStatement.setInt(2, value.rank);
            preparedStatement.setInt(3, getId(INSERT_NAMES, value.name));
            preparedStatement.setInt(4, getId(INSERT_PLATFORMS, value.platform));
            preparedStatement.setInt(5, getId(INSERT_YEARS, value.year));
            preparedStatement.setInt(6, getId(INSERT_GENRES, value.genre));
            preparedStatement.setInt(7, getId(INSERT_PUBLISHERS, value.publisher));
            preparedStatement.setDouble(8, value.naSales);
            preparedStatement.setDouble(9, value.euSales);
            preparedStatement.setDouble(10, value.jpSales);
            preparedStatement.setDouble(11, value.otherSales);
            preparedStatement.setDouble(12, value.globalSales);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getId(String query, String value) {
        var res = 0;
        try (var conn = getConnection()) {
            var preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, value);
            var resSet = preparedStatement.executeQuery();
            while (resSet.next()) {
                res = resSet.getInt(1);
            }
            preparedStatement.close();
            resSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
