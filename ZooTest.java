import Animals.*;
import Cells.BirdCell;
import Cells.LionCell;
import Cells.UngulatesCell;
import Exceptions.NoSuchAnimalException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Exceptions.NoFreePlacesException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ZooTest {
    List<Animal> animals = new ArrayList<>();
    Zoo zoo;
    BirdCell birdCell;
    LionCell lionCell;
    UngulatesCell ungulatesCell;

    @Before
    public void setUp() throws Exception {
        zoo = new Zoo();
        birdCell = new BirdCell(5, 5);
        lionCell = new LionCell(2, 2);
        ungulatesCell = new UngulatesCell(7, 7);

    }

    @Test
    public void testZoo() throws Exception {
        Eagle eagle = new Eagle("Orel", "American", 12, 200 );
        Geerafe geerafe = new Geerafe("Melman", "Brown", 23, 3, 4, "In dot");
        Zebra zebra = new Zebra("Marty", "White", 4, 4, 4, "In line");
        Lion lion = new Lion("Alex", "Zoopacian", 5, 3);

        birdCell.addAnimal(eagle);
        lionCell.addAnimal(lion);
        ungulatesCell.addAnimal(geerafe);
        ungulatesCell.addAnimal(zebra);

        zoo.addCell(birdCell);
        zoo.addCell(lionCell);
        zoo.addCell(ungulatesCell);

        Assert.assertEquals(zoo.getCountOfAnimals(), 4);

    }

    @Test(expected = NoFreePlacesException.class)
    public void testNoPLaces() throws Exception {
        lionCell.addAnimal(new Lion("George", "White", 2, 3));
        lionCell.addAnimal(new Lion("John", "White", 3, 3));
        lionCell.addAnimal(new Lion("John", "Black", 3, 4));
    }

    @Test(expected = NoSuchAnimalException.class)
    public void testNoSuchAnimal() throws Exception {
        LionCell lionCell = new LionCell(3, 3);
        lionCell.addAnimal(new Lion("George", "White", 2, 3));
        lionCell.removeAnimal(new Lion("John", "White", 3, 3));
    }





}

