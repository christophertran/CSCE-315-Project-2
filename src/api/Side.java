package api;

public class Side extends Item{
    static final String tableName = "sides";

    Side(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }
}
