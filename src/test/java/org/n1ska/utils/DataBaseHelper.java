package org.n1ska.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataBaseHelper {
    private DataBaseHelper() {

    }

    public static void deleteAllCreditRequestEntityTableRecordsMySql() {
        try (Connection conn = DataBaseHelper.GetMySqlConnection();
             Statement stmt = conn.createStatement();) {
            stmt.executeUpdate("DELETE FROM credit_request_entity");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllPaymentEntityTableRecordsMySql() {
        try (Connection conn = DataBaseHelper.GetMySqlConnection();
             Statement stmt = conn.createStatement();) {
            stmt.executeUpdate("DELETE FROM payment_entity");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllCreditRequestEntityTableRecordsPostgres() {
        try (Connection conn = DataBaseHelper.GetPostgresConnection();
             Statement stmt = conn.createStatement();) {
            stmt.executeUpdate("DELETE FROM credit_request_entity");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllPaymentEntityTableRecordsPostgres() {
        try (Connection conn = DataBaseHelper.GetPostgresConnection();
             Statement stmt = conn.createStatement();) {
            stmt.executeUpdate("DELETE FROM payment_entity");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static int getCountApprovedCreditRequestEntitiesMySql() {
        try (Connection conn = DataBaseHelper.GetMySqlConnection();
             Statement stmt = conn.createStatement();) {
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM credit_request_entity WHERE STATUS='APPROVED'")) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int getCountApprovedPaymentEntitiesMySql() {
        try (Connection conn = DataBaseHelper.GetMySqlConnection();
             Statement stmt = conn.createStatement();) {
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM payment_entity WHERE STATUS='APPROVED'")) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static int getCountApprovedCreditRequestEntitiesPostgres() {
        try (Connection conn = DataBaseHelper.GetPostgresConnection();
             Statement stmt = conn.createStatement();) {
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM credit_request_entity WHERE STATUS='APPROVED'")) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int getCountApprovedPaymentEntitiesPostgres() {
        try (Connection conn = DataBaseHelper.GetPostgresConnection();
             Statement stmt = conn.createStatement();) {
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM payment_entity WHERE STATUS='APPROVED'")) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    private static Connection GetMySqlConnection() throws SQLException {
        String dataBaseUserName;
        String dataBaseUserPass;

        Properties props = new Properties();
        try {
            try (var inStream = new FileInputStream("artifacts/aqa-shop-mysql/application.properties")) {
                props.load(inStream);
                dataBaseUserName = props.getProperty("spring.datasource.username");
                dataBaseUserPass = props.getProperty("spring.datasource.password");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return  DriverManager.getConnection("jdbc:mysql://localhost:3306/app", dataBaseUserName, dataBaseUserPass);
    }

    private static Connection GetPostgresConnection() throws SQLException {
        String dataBaseUserName;
        String dataBaseUserPass;

        Properties props = new Properties();
        try {
            try (var inStream = new FileInputStream("artifacts/aqa-shop-mysql/application.properties")) {
                props.load(inStream);
                dataBaseUserName = props.getProperty("spring.datasource.username");
                dataBaseUserPass = props.getProperty("spring.datasource.password");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return  DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", dataBaseUserName, dataBaseUserPass);
    }

}
