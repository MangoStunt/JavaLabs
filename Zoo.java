import Animals.Animal;
import Cells.Cell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Zoo {

    public Set<Cell> cells = new HashSet<>();

    public int getCountOfAnimals() {
        int amount = 0;

        for(Cell cell : cells) {
            amount += cell.getOccupiedPlaces();
        }
        return amount;
    }

    public <T extends Cell> void addCell(T cell) {
        cells.add(cell);
    }
}
