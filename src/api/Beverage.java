package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Beverage extends Item{
    static final String tableName = "beverages";

    Beverage(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }

    public static ArrayList<Beverage> getAllItems() throws SQLException {
        ArrayList<Beverage> items = new ArrayList<>();
        ArrayList<HashMap<String, String>> itemsDict = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Beverage.tableName, null));
        for (HashMap h : itemsDict) {
            Beverage i = new Beverage(Integer.parseInt((String) h.get(Beverage.id_column)),
                    (String) h.get(Beverage.name_column),
                    Float.parseFloat((String) h.get(Beverage.price_column)),
                    Integer.parseInt((String) h.get(Beverage.calories_column)));
            items.add(i);
        }
        return items;
    }
}
