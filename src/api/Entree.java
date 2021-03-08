package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Entree extends Item {
    static final String tableName = "entrees";
    static final String toppings_column = "toppings";

    ArrayList<Topping> toppings;

    Entree(int id, String name, float price, int calories, String toppings) throws SQLException {
        super(id, name, price, calories);

        this.toppings = new ArrayList<>();

        Scanner sc = new Scanner(toppings);
        sc.useDelimiter(" ");

        while (sc.hasNext()) {
            String toppingName = sc.next();

            HashMap<String, String> constraints = new HashMap<>();

            constraints.put(Topping.name_column, toppingName);
            ArrayList<HashMap<String, String>> result = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Item.getTableNameFromItemName(toppingName), constraints));

            if (result.size() > 0) {
                this.toppings.add(new Topping(Integer.parseInt(result.get(0).get(Topping.id_column)), result.get(0).get(Topping.name_column), Float.parseFloat(result.get(0).get(Topping.price_column)), Integer.parseInt(result.get(0).get(Topping.calories_column))));
            }
        }
    }

    Entree(int id, String name, float price, int calories, ArrayList<Topping> toppings) {
        super(id, name, price, calories);

        this.toppings = toppings;
    }

    public static ArrayList<Entree> getAllItems() throws SQLException {
        ArrayList<Entree> items = new ArrayList<>();
        ArrayList<HashMap<String, String>> itemsDict = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Entree.tableName, null));
        for (HashMap h : itemsDict) {
            Entree i = new Entree(Integer.parseInt((String) h.get(Entree.id_column)),
                    (String) h.get(Entree.name_column),
                    Float.parseFloat((String) h.get(Entree.price_column)),
                    Integer.parseInt((String) h.get(Entree.calories_column)),
                    (String) h.get(Entree.toppings_column));
            items.add(i);
        }
        return items;
    }
}
