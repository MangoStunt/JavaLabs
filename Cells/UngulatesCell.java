package Cells;

import Animals.Ungulates;

public class UngulatesCell<T extends Ungulates> extends MammalCell<T>{

    public UngulatesCell(int quantityOfPlaces, int quantityOfBowls) {
        super(quantityOfPlaces, quantityOfBowls);
    }

}
