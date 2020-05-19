package Animals;

public abstract class Ungulates extends Mammal {

    private int fingersInHoof;

    public Ungulates(String name, String classification, int age, int feedingPeriod, int fingersInHoof) {
        super(name, classification, age, feedingPeriod);
        this.fingersInHoof = fingersInHoof;
    }

}
