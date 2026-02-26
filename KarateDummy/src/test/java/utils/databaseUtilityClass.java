package utils;

import java.sql.*;
import java.util.*;

public class databaseUtilityClass {

    private static String pgUrl = "jdbc:postgresql://localhost:5432/testdb";
    private static String pgUser = "postgres";
    private static String pgPassword = "password";

    private static String oracleUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String oracleUser = "system";
    private static String oraclePassword = "password";

    public static List<Map<String, Object>> queryPostgres(String query) {
        return runQuery(pgUrl, pgUser, pgPassword, query);
    }

    public static List<Map<String, Object>> queryOracle(String query) {
        return runQuery(oracleUrl, oracleUser, oraclePassword, query);
    }

    private static List<Map<String, Object>> runQuery(String url, String user, String password, String query) {
        List<Map<String, Object>> results = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= colCount; i++) {
                    row.put(meta.getColumnLabel(i), rs.getObject(i));
                }
                results.add(row);
            }
        } catch (Exception e) {
            throw new RuntimeException("DB Query Failed: " + e.getMessage(), e);
        }
        return results;
    }
}
