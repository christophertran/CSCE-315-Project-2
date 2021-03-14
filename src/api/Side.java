package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Side extends Item{
    static final String tableName = "sides";

    Side(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }

    /**
     * Return all items from sides table in the database
     * @return ArrayList of all side items in database
     * @throws SQLException Throws SQLException
     */
    public static ArrayList<Item> getAllItems() throws SQLException {
        ArrayList<HashMap<String, String>> queryResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Side.tableName, null, null, null));
        return Item.getItemsFromQueryResult(queryResult);
    }
}
