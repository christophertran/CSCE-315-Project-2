package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Entree extends Item {
    static final String tableName = "entrees";
    static final String toppings_column = "toppings";

    ArrayList<Item> toppings;

    Entree(int id, String name, float price, int calories, String toppings) throws SQLException {
        super(id, name, price, calories);

        this.toppings = new ArrayList<>();

        Scanner sc = new Scanner(toppings);
        sc.useDelimiter(" ");

        while (sc.hasNext()) {
            String toppingName = sc.next();
            this.toppings.add(Item.getItemFromDatabaseByName(toppingName));
        }
    }

    public static ArrayList<Item> getAllItems() throws SQLException {
        ArrayList<HashMap<String, String>> itemsDict = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Entree.tableName, null, null));
        return Item.getItemsFromQueryResult(itemsDict);
    }
}
