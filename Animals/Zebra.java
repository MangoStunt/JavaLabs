package Animals;

public class Zebra extends Ungulates {

    public String color;

    public Zebra(String name, String classification, int age, int feedingPeriod, int fingersInHoof, String color) {
        super(name, classification, age, feedingPeriod, fingersInHoof);
        this.color = color;
    }

}
