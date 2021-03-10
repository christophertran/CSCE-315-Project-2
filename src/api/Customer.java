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
        HashMap<String, String> constraints = new HashMap<>();
        constraints.put(Customer.name_column, customerName.toUpperCase());
        ArrayList<HashMap<String, String>> customerResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Customer.tableName, constraints, null));

        if (customerResult.size() == 0)
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

    public static ArrayList<Item> getCustomerRecommendations(Customer customer, Integer limit) throws SQLException {
        HashMap<String, String> constraints = new HashMap<>();
        constraints.put(Order.customer_id_column, customer.getId().toString());

        ArrayList<HashMap<String, String>> queryResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Order.tableName, constraints, 1));

        Scanner sc = new Scanner(queryResult.get(0).get(Order.contents_column));
        sc.useDelimiter(" ");

        ArrayList<Item> previousOrder = new ArrayList<>();
        if (queryResult.size() > 0) {
            while(sc.hasNext() && (limit == null || (limit-- > 0))) {
                String itemName = Item.getFullItemNameFromString(sc.next());
                previousOrder.add(Item.getItemFromDatabaseByName(itemName));
            }
        }

        return previousOrder;
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
