package Cells;

import Animals.Bird;

public class BirdCell<T extends Bird> extends Cell<T> {

    private int quantityOfNests;

    public BirdCell(int quantityOfPlaces, int quantityOfNests) {
        super(quantityOfPlaces);
        this.quantityOfNests = quantityOfNests;
    }

}
