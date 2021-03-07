package api;

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
        ArrayList<HashMap<String, String>> result = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery("customer", constraints));

        if (result.size() == 0)
        {
            // The customer does NOT exist in the database yet, we need to add them.

        }
        else
        {
            // The customer DOES exist in the database, so return an object with their information.
            ret = new Customer (Integer.parseInt(result.get(0).get("id")), result.get(0).get("name"), result.get(0).get("address"), result.get(0).get("email"));
        }

        return ret;
    }
}
