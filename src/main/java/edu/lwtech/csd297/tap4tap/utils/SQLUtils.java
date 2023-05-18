package edu.lwtech.csd297.tap4tap.utils;

import java.sql.*;
import java.util.*;

import org.apache.logging.log4j.*;

public class SQLUtils {

    private static final Logger logger = LogManager.getLogger(SQLUtils.class);

    private SQLUtils() { }                                          // Hide the implicit public constructor

    public static Connection connect(String initParams) {
        logger.debug("Connecting to {}...", initParams);

        String driverClass = "org.mariadb.jdbc.Driver";
        try {
            Class.forName(driverClass);                             // Dynamically loads the driver from the WAR file
        } catch (ClassNotFoundException e) {
            logger.error("Unable to find JDBC driver on classpath: {}", driverClass , e);
            return null;
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(initParams);
        } catch (SQLException e) {
            logger.error("Unable to connect to SQL Database with: {}", initParams, e);
            return null;
        }

        logger.debug("Connected!");
        return conn;
    }

    public static ResultSet executeSelect(Connection conn, String query, String... arguments) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement(query); ) {
            int position = 1;
            for (String arg : arguments) {
                stmt.setString(position++, arg);
            }

            return stmt.executeQuery();
        }
    }

    public static List<SQLRow> executeSQL(Connection conn, String query, String... arguments) {
        logger.debug("Executing SQL statement: {}", query);

        try ( PreparedStatement stmt = conn.prepareStatement(query); ) {
            // Substitute in the argument values for the question marks
            int position = 1;
            for (String arg : arguments) {
                stmt.setString(position++, arg);
            }

            query = query.toLowerCase();
            if (query.contains("update ") || query.contains("delete ")) {

                int numRows = stmt.executeUpdate();
                return results(numRows);

            } else if (query.contains("select ")) {

                // Execute the SELECT query
                ResultSet sqlResults = stmt.executeQuery();

                // Get the column names
                ResultSetMetaData md = sqlResults.getMetaData();
                List<String> columns = new ArrayList<>();
                for (int i=0; i < md.getColumnCount(); i++) {
                    columns.add(md.getColumnName(i+1));
                }
                // Store each row in a List
                List<SQLRow> rows = new ArrayList<>();
                while (sqlResults.next()) {
                    SQLRow row = new SQLRow(columns, sqlResults);
                    rows.add(row);
                }

                return rows;
            }
        } catch (SQLException e) {
            logger.error("SQL Exception caught in executeSQL: {}", query, e);
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }


    public static String executeSQLInsert(Connection conn, String query, String id, String... arguments) {
        logger.debug("Executing SQL Insert: {}", query);

        String newID = "";
        String[] returnColumns = new String[] { id };

        try ( PreparedStatement stmt = conn.prepareStatement(query, returnColumns); ) {
            // Create the new statement object, specifying the recID return column as well

            // Substitute in the argument values for the question marks
            for (int i = 1; i < 13 ; i++)
                stmt.setString(i, arguments[i]);
            for (int i =13; i < 15; i++){
                stmt.setDouble(i, Double.parseDouble(arguments[i]));
            }
            // Execute the INSERT statement
            stmt.executeUpdate();

            // Get the new recID value from the query results and return it to the caller
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            newID = keys.getString(1);
        } catch (SQLException e) {
            logger.error("SQL Exception caught in executeSQLInsert: {}", query, e);
            return null;
        }

        return newID;
    }

    public static void disconnect(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            logger.error("Exception thrown while trying to close SQL Connection", e);
        }
    }

    // ===============================================================================================

    private static List<SQLRow> results(int i) {
        List<SQLRow> rows = new ArrayList<>();
        rows.add(new SQLRow("Value", i));
        return rows;
    }

}