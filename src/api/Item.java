package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

class Item {
    static final String id_column = "id";
    static final String name_column = "name";
    static final String price_column = "price";
    static final String calories_column = "calories";

    int id;
    String name;
    float price;
    int calories;

    Item(int id, String name, float price, int calories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    static ArrayList<Item> getAllItems(String itemType) throws SQLException {
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<HashMap<String, String>> itemsDict = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(itemType, null));
        for (HashMap h : itemsDict) {
            Item i = new Item(Integer.parseInt((String) h.get(Item.id_column)),
                    (String) h.get(Item.name_column),
                    Float.parseFloat((String) h.get(Item.price_column)),
                    Integer.parseInt((String) h.get(Item.calories_column)));
            items.add(i);
        }
        return items;
    }

    static String getTableNameFromItemName(String itemName) {
        char itemCode = itemName.charAt(0);
        switch (itemCode) {
            case 'B':
                return Beverage.tableName;
            case 'D':
                return Dessert.tableName;
            case 'E':
                return Entree.tableName;
            case 'M':
                return Meal.tableName;
            case 'S':
                return Side.tableName;
            case 'T':
                return Topping.tableName;
        }

        return "";
    }
}