package edu.options;

public enum  TravelsCountType {
    FOUR(4),
    TEN(10),
    TWENTY(20);
    private int travelsLeft;

    TravelsCountType(int passesLeft) {
        this.travelsLeft = passesLeft;
    }

    public int getTravelsLeft() {
        return travelsLeft;
    }
}
