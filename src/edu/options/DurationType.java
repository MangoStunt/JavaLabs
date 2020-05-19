package edu.options;

public enum DurationType {
    DAY(1),
    WEEK(7),
    MONTH(30);
    private int daysLeft;

    DurationType(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public int getDaysLeft() {
        return daysLeft;
    }
}
