package api;

public class Topping extends Item {
    static final String tableName = "toppings";

    Topping(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }
}
