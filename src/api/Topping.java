package api;

public class Topping extends Item {
    Topping(int id) {
        super(id);
    }

    Topping(String name, float price, int calories) {
        super(name, price, calories);
    }
}
