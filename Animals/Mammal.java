package Animals;

public abstract class Mammal extends Animal {

    private int feedingPeriod;

    public Mammal(String name, String classification, int age, int feedingPeriod) {
        super(name, classification, age);
        this.feedingPeriod = feedingPeriod;
    }

}
