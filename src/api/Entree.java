package api;

import java.util.ArrayList;

public class Entree extends Item {
    ArrayList<Topping> toppings;


    Entree(int id, String name, float price, int calories) {
        super(id, name, price, calories);
    }
}
