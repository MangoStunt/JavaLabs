package Cells;

import Animals.Animal;
import Exceptions.NoFreePlacesException;
import Exceptions.NoSuchAnimalException;

import java.util.HashSet;
import java.util.Set;

public abstract class Cell<T extends Animal> {
    private Set<T> list = new HashSet<>();
    private int quantityOfPlaces;

    public Cell (int quantityOfPlaces) {
        this.quantityOfPlaces = quantityOfPlaces;
    }

    public int getQuantityOfPlaces() {
        return this.quantityOfPlaces;
    }

    public int getOccupiedPlaces() {
        return this.list.size();
    }

    public void addAnimal(T animal) throws NoFreePlacesException {
        if(this.list.size() == quantityOfPlaces) throw new NoFreePlacesException("No free places in this cell");
        else list.add(animal);
    }

    public boolean removeAnimal(T animal) throws NoSuchAnimalException {
        if(!(list.contains(animal))) throw new NoSuchAnimalException("No such animal in this cell");
        else return true;
    }

}
