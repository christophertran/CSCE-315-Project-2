package api;

import java.util.ArrayList;

public class Entree extends Item {
    ArrayList<Topping> toppings;

    Entree(int id) {
        super(id);

    }

    Entree(String name, float price, int calories) {
        super(name, price, calories);
    }
}
