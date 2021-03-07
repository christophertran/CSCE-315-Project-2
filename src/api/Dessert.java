package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Dessert extends Item {
    static final String tableName = "desserts";

    Dessert(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }

    static ArrayList<Dessert> getAllItems() throws SQLException {
        ArrayList<Dessert> items = new ArrayList<>();
        ArrayList<HashMap<String, String>> itemsDict = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Dessert.tableName, null));
        for (HashMap h : itemsDict) {
            Dessert i = new Dessert(Integer.parseInt((String) h.get(Dessert.id_column)),
                    (String) h.get(Dessert.name_column),
                    Float.parseFloat((String) h.get(Dessert.price_column)),
                    Integer.parseInt((String) h.get(Dessert.calories_column)));
            items.add(i);
        }
        return items;
    }
}
