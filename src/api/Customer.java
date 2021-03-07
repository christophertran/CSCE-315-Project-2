package api;

import javax.management.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Customer {
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
        constraints.put("name", customerName);
        ArrayList<HashMap<String, String>> customerResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery("customers", constraints));

        if (customerResult == null || customerResult.size() == 0)
        {
            // The customer does NOT exist in the database yet, we need to add them.
            HashMap<String, String> values = new HashMap<>();

            values.put("name", customerName);
            String query = QueryBuilder.buildInsertionQuery("customers", values);
            Integer updateResult = QueryBuilder.executeUpdate(query);

            if (updateResult > 0) {
                customerResult = QueryBuilder.executeQuery("SELECT * FROM customers ORDER BY \"id\" DESC LIMIT 1");
            }

            System.out.println();
        }

        return new Customer (Integer.parseInt(customerResult.get(0).get("id")), customerResult.get(0).get("name"), customerResult.get(0).get("address"), customerResult.get(0).get("email"));
    }
}
