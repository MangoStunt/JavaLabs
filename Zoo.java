import Cells.Cell;
import java.util.ArrayList;
import java.util.List;


public class Zoo {

    public List<Cell> cells = new ArrayList<>();

    public int getCountOfAnimals() {
        int amount = 0;

        for(Cell cell : cells) {
            amount += cell.getOccupiedPlaces();
        }
        return amount;
    }

    public <C extends Cell> void addCell(C cell) {
        cells.add(cell);
    }
}
