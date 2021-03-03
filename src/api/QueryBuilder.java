package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QueryBuilder {
    static String buildSelectionQuery(ArrayList<String> columns, String table, HashMap<String, String> constraints) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");

        // If all columns, add * to query
        if (columns.size() == 1 && columns.get(0).equals("*")) {
            stringBuilder.append("*");
        // Otherwise, add the columns separated by commas
        } else {
            for (int i = 0; i < columns.size(); i++) {
                stringBuilder.append(columns.get(i));
                if (i != columns.size() - 1)
                    stringBuilder.append(", ");
            }
        }
        stringBuilder.append(" FROM ").append(table);

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

    public static void main(String[] args) {
        ArrayList<String> cols = new ArrayList<>();
        HashMap<String, String> constraints = new HashMap<>();
        constraints.put("id", "5");
        constraints.put("name", "Joe");
        cols.add("name");
        System.out.println(buildSelectionQuery(cols, "beverages", constraints));
    }
}
