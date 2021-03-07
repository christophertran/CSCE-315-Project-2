package api;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    static final String tableName = "orders";

    int id;
    Customer customer;
    Employee employee;
    String date;
    String time;
    String contents;
    boolean fulfilled;

    public Order(ArrayList<Item> items, Customer customer, Employee employee, boolean fulfilled) throws SQLException {
        this.customer = customer;
        this.employee = employee;
        this.date = LocalDate.now().toString();
        this.time = LocalTime.now().toString();
        this.contents = Order.makeItemListString(items);
        this.fulfilled = fulfilled;

        // Insert into database
        HashMap<String, String> values = new HashMap<>();
        if (this.customer != null) {
            values.put("customer_id", this.customer.id.toString());
        }
        if (this.employee != null) {
            values.put("employee_id", this.employee.id.toString());
        }
        values.put("date", this.date);
        values.put("time", this.time);
        values.put("contents", this.contents);
        values.put("fulfilled", this.fulfilled ? "true" : "false");

        int updateResult = QueryBuilder.executeUpdate(QueryBuilder.buildInsertionQuery(Order.tableName, values));

        ArrayList<HashMap<String, String>> orderResult = null;

        if (updateResult > 0) {
            orderResult = QueryBuilder.executeQuery(QueryBuilder.buildGetLastItemFromTableQuery(Order.tableName));
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
