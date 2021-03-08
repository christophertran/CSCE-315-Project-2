package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Side extends Item{
    static final String tableName = "sides";

    Side(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }

    public static ArrayList<Side> getAllItems() throws SQLException {
        ArrayList<Side> items = new ArrayList<>();
        ArrayList<HashMap<String, String>> itemsDict = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Side.tableName, null));
        for (HashMap h : itemsDict) {
            Side i = new Side(Integer.parseInt((String) h.get(Side.id_column)),
                    (String) h.get(Side.name_column),
                    Float.parseFloat((String) h.get(Side.price_column)),
                    Integer.parseInt((String) h.get(Side.calories_column)));
            items.add(i);
        }
        return items;
    }
}
