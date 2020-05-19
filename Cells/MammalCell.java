package Cells;

import Animals.Animal;
import Animals.Mammal;

public abstract class MammalCell<T extends Mammal> extends Cell<T> {

    private int quantityOfBowls;

    public MammalCell(int quantityOfPlaces, int quantityOfBowls) {
        super(quantityOfPlaces);
        this.quantityOfBowls = quantityOfBowls;
    }
}
