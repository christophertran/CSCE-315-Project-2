package api;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class Item {
    static final String id_column = "id";
    static final String name_column = "name";
    static final String price_column = "price";
    static final String calories_column = "calories";

    Integer id;
    String name;
    Float price;
    Integer calories;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public Integer getCalories() {
        return calories;
    }

    /**
     * Set the price of the current item. This will also update the item's information in the database
     *
     * @param price New price of item
     * @return Integer representing success of database update. > 0 is success.
     */
    public Integer setPrice(float price) {
        this.price = price;

        HashMap<String, String> values = new HashMap<>();
        HashMap<String, String> constraints = new HashMap<>();

        values.put(Item.price_column, this.price.toString());

        constraints.put(Item.name_column, this.name);

        return QueryBuilder.executeUpdate(QueryBuilder.buildUpdateQuery(Item.getTableNameFromItemName(this.name), values, constraints));
    }

    /**
     * Set the calories of the current item. This will also update the item's information in the database
     *
     * @param calories New price of item
     * @return Integer representing success of database update. > 0 is success.
     */
    public Integer setCalories(int calories) {
        this.calories = calories;

        HashMap<String, String> values = new HashMap<>();
        HashMap<String, String> constraints = new HashMap<>();

        values.put(Item.calories_column, this.calories.toString());

        constraints.put(Item.name_column, this.name);

        return QueryBuilder.executeUpdate(QueryBuilder.buildUpdateQuery(Item.getTableNameFromItemName(this.name), values, constraints));
    }

    Item(Integer id, String name, Float price, Integer calories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    public static ArrayList<Item> getTrendingUpAndDownItems() throws  SQLException {
        ArrayList<Item> ret = new ArrayList<>();

        String currentDate = LocalDate.now().toString();
        String currentDataMinusTwoWeeks = LocalDate.parse(currentDate).minusWeeks(2).toString();

        ArrayList<HashMap<String, String>> queryResult = QueryBuilder.executeQuery(QueryBuilder.buildGetOrdersFromDateRangeQuery(currentDataMinusTwoWeeks, currentDate, null, 15));

        ArrayList<String> previousOrderContentsNames = new ArrayList<>();
        ArrayList<Item> previousOrderContents = new ArrayList<>();
        HashMap<String, Item> nameToItemMap = new HashMap<>();
        for (HashMap<String, String> order : queryResult) {
            Scanner sc = new Scanner(order.get(Order.contents_column));
            sc.useDelimiter(" ");

            while (sc.hasNext()) {
                String contentName = sc.next().substring(0, 2);

                HashMap<String, String> constraints = new HashMap<>();

                previousOrderContentsNames.add(contentName);
                constraints.put(Item.name_column, contentName);

                ArrayList<HashMap<String, String>> result = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Item.getTableNameFromItemName(contentName), constraints, null));

                if (result.size() > 0) {
                    char itemCode = contentName.charAt(0);
                    switch (itemCode) {
                        case 'B':
                            previousOrderContents.add(new Beverage(Integer.parseInt(result.get(0).get(Beverage.id_column)),
                                    result.get(0).get(Beverage.name_column),
                                    Float.parseFloat(result.get(0).get(Beverage.price_column)),
                                    Integer.parseInt(result.get(0).get(Beverage.calories_column))));
                            break;
                        case 'D':
                            previousOrderContents.add(new Dessert(Integer.parseInt(result.get(0).get(Dessert.id_column)),
                                    result.get(0).get(Dessert.name_column),
                                    Float.parseFloat(result.get(0).get(Dessert.price_column)),
                                    Integer.parseInt(result.get(0).get(Dessert.calories_column))));
                            break;
                        case 'E':
                            previousOrderContents.add(new Entree(Integer.parseInt(result.get(0).get(Entree.id_column)),
                                    result.get(0).get(Entree.name_column),
                                    Float.parseFloat(result.get(0).get(Entree.price_column)),
                                    Integer.parseInt(result.get(0).get(Entree.calories_column)),
                                    result.get(0).get(Entree.toppings_column)));
                            break;
                        case 'S':
                            previousOrderContents.add(new Side(Integer.parseInt(result.get(0).get(Side.id_column)),
                                    result.get(0).get(Side.name_column),
                                    Float.parseFloat(result.get(0).get(Side.price_column)),
                                    Integer.parseInt(result.get(0).get(Side.calories_column))));
                            break;
                        case 'T':
                            previousOrderContents.add(new Topping(Integer.parseInt(result.get(0).get(Topping.id_column)),
                                    result.get(0).get(Topping.name_column),
                                    Float.parseFloat(result.get(0).get(Topping.price_column)),
                                    Integer.parseInt(result.get(0).get(Topping.calories_column))));
                            break;
                    }

                    nameToItemMap.put(contentName, previousOrderContents.get(previousOrderContents.size() - 1));
                }
            }
        }

        HashSet<String> uniqueNames = new HashSet<>(previousOrderContentsNames);
        HashMap<String, Integer> frequencies = new HashMap<>();
        for (String n : uniqueNames) {
            frequencies.computeIfAbsent(n, n1 -> Collections.frequency(previousOrderContentsNames, n1));
        }

        Map.Entry<String, Integer> maxEntry1 = null;
        Map.Entry<String, Integer> minEntry1 = null;

        for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
            if (maxEntry1 == null || entry.getValue() > maxEntry1.getValue()) {
                maxEntry1 = entry;
            }
        }

        frequencies.remove(maxEntry1.getKey());

        for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
            if (minEntry1 == null || entry.getValue() > minEntry1.getValue()) {
                minEntry1 = entry;
            }
        }

        frequencies.remove(minEntry1.getKey());

        Map.Entry<String, Integer> maxEntry2 = null;
        Map.Entry<String, Integer> minEntry2 = null;

        for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
            if (maxEntry2 == null || entry.getValue() > maxEntry2.getValue()) {
                maxEntry2 = entry;
            }
        }

        frequencies.remove(maxEntry2.getKey());

        for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
            if (minEntry2 == null || entry.getValue() < minEntry2.getValue()) {
                minEntry2 = entry;
            }
        }

        frequencies.remove(minEntry2.getKey());

        ret.add(nameToItemMap.get(maxEntry1.getKey()));
        ret.add(nameToItemMap.get(maxEntry2.getKey()));
        ret.add(nameToItemMap.get(minEntry1.getKey()));
        ret.add(nameToItemMap.get(minEntry2.getKey()));

        return ret;
    }

    /**
     * Returns string that is the full name of the item. This is meant for it you have "E1+T2-T3". This will return E1.
     *
     * @param str String that contains the full name of an item at the beginning
     * @return String that represents the full name of an item
     */
    public static String getFullItemNameFromString(String str) {
        return str.substring(0, 2).toUpperCase();
    }

    /**
     * Returns a char that is the item code from the item name given. If given "E1", this returns "E".
     *
     * @param itemName Full name of an item
     * @return char that is the item code
     */
    public static char getItemCodeFromItemName(String itemName) {
        return itemName.charAt(0);
    }

    /**
     * Returns the name of the table associated with an item
     *
     * @param itemName Name of item that table is needed
     * @return String that is the name of a table
     */
    public static String getTableNameFromItemName(String itemName) {
        switch (Item.getItemCodeFromItemName(itemName)) {
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

    /**
     * Return an item created from data in the database given the name of the item
     *
     * @param itemName Name of the item wanted
     * @return Item object that has data from the database
     * @throws SQLException Throws SQLException
     */
    public static Item getItemFromDatabaseByName(String itemName) throws SQLException {
        Item ret = null;

        HashMap<String, String> constraints = new HashMap<>();
        constraints.put(Item.name_column, itemName);

        ArrayList<HashMap<String, String>> result = QueryBuilder.executeQuery(
                QueryBuilder.buildSelectionQuery(Item.getTableNameFromItemName(itemName), constraints, null));

        if (result.size() > 0) {
            switch (Item.getItemCodeFromItemName(itemName)) {
                case 'B':
                    ret = new Beverage(Integer.parseInt(result.get(0).get(Beverage.id_column)),
                            result.get(0).get(Beverage.name_column),
                            Float.parseFloat(result.get(0).get(Beverage.price_column)),
                            Integer.parseInt(result.get(0).get(Beverage.calories_column)));
                    break;
                case 'D':
                    ret = new Dessert(Integer.parseInt(result.get(0).get(Dessert.id_column)),
                            result.get(0).get(Dessert.name_column),
                            Float.parseFloat(result.get(0).get(Dessert.price_column)),
                            Integer.parseInt(result.get(0).get(Dessert.calories_column)));
                    break;
                case 'E':
                    ret = new Entree(Integer.parseInt(result.get(0).get(Entree.id_column)),
                            result.get(0).get(Entree.name_column),
                            Float.parseFloat(result.get(0).get(Entree.price_column)),
                            Integer.parseInt(result.get(0).get(Entree.calories_column)),
                            result.get(0).get(Entree.toppings_column));
                    break;
                case 'M':
                    ret = new Meal(Integer.parseInt(result.get(0).get(Meal.id_column)),
                            result.get(0).get(Meal.name_column),
                            Float.parseFloat(result.get(0).get(Meal.price_column)),
                            Integer.parseInt(result.get(0).get(Meal.calories_column)),
                            result.get(0).get(Meal.contents_column));
                    break;
                case 'S':
                    ret = new Side(Integer.parseInt(result.get(0).get(Side.id_column)),
                            result.get(0).get(Side.name_column),
                            Float.parseFloat(result.get(0).get(Side.price_column)),
                            Integer.parseInt(result.get(0).get(Side.calories_column)));
                    break;
                case 'T':
                    ret = new Topping(Integer.parseInt(result.get(0).get(Topping.id_column)),
                            result.get(0).get(Topping.name_column),
                            Float.parseFloat(result.get(0).get(Topping.price_column)),
                            Integer.parseInt(result.get(0).get(Topping.calories_column)));
                    break;
            }
        }

        return ret;
    }

    /**
     * Gets an ArrayList of Items from the database parsed from the given string
     *
     * @param items String of items that are wanted, space deliminated.
     * @return ArrayList of items created from data in database
     * @throws SQLException Throws SQLException
     */
    public static ArrayList<Item> getItemsFromString(String items) throws SQLException {
        ArrayList<Item> ret = new ArrayList<>();

        Scanner sc = new Scanner(items);
        sc.useDelimiter(" ");

        while (sc.hasNext()) {
            String contentName = Item.getFullItemNameFromString(sc.next());
            ret.add(Item.getItemFromDatabaseByName(contentName));
        }

        return ret;
    }

    /**
     * Gets ArrayList of items given the result dictionary that is returned from an executeQuery call.
     *
     * @param result Result dictionary returned from an executeQuery call
     * @return ArrayList of items in provided result param
     * @throws SQLException Throws SQLException
     */
    public static ArrayList<Item> getItemsFromQueryResult(ArrayList<HashMap<String, String>> result) throws SQLException {
        ArrayList<Item> ret = new ArrayList<>();

        for (HashMap<String, String> h : result) {
            switch (Item.getItemCodeFromItemName(h.get(Item.name_column))) {
                case 'B':
                    ret.add(new Beverage(Integer.parseInt(result.get(0).get(Beverage.id_column)),
                            result.get(0).get(Beverage.name_column),
                            Float.parseFloat(result.get(0).get(Beverage.price_column)),
                            Integer.parseInt(result.get(0).get(Beverage.calories_column))));
                    break;
                case 'D':
                    ret.add(new Dessert(Integer.parseInt(result.get(0).get(Dessert.id_column)),
                            result.get(0).get(Dessert.name_column),
                            Float.parseFloat(result.get(0).get(Dessert.price_column)),
                            Integer.parseInt(result.get(0).get(Dessert.calories_column))));
                    break;
                case 'E':
                    ret.add(new Entree(Integer.parseInt(result.get(0).get(Entree.id_column)),
                            result.get(0).get(Entree.name_column),
                            Float.parseFloat(result.get(0).get(Entree.price_column)),
                            Integer.parseInt(result.get(0).get(Entree.calories_column)),
                            result.get(0).get(Entree.toppings_column)));
                    break;
                case 'M':
                    ret.add(new Meal(Integer.parseInt(result.get(0).get(Meal.id_column)),
                            result.get(0).get(Meal.name_column),
                            Float.parseFloat(result.get(0).get(Meal.price_column)),
                            Integer.parseInt(result.get(0).get(Meal.calories_column)),
                            result.get(0).get(Meal.contents_column)));
                    break;
                case 'S':
                    ret.add(new Side(Integer.parseInt(result.get(0).get(Side.id_column)),
                            result.get(0).get(Side.name_column),
                            Float.parseFloat(result.get(0).get(Side.price_column)),
                            Integer.parseInt(result.get(0).get(Side.calories_column))));
                    break;
                case 'T':
                    ret.add(new Topping(Integer.parseInt(result.get(0).get(Topping.id_column)),
                            result.get(0).get(Topping.name_column),
                            Float.parseFloat(result.get(0).get(Topping.price_column)),
                            Integer.parseInt(result.get(0).get(Topping.calories_column))));
                    break;
            }
        }

        return ret;
    }

    /**
     * Gets a stringified version of the names of the items provided
     *
     * @param items ArrayList of items whose names you want turned into a single string
     * @return String with names of items
     */
    public static String getItemsAsString(ArrayList<Item> items) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Item i : items) {
            stringBuilder.append(i.getName()).append(" ");
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());

        return stringBuilder.toString();
    }
}