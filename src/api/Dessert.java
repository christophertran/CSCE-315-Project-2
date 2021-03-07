package api;

public class Dessert extends Item {
    static final String tableName = "desserts";

    Dessert(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }
}
