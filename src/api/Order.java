package api;

import java.util.ArrayList;

public class Order {
    int id;
    Customer customer;
    Employee employee;
    String date;
    String time;
    String contents;
    boolean fulfilled;

    public Order(ArrayList<Item> items, Customer customer, Employee employee, boolean fulfilled) {
        String contents = makeItemListString(items);
        this.customer = customer;
        this.employee = employee;
        this.date = java.time.LocalDate.now().toString();
        this.time = java.time.LocalTime.now().toString();
        this.contents = contents;
        this.fulfilled = fulfilled;
        // Insert into database
        this.id = id;
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
