package api;

import java.sql.SQLException;
import java.util.*;

public class Customer {
    static final String tableName = "customers";
    static final String id_column = "id";
    static final String name_column = "name";
    static final String address_column = "address";
    static final String email_column = "email";

    Integer id;
    String name;
    String address;
    String email;

    public Customer(Integer id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public static Customer getCustomerByName(String customerName) throws SQLException {
        Customer ret = null;

        HashMap<String, String> constraints = new HashMap<>();
        constraints.put(Customer.name_column, customerName.toUpperCase());
        ArrayList<HashMap<String, String>> customerResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Customer.tableName, constraints));

        if (customerResult == null || customerResult.size() == 0)
        {
            // The customer does NOT exist in the database yet, we need to add them.
            HashMap<String, String> values = new HashMap<>();

            values.put(Customer.name_column, customerName.toUpperCase());
            String query = QueryBuilder.buildInsertionQuery(Customer.tableName, values);
            Integer updateResult = QueryBuilder.executeUpdate(query);

            if (updateResult > 0) {
                customerResult = QueryBuilder.executeQuery(QueryBuilder.buildGetLastItemFromTableQuery(Customer.tableName));
            }
        }

        return new Customer (Integer.parseInt(customerResult.get(0).get(Customer.id_column)), customerResult.get(0).get(Customer.name_column), customerResult.get(0).get(Customer.address_column), customerResult.get(0).get(Customer.email_column));
    }

    /**
     * This functions take a customer object and returns the last 3 items from their previous orders as recommendations
     * @param customer
     * @return
     * @throws SQLException
     */
    public static ArrayList<Item> getCustomerRecommendations(Customer customer) throws SQLException {
        HashMap<String, String> constraints = new HashMap<>();
        constraints.put(Order.customer_id_column, customer.getId().toString());

        ArrayList<HashMap<String, String>> queryResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Order.tableName, constraints));

        ArrayList<Item> previousOrderContents = new ArrayList<>();
        for (HashMap<String, String> order : queryResult) {
            Scanner sc = new Scanner(order.get(Order.contents_column));
            sc.useDelimiter(" ");

            while (sc.hasNext() && previousOrderContents.size() < 3) {
                String contentName = sc.next();

                constraints.clear();

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
                }
            }
        }

        return previousOrderContents;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
