package api;

import db.dbSetup;

import java.io.*;
import java.util.*;
import java.sql.*;

public class QueryBuilder {
    static Connection conn = null;

    public static void openDBConnection() {
        getDBConnection();
    }

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

    static Connection getDBConnection() {
        if (QueryBuilder.conn == null) {
            try {
                //Class.forName("org.postgresql.Driver");
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

    static ArrayList<HashMap<String, String>> executeQuery(String sqlStatement) throws SQLException {
        ResultSet result = null;

        System.out.println(sqlStatement);

        try {
            //create a statement object
            Statement stmt = getDBConnection().createStatement();
            //send statement to DBMS
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

    static Integer executeUpdate(String sqlStatement) {
        Integer result = null;

        System.out.println(sqlStatement);

        try {
            //create a statement object
            Statement stmt = getDBConnection().createStatement();
            //send statement to DBMS
            result = stmt.executeUpdate(sqlStatement);
        } catch (Exception e) {
            System.out.println("Error accessing Database.");
        }

        return result;
    }

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

    static String buildInsertionQuery(String table, HashMap<String, String> values) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ").append(table).append(" (");

        for (Map.Entry<String, String> entry : values.entrySet()) {
            String key = entry.getKey();
            stringBuilder.append(String.format("\"%s\", ", key));
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append(") VALUES (");

        for (Map.Entry<String, String> entry : values.entrySet()) {
            String value = entry.getValue();
            stringBuilder.append(String.format("'%s', ", value));
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append(");");

        return stringBuilder.toString();
    }

    static String buildGetLastItemFromTableQuery(String table) {
        return "SELECT * FROM " + table +
                " ORDER BY \"id\" DESC LIMIT 1;";
    }

    static String buildGetOrdersFromDateRangeQuery(String date1, String date2, Integer customerID, Integer limit) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM \"").append(Order.tableName).append("\" WHERE \"")
                .append(Order.date_column).append("\" BETWEEN '").append(date1)
                .append("' AND '").append(date2);

        if (customerID != null) {
            stringBuilder.append("' AND \"").append(Order.customer_id_column).append("\" = ").append(customerID);
        } else {
            stringBuilder.append("'");
        }

        if (limit != null) {
            stringBuilder.append(" LIMIT ").append(limit);
        }

        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        QueryBuilder.openDBConnection();

        ArrayList<Item> allBeverages = Beverage.getAllItems();
        ArrayList<Item> allDesserts = Dessert.getAllItems();
        ArrayList<Item> allEntrees = Entree.getAllItems();
        ArrayList<Item> allMeals = Meal.getAllItems();
        ArrayList<Item> allSides = Side.getAllItems();
        ArrayList<Item> allToppings = Topping.getAllItems();

        ArrayList<Item> recommendations = Customer.getCustomerRecommendations(Customer.getCustomerByName("Brennan"), 3);

        QueryBuilder.closeDBConnection();
    }
}
