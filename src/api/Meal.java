package api;

public class Meal extends Item{
    static final String tableName = "meals";

    Meal(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }
}
