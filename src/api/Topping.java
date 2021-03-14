package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Topping extends Item {
    static final String tableName = "toppings";

    Topping(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }

    /**
     * Return all items from toppings table in the database
     * @return ArrayList of all topping items in database
     * @throws SQLException Throws SQLException
     */
    public static ArrayList<Item> getAllItems() throws SQLException {
        ArrayList<HashMap<String, String>> queryResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Topping.tableName, null, null, null));
        return Item.getItemsFromQueryResult(queryResult);
    }
}
