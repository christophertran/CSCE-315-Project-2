package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Topping extends Item {
    static final String tableName = "toppings";

    Topping(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }

    public static ArrayList<Topping> getAllItems() throws SQLException {
        ArrayList<Topping> items = new ArrayList<>();
        ArrayList<HashMap<String, String>> itemsDict = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Topping.tableName, null));
        for (HashMap h : itemsDict) {
            Topping i = new Topping(Integer.parseInt((String) h.get(Topping.id_column)),
                    (String) h.get(Topping.name_column),
                    Float.parseFloat((String) h.get(Topping.price_column)),
                    Integer.parseInt((String) h.get(Topping.calories_column)));
            items.add(i);
        }
        return items;
    }
}
