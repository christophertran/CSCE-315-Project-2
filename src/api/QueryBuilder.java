package api;

import db.dbSetup;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class QueryBuilder {

    static ArrayList<HashMap<String, String>> executeQuery(String sqlStatement) throws SQLException {
        dbSetup my = new dbSetup();
        Connection conn = null;
        try {
            //Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://csce-315-db.engr.tamu.edu/db907_group10_project2",
                    my.user, my.pswd);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        ResultSet result = null;
        try {
            //create a statement object
            Statement stmt = conn.createStatement();
            //send statement to DBMS
            result = stmt.executeQuery(sqlStatement);
        } catch (Exception e) {
            System.out.println("Error accessing Database.");
        }
        //closing the connection
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch (Exception e) {
            System.out.println("Connection NOT Closed.");
        }

        ResultSetMetaData md = result.getMetaData();
        int columns = md.getColumnCount();
        ArrayList list = new ArrayList(50);
        while (result.next()){
            HashMap<String, String> row = new HashMap(columns);
            for(int i=1; i<=columns; ++i){
                row.put(md.getColumnName(i), result.getObject(i).toString());
            }
            list.add(row);
        }

        return list;
    }

    static String buildSelectionQuery(String table, HashMap<String, String> constraints) {
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
                stringBuilder.append(String.format("%s = \"%s\"", key, value));
                if (i != constraintsSize - 1)
                    stringBuilder.append(", ");
                i++;
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) throws SQLException {
//        ArrayList<String> cols = new ArrayList<>();
//        HashMap<String, String> constraints = new HashMap<>();
//        constraints.put("id", "5");
//        constraints.put("name", "Joe");
//        cols.add("name");
//        System.out.println(buildSelectionQuery(cols, "beverages", constraints));
        ArrayList<HashMap<String, String>> result = executeQuery("SELECT * FROM entrees");
        ArrayList<Item> items = Item.getAllItems("entrees");
        System.out.println();
    }
}
