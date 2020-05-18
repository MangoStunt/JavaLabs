package Animals;

public abstract class Bird extends Animal {

    public int wingspan;

    public Bird(String name, String classification, int age, int wingspan ) {
        super(name, classification, age);
        this.wingspan = wingspan;
    }

}
