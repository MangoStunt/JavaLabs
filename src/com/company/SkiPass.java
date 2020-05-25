package com.company;

import java.util.*;

public class SkiPass {
    public enum Type { Weekday, Weekend, Season }

    private int id;
    private Type type;
    private Date activationDate;
    private Date expirationDate;
    private int rides;
    private boolean isBlocked;

    public int getID() {
        return id;
    }
    public Type getType() {
        return (type);
    }
    public Date getActivationDate() {
        return activationDate;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public int getRides() {
        return rides;
    }
    public boolean isBlocked() {
        return isBlocked;
    }

    public void setID(PassSystem system) {
        id = system.getPassCounter();
    }
    private void setExpirationDate(double days) {
        expirationDate = (Date)activationDate.clone();
        double i = -1;

        days = (days < 0) ? 0 : days;
        while (++i < days) {
            while (!isRightDay(this.type, expirationDate))
                expirationDate.setTime(expirationDate.getTime() + 24 * 60 * 60 * 1000);
            expirationDate.setTime(expirationDate.getTime() + 24 * 60 * 60 * 1000);
        }
        expirationDate.setTime(
                (long)(expirationDate.getTime() + (days - i) * 24 * 60 * 60 * 1000));
    }
    public void block() {
        isBlocked = true;
    }
    public void unblock() {
        isBlocked = false;
    }

    public static SkiPass newWeekdayPass(Date activationDate, double days) {
        SkiPass pass = new SkiPass();

        pass.type = Type.Weekday;
        pass.activationDate = activationDate;
        pass.setExpirationDate(days);
        return (pass);
    }
    public static SkiPass newWeekdayPass(int rides) {
        SkiPass pass = new SkiPass();

        pass.type = Type.Weekday;
        pass.rides = (rides < 0) ? 0 : rides;
        return (pass);
    }
    public static SkiPass newWeekendPass(Date activationDate, double days) {
        SkiPass pass = new SkiPass();

        pass.type = Type.Weekend;
        pass.activationDate = activationDate;
        pass.setExpirationDate(days);
        return (pass);
    }
    public static SkiPass newWeekendPass(int rides) {
        SkiPass pass = new SkiPass();

        pass.type = Type.Weekend;
        pass.rides = (rides < 0) ? 0 : rides;
        return (pass);
    }
    public static SkiPass newSeasonPass() {
        SkiPass pass = new SkiPass();

        pass.type = Type.Season;
        return (pass);
    }

    public void decRides() {
        --rides;
    }

    public static boolean isRightDay(Type type, Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        return ((type == Type.Weekday) == (calendar.get(Calendar.DAY_OF_WEEK) > 1 &&
                calendar.get(Calendar.DAY_OF_WEEK) < 7));
    }
}
