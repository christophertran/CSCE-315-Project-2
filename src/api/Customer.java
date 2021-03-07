package api;

import javax.management.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
        constraints.put(Customer.name_column, customerName);
        ArrayList<HashMap<String, String>> customerResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Customer.tableName, constraints));

        if (customerResult == null || customerResult.size() == 0)
        {
            // The customer does NOT exist in the database yet, we need to add them.
            HashMap<String, String> values = new HashMap<>();

            values.put(Customer.name_column, customerName);
            String query = QueryBuilder.buildInsertionQuery(Customer.tableName, values);
            Integer updateResult = QueryBuilder.executeUpdate(query);

            if (updateResult > 0) {
                customerResult = QueryBuilder.executeQuery(QueryBuilder.buildGetLastItemFromTableQuery(Customer.tableName));
            }
        }

        return new Customer (Integer.parseInt(customerResult.get(0).get(Customer.id_column)), customerResult.get(0).get(Customer.name_column), customerResult.get(0).get(Customer.address_column), customerResult.get(0).get(Customer.email_column));
    }
}
