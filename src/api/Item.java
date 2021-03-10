package api;

import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class Item {
    static final String id_column = "id";
    static final String name_column = "name";
    static final String price_column = "price";
    static final String calories_column = "calories";

    int id;
    String name;
    float price;
    int calories;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getCalories() {
        return calories;
    }

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

    /**
     * Returns an ArrayList of 4 items, the first two items are the trending up items and the last two items are the trending down items.
     * @return
     * @throws SQLException
     */
    static ArrayList<Item> getTrendingUpAndDownItems() throws  SQLException {
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

                ArrayList<HashMap<String, String>> result = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Item.getTableNameFromItemName(contentName), constraints));

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