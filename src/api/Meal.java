package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Meal extends Item{
    static final String tableName = "meals";
    static final String contents_column = "contents";

    ArrayList<Item> contents;

    Meal(int id, String name, float price, int calories, String contents) throws SQLException {
        super(id, name, price, calories);

        this.contents = new ArrayList<>();

        Scanner sc = new Scanner(contents);
        sc.useDelimiter(" ");

        while (sc.hasNext()) {
            String itemName = Item.getFullItemNameFromString(sc.next());
            this.contents.add(Item.getItemFromDatabaseByName(itemName));
        }
    }

    public static ArrayList<Item> getAllItems() throws SQLException {
        ArrayList<HashMap<String, String>> queryResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Meal.tableName, null, null));
        return Item.getItemsFromQueryResult(queryResult);
    }
}
