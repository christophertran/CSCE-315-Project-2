package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Meal extends Item{
    static final String tableName = "meals";
    static final String contents_column = "contents";

    private ArrayList<Item> contents;

    public ArrayList<Item> getContents() {
        return contents;
    }

    /**
     * Sets contents but takes string as param instead
     * @param contents Stringified contents
     * @return Integer that represents success of update. > 0 is success.
     * @throws SQLException Throws SQLException
     */
    public Integer setContents(String contents) throws SQLException {
        return setContents(Item.getItemsFromString(contents));
    }

    /**
     * Sets contents of Meal, also updates information in the database
     *
     * @param contents ArrayList of new contents
     * @return Integer that represents success of update. > 0 is success.
     */
    public Integer setContents(ArrayList<Item> contents) {
        this.contents = contents;

        HashMap<String, String> values = new HashMap<>();
        HashMap<String, String> constraints = new HashMap<>();

        values.put(Meal.contents_column, Item.getItemsAsString(this.contents));

        constraints.put(Meal.name_column, this.getName());

        return QueryBuilder.executeUpdate(QueryBuilder.buildUpdateQuery(Item.getTableNameFromItemName(this.getName()), values, constraints));
    }

    Meal(int id, String name, float price, int calories, String contents) throws SQLException {
        super(id, name, price, calories);
        this.contents = Item.getItemsFromString(contents);
    }

    /**
     * Return all items from meals table in the database
     * @return ArrayList of all meal items in database
     * @throws SQLException Throws SQLException
     */
    public static ArrayList<Item> getAllItems() throws SQLException {
        ArrayList<HashMap<String, String>> queryResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Meal.tableName, null, null));
        return Item.getItemsFromQueryResult(queryResult);
    }

    /**
     * Overloaded version of item's createButtonHTML, meant to return meal specific HTML information
     * @param item Meal item to have html information created for
     * @return HTML code in a string displaying given item's information
     */
    public static String createButtonHTML(Item item) {
        StringBuilder stringBuilder = new StringBuilder();

        if (item != null) {
            Meal meal = (Meal) item;

            stringBuilder.append(String.format("<html><center>\n%s<br><br>", meal.getFullName()));

            for (Item i : meal.getContents()) {
                stringBuilder.append(String.format("\n%s<br>", i.getFullName()));
            }

            stringBuilder.append(String.format("<br>\nPrice: $%s, Calories %s\n</center>\n</html>", meal.getPrice().toString(), meal.getCalories().toString()));
        }

        return stringBuilder.toString();
    }
}
