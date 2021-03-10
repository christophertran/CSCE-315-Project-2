package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Dessert extends Item {
    static final String tableName = "desserts";

    Dessert(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }

    public static ArrayList<Item> getAllItems() throws SQLException {
        ArrayList<HashMap<String, String>> queryResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Dessert.tableName, null, null));
        return Item.getItemsFromQueryResult(queryResult);
    }
}
