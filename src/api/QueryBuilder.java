package api;

import db.dbSetup;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.sql.*;

public class QueryBuilder {
    static Connection conn = null;

    /**
     * Explicitly open the connection to the SQL database
     */
    public static void openDBConnection() {
        getDBConnection();
    }

    /**
     * Explicitly close the connection to the SQL database
     */
    public static void closeDBConnection() {
        if (QueryBuilder.conn != null) {
            try {
                QueryBuilder.conn.close();
                System.out.println("Connection Closed.");
            } catch (Exception e) {
                System.out.println("Connection NOT Closed.");
            }
        }
    }

    /**
     * Get a Connection object that is linked to the SQL database
     *
     * @return Connection object
     */
    static Connection getDBConnection() {
        if (QueryBuilder.conn == null) {
            try {
                // Class.forName("org.postgresql.Driver");
                QueryBuilder.conn = DriverManager.getConnection(
                        "jdbc:postgresql://csce-315-db.engr.tamu.edu/db907_group10_project2",
                        dbSetup.user, dbSetup.pswd);
                System.out.println("Connection Opened.");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }

        return QueryBuilder.conn;
    }

    /**
     * Executes a regular SQL query
     *
     * @param sqlStatement SQL query that is meant to be executed
     * @return Returns and ArrayList of HashMaps that contains information from the result set
     * @throws SQLException Throws SQLExceptions
     */
    static ArrayList<HashMap<String, String>> executeQuery(String sqlStatement) throws SQLException {
        ResultSet result = null;

        // System.out.println(sqlStatement);

        try {
            // create a statement object
            Statement stmt = getDBConnection().createStatement();
            // send statement to DBMS
            result = stmt.executeQuery(sqlStatement);
        } catch (Exception e) {
            System.out.println("Error accessing Database.");
        }

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        if (result != null) {
            ResultSetMetaData md = result.getMetaData();
            int columns = md.getColumnCount();
            while (result.next()) {
                HashMap<String, String> row = new HashMap<>(columns);
                for (int i = 1; i <= columns; ++i) {
                    row.put(md.getColumnName(i), result.getString(i));
                }
                list.add(row);
            }
        }

        return list;
    }

    /**
     * Executes a update SQL Query
     * @param sqlStatement SQL query that is meant to be executed
     * @return Integer that represents how many rows were changed in the database
     */
    static Integer executeUpdate(String sqlStatement) {
        Integer result = null;

        // System.out.println(sqlStatement);

        try {
            // create a statement object
            Statement stmt = getDBConnection().createStatement();
            // send statement to DBMS
            result = stmt.executeUpdate(sqlStatement);
        } catch (Exception e) {
            System.out.println("Error accessing Database.");
        }

        return result;
    }

    /**
     * Creates a SQL Select * query, where constraints is the mapping of key to value
     *
     * @param table Name of table in database
     * @param constraints "WHERE" constraints of query
     * @param limit Add the "LIMIT" clause to the query
     * @return String that represents the SQL selection query created
     */
    static String buildSelectionQuery(String table, HashMap<String, String> constraints, Integer limit) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ").append(table);

        // Add 'WHERE' clause from constraints parameter
        if (constraints != null) {
            stringBuilder.append(" WHERE ");
            int constraintsSize = constraints.size();
            int i = 0;
            for (Map.Entry<String, String> entry : constraints.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                stringBuilder.append(String.format("\"%s\" = '%s'", key, value));
                if (i != constraintsSize - 1)
                    stringBuilder.append(", ");
                i++;
            }
        }

        if (limit != null) {
            stringBuilder.append(" LIMIT ").append(limit);
        }

        stringBuilder.append(";");

        return stringBuilder.toString();
    }

    /**
     * Creates a SQL "INSERT INTO" statement
     *
     * @param table Name of table to be inserted into
     * @param values Map of column name to values in those columns
     * @return String that represents the SQL insertion query created
     */
    static String buildInsertionQuery(String table, HashMap<String, String> values) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("INSERT INTO \"%s\" ( "));

        for (Map.Entry<String, String> entry : values.entrySet()) {
            stringBuilder.append(String.format(" \"%s\", ", entry.getKey()));
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append(" ) VALUES ( ");

        for (Map.Entry<String, String> entry : values.entrySet()) {
            stringBuilder.append(String.format(" '%s', ", entry.getValue()));
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append(" ); ");

        return stringBuilder.toString();
    }

    /**
     * Creates a SQL "UPDATE" query
     *
     * @param table Name of table to be inserted into
     * @param values Map of old value to new value
     * @param constraints "WHERE" constraints of query
     * @return String that represents the SQL update query created
     */
    static String buildUpdateQuery(String table, HashMap<String, String> values, HashMap<String, String> constraints) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("UPDATE \"%s\" SET ", table));

        for (Map.Entry<String, String> entry : values.entrySet()) {
            stringBuilder.append(String.format(" \"%s\" = '%s' ", entry.getKey(), entry.getValue()));
        }

        if (constraints != null) {
            stringBuilder.append(" WHERE ");
            for (Map.Entry<String, String> entry : constraints.entrySet()) {
                stringBuilder.append(String.format(" \"%s\" = '%s' ", entry.getKey(), entry.getValue()));
            }
        }
        stringBuilder.append(";");

        return stringBuilder.toString();
    }

    /**
     * Creates a SQL query to get the last item from the specified table
     *
     * @param table Name of table to have last item taken from
     * @return String that represents the SQL query created
     */
    static String buildGetLastItemFromTableQuery(String table) {
        return "SELECT * FROM " + table +
                " ORDER BY \"id\" DESC LIMIT 1;";
    }

    /**
     * Creates a SQL selection query that takes a BETWEEN clause for the "date" column
     *
     * @param date1 Start of date range
     * @param date2 End of date range
     * @param customerID ID of customers to find range of, if null the clause is ignored
     * @param limit Limit the number of orders returned, if null the clause is ignored
     * @return String that represents the SQL query created
     */
    static String buildGetOrdersFromDateRangeQuery(String date1, String date2, Integer customerID, Integer limit) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("SELECT * FROM \"%s\" WHERE \"%s\" BETWEEN '%s' AND '%s' ", Order.tableName, Order.date_column, date1, date2));

        if (customerID != null) {
            stringBuilder.append(String.format(" AND \"%s\" = %s", Order.customer_id_column, customerID.toString()));
        }

        if (limit != null) {
            stringBuilder.append(" LIMIT ").append(limit);
        }

        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        QueryBuilder.openDBConnection();

        HashMap<String, ArrayList<Item>> trendingItems = Item.getTrendingItems(3);

        System.out.println("Trending Up: ");
        System.out.println(Item.getItemsAsString(trendingItems.get(Item.trending_up_key)));

        System.out.println("Trending Down: ");
        System.out.println(Item.getItemsAsString(trendingItems.get(Item.trending_down_key)));

        QueryBuilder.closeDBConnection();
    }
}
