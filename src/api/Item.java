package api;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class Item {
    static final String id_column = "id";
    static final String name_column = "name";
    static final String price_column = "price";
    static final String calories_column = "calories";

    public static final String trending_up_key = "trending up";
    public static final String trending_down_key = "trending down";

    private Integer id;
    private String name;
    private Float price;
    private Integer calories;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        String ret = "";

        switch (Item.getItemCodeFromItemName(name)) {
            case 'B':
                ret = "Beverage" + name.charAt(1);
                break;
            case 'D':
                ret = "Dessert" + name.charAt(1);
                break;
            case 'E':
                ret = "Entree" + name.charAt(1);
                break;
            case 'M':
                ret = "Meal" + name.charAt(1);
                break;
            case 'S':
                ret = "Side" + name.charAt(1);
                break;
            case 'T':
                ret = "Topping" + name.charAt(1);
                break;
        }

        return ret;
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

    /**
     * Returns all items inside a map, with their names being the key and corresponding item as the value.
     * @return HashMap of items with their names as the keys
     * @throws SQLException Throws SQLException
     */
    public static HashMap<String, Item> getAllItemsAsMap() throws SQLException {
        HashMap<String, Item> ret = new HashMap<>();

        ArrayList<Item> allItems = new ArrayList<>();
        allItems.addAll(Beverage.getAllItems());
        allItems.addAll(Dessert.getAllItems());
        allItems.addAll(Entree.getAllItems());
        allItems.addAll(Meal.getAllItems());
        allItems.addAll(Side.getAllItems());
        allItems.addAll(Topping.getAllItems());

        for (Item i : allItems) {
            ret.putIfAbsent(i.getName(), i);
        }

        return ret;
    }

    /**
     * Gets the provided amount of both trending up and trending down items. They are returned in a hashmap and the values
     * can be accessed by using the keys Item.trending_up_key and Item.trending_down_key
     * Trending up and trending down is calculated by getting the most popular and least popular items from the past two weeks
     * from the current date.
     *
     * @param limit Specify how many trending items you want returned
     * @return Return hashmap containing two arraylists of trending up and trending down items.
     * @throws SQLException Throws SQL Exception
     */
    public static HashMap<String, ArrayList<Item>> getTrendingItems(Integer limit) throws SQLException {
        HashMap<String, ArrayList<Item>> ret = new HashMap<>();
        ret.put(Item.trending_up_key, new ArrayList<>());
        ret.put(Item.trending_down_key, new ArrayList<>());

        String currentDate = LocalDate.now().toString();
        String currentDateMinus2Weeks = LocalDate.now().minusWeeks(2).toString();

        HashMap<String, Integer> itemNameFrequencies = Item.getItemFrequencies(currentDateMinus2Weeks, currentDate);

        for (int i = 0; i < limit; i++) {
            Map.Entry<String, Integer> maxEntry = null;
            Map.Entry<String, Integer> minEntry = null;

            for (Map.Entry<String, Integer> itemNameFrequencyEntry : itemNameFrequencies.entrySet()) {
                if (maxEntry == null || itemNameFrequencyEntry.getValue() > maxEntry.getValue()) {
                    maxEntry = itemNameFrequencyEntry;
                }

                if (minEntry == null || itemNameFrequencyEntry.getValue() < minEntry.getValue()) {
                    minEntry = itemNameFrequencyEntry;
                }
            }

            ret.get(Item.trending_up_key).add(Item.getItemFromDatabaseByName(maxEntry.getKey()));
            itemNameFrequencies.remove(maxEntry.getKey());
            ret.get(Item.trending_down_key).add(Item.getItemFromDatabaseByName(minEntry.getKey()));
            itemNameFrequencies.remove(minEntry.getKey());
        }

        return ret;
    }

    /**
     * Return frequencies of order content items in data range provided
     * @param startDate Beginning of date range
     * @param endDate End of date range
     * @return HashMap with item name as key and frequency count as value
     * @throws SQLException Throws SQLException
     */
    public static HashMap<String, Integer> getItemFrequencies(String startDate, String endDate) throws SQLException {
        String query = QueryBuilder.buildGetOrdersFromDateRangeQuery(startDate, endDate, null, null);
        ArrayList<HashMap<String, String>> queryResult = QueryBuilder.executeQuery(query);

        ArrayList<String> itemsNames = new ArrayList<>();
        for (HashMap<String, String> order : queryResult) {
            String orderContents = order.get(Order.contents_column);

            Scanner sc = new Scanner(orderContents);
            sc.useDelimiter(" ");

            while (sc.hasNext()) {
                itemsNames.add(Item.getFullItemNameFromString(sc.next()));
            }
        }

        HashSet<String> uniqueItemNames = new HashSet<>(itemsNames);
        HashMap<String, Integer> itemNameFrequencies = new HashMap<>();
        for (String s : uniqueItemNames) {
            itemNameFrequencies.computeIfAbsent(s, s1 -> Collections.frequency(itemsNames, s1));
        }

        return itemNameFrequencies;
    }

    /**
     * This functions will return the effect of an item's price change on a weeks revenue. Given a date, data will be pulled
     * from 1 week before and price change effect will be calculated.
     *
     * @param itemName Name of item that price will be changed for
     * @param adjustedPrice New price of item that revenue change is wanted to be calculated at
     * @param endDate Date that change wants to be calculated from
     * @return Float that is the difference between revenues
     * @throws SQLException Throws SQLException
     */
    public static Float priceChangeEffect(String itemName, Float adjustedPrice, String endDate) throws SQLException {
        String currentDateMinus1Week = LocalDate.parse(endDate).minusWeeks(1).toString();
        HashMap<String, Integer> itemNameFrequencies = Item.getItemFrequencies(currentDateMinus1Week, endDate);

        int quantityOfSelectedItem;
        if (itemNameFrequencies.get(itemName) != null) {
            quantityOfSelectedItem = itemNameFrequencies.get(itemName);
        } else {
            quantityOfSelectedItem = 0;
        }

        Item selectedItem = Item.getItemFromDatabaseByName(itemName);
        float price = selectedItem.getPrice();
        return (adjustedPrice - price) * quantityOfSelectedItem;
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
                QueryBuilder.buildSelectionQuery(Item.getTableNameFromItemName(itemName), constraints, null, null));

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
                    ret.add(new Beverage(Integer.parseInt(h.get(Beverage.id_column)),
                            h.get(Beverage.name_column),
                            Float.parseFloat(h.get(Beverage.price_column)),
                            Integer.parseInt(h.get(Beverage.calories_column))));
                    break;
                case 'D':
                    ret.add(new Dessert(Integer.parseInt(h.get(Dessert.id_column)),
                            h.get(Dessert.name_column),
                            Float.parseFloat(h.get(Dessert.price_column)),
                            Integer.parseInt(h.get(Dessert.calories_column))));
                    break;
                case 'E':
                    ret.add(new Entree(Integer.parseInt(h.get(Entree.id_column)),
                            h.get(Entree.name_column),
                            Float.parseFloat(h.get(Entree.price_column)),
                            Integer.parseInt(h.get(Entree.calories_column)),
                            h.get(Entree.toppings_column)));
                    break;
                case 'M':
                    ret.add(new Meal(Integer.parseInt(h.get(Meal.id_column)),
                            h.get(Meal.name_column),
                            Float.parseFloat(h.get(Meal.price_column)),
                            Integer.parseInt(h.get(Meal.calories_column)),
                            h.get(Meal.contents_column)));
                    break;
                case 'S':
                    ret.add(new Side(Integer.parseInt(h.get(Side.id_column)),
                            h.get(Side.name_column),
                            Float.parseFloat(h.get(Side.price_column)),
                            Integer.parseInt(h.get(Side.calories_column))));
                    break;
                case 'T':
                    ret.add(new Topping(Integer.parseInt(h.get(Topping.id_column)),
                            h.get(Topping.name_column),
                            Float.parseFloat(h.get(Topping.price_column)),
                            Integer.parseInt(h.get(Topping.calories_column))));
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

    /**
     * Returns HTML for displaying into information on the GUI
     * @param item Item that display information is to be created for
     * @return HTML code in string format of given item's information
     */
    public static String createButtonHTML(Item item) {
        return String.format("<html>\n<center>\n%s<br><br>\nPrice: $%s, Calories: %s\n</center>\n</html>\n", item.getFullName(), item.getPrice().toString(), item.getCalories().toString());
    }

    /**
     * Returns the abbreviated version of an item name given the full version
     * @param fullName Full name of an item
     * @return Abbreviated version of the full name of an item
     */
    public static String getAbbreviatedNameFromFullName(String fullName) {
        return String.valueOf(fullName.charAt(0)) + fullName.charAt(fullName.length() - 1);
    }
}