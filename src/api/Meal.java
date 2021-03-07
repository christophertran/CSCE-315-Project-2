package api;

import java.sql.Array;
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
            String contentName = sc.next();

            HashMap<String, String> constraints = new HashMap<>();

            constraints.put(Item.name_column, contentName);

            ArrayList<HashMap<String, String>> result = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Item.getTableNameFromItemName(contentName), constraints));

            if (result.size() > 0) {
                char itemCode = contentName.charAt(0);
                switch (itemCode) {
                    case 'B':
                        this.contents.add(new Beverage(Integer.parseInt(result.get(0).get(Beverage.id_column)),
                                result.get(0).get(Beverage.name_column),
                                Float.parseFloat(result.get(0).get(Beverage.price_column)),
                                Integer.parseInt(result.get(0).get(Beverage.calories_column))));
                        break;
                    case 'D':
                        this.contents.add(new Dessert(Integer.parseInt(result.get(0).get(Dessert.id_column)),
                                result.get(0).get(Dessert.name_column),
                                Float.parseFloat(result.get(0).get(Dessert.price_column)),
                                Integer.parseInt(result.get(0).get(Dessert.calories_column))));
                        break;
                    case 'E':
                        this.contents.add(new Entree(Integer.parseInt(result.get(0).get(Entree.id_column)),
                                result.get(0).get(Entree.name_column),
                                Float.parseFloat(result.get(0).get(Entree.price_column)),
                                Integer.parseInt(result.get(0).get(Entree.calories_column)),
                                result.get(0).get(Entree.toppings_column)));
                        break;
                    case 'S':
                        this.contents.add(new Side(Integer.parseInt(result.get(0).get(Side.id_column)),
                                result.get(0).get(Side.name_column),
                                Float.parseFloat(result.get(0).get(Side.price_column)),
                                Integer.parseInt(result.get(0).get(Side.calories_column))));
                        break;
                    case 'T':
                        this.contents.add(new Topping(Integer.parseInt(result.get(0).get(Topping.id_column)),
                                result.get(0).get(Topping.name_column),
                                Float.parseFloat(result.get(0).get(Topping.price_column)),
                                Integer.parseInt(result.get(0).get(Topping.calories_column))));
                        break;
                }
            }
        }
    }

    Meal(int id, String name, float price, int calories, ArrayList<Item> contents) {
        super(id, name, price, calories);

        this.contents = contents;
    }

    static ArrayList<Meal> getAllItems() throws SQLException {
        ArrayList<Meal> items = new ArrayList<>();
        ArrayList<HashMap<String, String>> itemsDict = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Meal.tableName, null));
        for (HashMap h : itemsDict) {
            Meal i = new Meal(Integer.parseInt((String) h.get(Meal.id_column)),
                    (String) h.get(Meal.name_column),
                    Float.parseFloat((String) h.get(Meal.price_column)),
                    Integer.parseInt((String) h.get(Meal.calories_column)),
                    (String) h.get(Meal.contents_column));
            items.add(i);
        }
        return items;
    }
}
