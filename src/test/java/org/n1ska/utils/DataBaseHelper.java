package org.n1ska.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataBaseHelper {
    private DataBaseHelper() {

    }

    public static void deleteAllCreditRequestEntityTableRecords() {
        try (Connection conn = DataBaseHelper.GetConnection();
             Statement stmt = conn.createStatement();) {
            stmt.executeUpdate("DELETE FROM credit_request_entity");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllPaymentEntityTableRecords() {
        try (Connection conn = DataBaseHelper.GetConnection();
             Statement stmt = conn.createStatement();) {
            stmt.executeUpdate("DELETE FROM payment_entity");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCountApprovedCreditRequestEntities() {
        try (Connection conn = DataBaseHelper.GetConnection();
             Statement stmt = conn.createStatement();) {
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM credit_request_entity WHERE STATUS='APPROVED'")) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getCountApprovedPaymentEntities() {
        try (Connection conn = DataBaseHelper.GetConnection();
             Statement stmt = conn.createStatement();) {
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM payment_entity WHERE STATUS='APPROVED'")) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static Connection GetConnection() throws SQLException {
        String dataBaseUrl;
        String dataBaseUserName;
        String dataBaseUserPass;

        Properties props = new Properties();
        try {
            try (var inStream = new FileInputStream("artifacts/application.properties")) {
                props.load(inStream);
                dataBaseUrl = props.getProperty("spring.datasource.url");
                dataBaseUserName = props.getProperty("spring.datasource.username");
                dataBaseUserPass = props.getProperty("spring.datasource.password");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return  DriverManager.getConnection(dataBaseUrl, dataBaseUserName, dataBaseUserPass);
    }

}
