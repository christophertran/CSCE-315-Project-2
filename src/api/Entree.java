package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Entree extends Item {
    static final String tableName = "entrees";
    static final String toppings_column = "toppings";

    private ArrayList<Item> toppings;

    public ArrayList<Item> getToppings() {
        return toppings;
    }

    /**
     * Sets toppings but takes string as param instead
     * @param toppings Stringified toppings
     * @return Integer that represents success of update. > 0 is success.
     * @throws SQLException Throws SQLException
     */
    public Integer setToppings(String toppings) throws SQLException {
        return setToppings(Item.getItemsFromString(toppings));
    }

    /**
     * Sets toppings of Entree, also updates information in the database
     *
     * @param toppings ArrayList of new contents
     * @return Integer that represents success of update. > 0 is success.
     */
    public Integer setToppings(ArrayList<Item> toppings) {
        this.toppings = toppings;

        HashMap<String, String> values = new HashMap<>();
        HashMap<String, String> constraints = new HashMap<>();

        values.put(Entree.toppings_column, Item.getItemsAsString(this.toppings));

        constraints.put(Entree.name_column, this.getName());

        return QueryBuilder.executeUpdate(QueryBuilder.buildUpdateQuery(Item.getTableNameFromItemName(this.getName()), values, constraints));
    }

    Entree(int id, String name, float price, int calories, String toppings) throws SQLException {
        super(id, name, price, calories);
        this.toppings = Item.getItemsFromString(toppings);
    }

    /**
     * Return all items from entrees table in the database
     * @return ArrayList of all entree items in database
     * @throws SQLException Throws SQLException
     */
    public static ArrayList<Item> getAllItems() throws SQLException {
        ArrayList<HashMap<String, String>> queryResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Entree.tableName, null, null, null));
        return Item.getItemsFromQueryResult(queryResult);
    }
}
