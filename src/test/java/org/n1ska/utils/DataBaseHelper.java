package org.n1ska.utils;

import java.sql.*;

public class DataBaseHelper {
    private DataBaseHelper() {

    }

    public static void deleteAllCreditRequestEntityTableRecords(String dataBaseUrl, String dataBaseUserName, String dataBaseUserPass) {
        try (Connection conn = DriverManager.getConnection(dataBaseUrl, dataBaseUserName, dataBaseUserPass);
             Statement stmt = conn.createStatement();) {
            stmt.executeUpdate("DELETE FROM credit_request_entity");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllPaymentEntityTableRecords(String dataBaseUrl, String dataBaseUserName, String dataBaseUserPass) {
        try (Connection conn = DriverManager.getConnection(dataBaseUrl, dataBaseUserName, dataBaseUserPass);
             Statement stmt = conn.createStatement();) {
            stmt.executeUpdate("DELETE FROM payment_entity");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCountApprovedCreditRequestEntities(String dataBaseUrl, String dataBaseUserName, String dataBaseUserPass) {
        try (Connection conn = DriverManager.getConnection(dataBaseUrl, dataBaseUserName, dataBaseUserPass);
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

    public static int getCountApprovedPaymentEntities(String dataBaseUrl, String dataBaseUserName, String dataBaseUserPass) {
        try (Connection conn = DriverManager.getConnection(dataBaseUrl, dataBaseUserName, dataBaseUserPass);
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

}
