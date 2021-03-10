package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Side extends Item{
    static final String tableName = "sides";

    Side(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }

    public static ArrayList<Item> getAllItems() throws SQLException {
        ArrayList<HashMap<String, String>> queryResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Side.tableName, null, null));
        return Item.getItemsFromQueryResult(queryResult);
    }
}
