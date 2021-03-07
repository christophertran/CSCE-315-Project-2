package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    int id;
    Customer customer;
    Employee employee;
    String date;
    String time;
    String contents;
    boolean fulfilled;

    public Order(ArrayList<Item> items, Customer customer, Employee employee, boolean fulfilled) throws SQLException {
        String contents = makeItemListString(items);
        this.customer = customer;
        this.employee = employee;
        this.date = java.time.LocalDate.now().toString();
        this.time = java.time.LocalTime.now().toString();
        this.contents = contents;
        this.fulfilled = fulfilled;
        // Insert into database
        HashMap<String, String> values = new HashMap<>();
        values.put("customer_id", this.customer.id.toString());
        //values.put("employee_id", this.employee.id.toString());
        values.put("date", this.date);
        values.put("time", this.time);
        values.put("contents", this.contents);
        values.put("fulfilled", this.fulfilled ? "true" : "false");
        String query = QueryBuilder.buildInsertionQuery("orders", values);
        int updateResult = QueryBuilder.executeUpdate(query);
        ArrayList<HashMap<String, String>> orderResult = null;
        if (updateResult > 0) {
            orderResult = QueryBuilder.executeQuery("SELECT * FROM orders ORDER BY \"id\" DESC LIMIT 1");
        }
        if (orderResult != null) {
            this.id = Integer.parseInt(orderResult.get(0).get("id"));
        }

    }

    static String makeItemListString(ArrayList<Item> items) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Item item : items) {
            String typeCode = item.getClass().getName().substring(0, 1);
            stringBuilder.append(typeCode).append(item.id).append(" ");
        }
        return stringBuilder.toString();
    }
}
