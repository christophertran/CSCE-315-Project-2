package api;

public class Beverage extends Item{
    static final String tableName = "beverages";

    Beverage(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }
}
