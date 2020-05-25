package com.company;

import java.io.*;
import java.util.Date;
import java.util.HashMap;

public class PassSystem {
    private HashMap<SkiPass.Type, HashMap<Integer, SkiPass>> Passes;
    private Date seasonStart;
    private Date seasonEnd;
    private int passCounter;

    public SkiPass getPass(int id) {
        if (Passes.get(SkiPass.Type.Weekday).containsKey(id))
            return (Passes.get(SkiPass.Type.Weekday).get(id));
        else if (Passes.get(SkiPass.Type.Weekend).containsKey(id))
            return (Passes.get(SkiPass.Type.Weekend).get(id));
        else if (Passes.get(SkiPass.Type.Season).containsKey(id))
            return (Passes.get(SkiPass.Type.Season).get(id));
        return (null);
    }
    public Date getSeasonStart() {
        return seasonStart;
    }
    public Date getSeasonEnd() {
        return seasonEnd;
    }
    public int getPassCounter() {
        return (passCounter++);
    }

    {
        passCounter = 0;
        Passes = new HashMap<>(3);
        Passes.put(SkiPass.Type.Weekday, new HashMap<>(5000));
        Passes.put(SkiPass.Type.Weekend, new HashMap<>(5000));
        Passes.put(SkiPass.Type.Season, new HashMap<>(5000));
    }
    public static PassSystem newInstance(Date seasonStart, Date seasonEnd) {
        PassSystem system;

        if (seasonStart.after(seasonEnd))
            return (null);
        system = new PassSystem();
        system.seasonStart = seasonStart;
        system.seasonEnd = seasonEnd;
        return (system);
    }

    public int createWeekdayPass(Date activationDate, double days) {
        SkiPass pass = SkiPass.newWeekdayPass(activationDate, days);

        pass.setID(this);
        Passes.get(pass.getType()).put(pass.getID(), pass);
        return (pass.getID());
    }
    public int createWeekdayPass(int rides) {
        SkiPass pass = SkiPass.newWeekdayPass(rides);

        pass.setID(this);
        Passes.get(pass.getType()).put(pass.getID(), pass);
        return (pass.getID());
    }
    public int createWeekendPass(Date activationDate, double days) {
        SkiPass pass = SkiPass.newWeekendPass(activationDate, days);

        pass.setID(this);
        Passes.get(pass.getType()).put(pass.getID(), pass);
        return (pass.getID());
    }
    public int createWeekendPass(int rides) {
        SkiPass pass = SkiPass.newWeekendPass(rides);

        pass.setID(this);
        Passes.get(pass.getType()).put(pass.getID(), pass);
        return (pass.getID());
    }
    public int createSeasonPass() {
        SkiPass pass = SkiPass.newSeasonPass();

        pass.setID(this);
        Passes.get(pass.getType()).put(pass.getID(), pass);
        return (pass.getID());
    }

    private boolean notePass(SkiPass pass, int turnID, boolean res)
            throws IOException {

        FileWriter fw = new FileWriter("system.log", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        Date now = new Date();
        pw.printf("%s AT %010d - PASS ID: %010d - DATE: %s\r\n",
                ((res) ? "PASSED " : "REFUSED"), turnID, pass.getID(), now.toString());
        pw.close();
        bw.close();
        fw.close();
        return (res);
    }

    public boolean pass(int id, int turnID)
            throws IOException{
        SkiPass pass = getPass(id);
        Date now = new Date();

        if (pass.isBlocked())
            return (notePass(pass, turnID, false));
        if (pass.getActivationDate() == null && pass.getType() != SkiPass.Type.Season) {
            if (SkiPass.isRightDay(pass.getType(), now) && pass.getRides() > 0) {
                pass.decRides();
                return (notePass(pass, turnID, true));
            }
            else
                return (notePass(pass, turnID, false));
        }
        else {
            if (now.after(seasonEnd))
                return (notePass(pass, turnID, false));
            else {
                if (pass.getType() == SkiPass.Type.Season)
                    return (notePass(pass, turnID, true));
                else {
                    if (now.before(pass.getExpirationDate()) &&
                            SkiPass.isRightDay(pass.getType(), now))
                        return (notePass(pass, turnID, true));
                    else
                        return (notePass(pass, turnID, false));
                }
            }
        }
    }
}
