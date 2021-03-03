package api;

import java.util.ArrayList;

public class QueryBuilder {
    static String buildSelectionQuery(ArrayList<String> columns, String table) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        for (int i = 0; i < columns.size(); i++) {
            stringBuilder.append(columns.get(i));
            if (i != columns.size() - 1)
                stringBuilder.append(", ");
        }
        stringBuilder.append(" FROM " + table);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ArrayList<String> cols = new ArrayList<>();
        cols.add("name");
        System.out.println(buildSelectionQuery(cols, "beverages"));
    }
}
