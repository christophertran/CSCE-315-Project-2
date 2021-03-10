package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Beverage extends Item{
    static final String tableName = "beverages";

    Beverage(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }

    public static ArrayList<Item> getAllItems() throws SQLException {
        ArrayList<HashMap<String, String>> queryResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Beverage.tableName, null, null));
        return Item.getItemsFromQueryResult(queryResult);
    }
}
