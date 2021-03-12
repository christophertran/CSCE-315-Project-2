package api;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    static final String tableName = "orders";
    static final String id_column = "id";
    static final String customer_id_column = "customer_id";
    static final String employee_id_column = "employee_id";
    static final String date_column = "date";
    static final String time_column = "time";
    static final String contents_column = "contents";
    static final String fulfilled_column = "fulfilled";

    private int id;
    private Customer customer;
    private Employee employee;
    private String date;
    private String time;
    private String contents;
    private boolean fulfilled;

    public Order(String contents, Customer customer, Employee employee, boolean fulfilled) throws SQLException {
        this.customer = customer;
        this.employee = employee;
        this.date = LocalDate.now().toString();
        this.time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.contents = contents;
        this.fulfilled = fulfilled;

        // Insert into database
        HashMap<String, String> values = new HashMap<>();
        if (this.customer != null) {
            values.put(Order.customer_id_column, this.customer.getId().toString());
        }

        if (this.employee != null) {
            values.put(Order.employee_id_column, this.employee.getId().toString());
        }
        values.put(Order.date_column, this.date);
        values.put(Order.time_column, this.time);
        values.put(Order.contents_column, this.contents);
        values.put(Order.fulfilled_column, this.fulfilled ? "true" : "false");

        int updateResult = QueryBuilder.executeUpdate(QueryBuilder.buildInsertionQuery(Order.tableName, values));

        ArrayList<HashMap<String, String>> orderResult = null;

        if (updateResult > 0) {
            orderResult = QueryBuilder.executeQuery(QueryBuilder.buildGetLastItemFromTableQuery(Order.tableName));
        }

        if (orderResult != null) {
            this.id = Integer.parseInt(orderResult.get(0).get(Order.id_column));
        }
    }
}
