package api;

import java.util.ArrayList;

public class Entree extends Item {
    static final String tableName = "entrees";

    ArrayList<Topping> toppings;

    Entree(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }
}
