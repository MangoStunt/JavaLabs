package com.company;

import java.io.IOException;
import java.util.Date;

public class Main {

    public static void main(String[] args)
			throws IOException {

            Date now = new Date();
            Date seasonEnd = (Date)now.clone();
            seasonEnd.setTime((long)(seasonEnd.getTime() + 30.0 * 24.0 * 60.0 * 60.0 * 1000.0));
            PassSystem system = PassSystem.newInstance(now, seasonEnd);
            Turnstile turn = new Turnstile(system);

            system.createWeekdayPass(5);
            system.createWeekdayPass(new Date(), 10);
            system.createWeekendPass(10);
            system.createWeekendPass(new Date(), 3);
            system.createSeasonPass();

            turn.pass(0);
            turn.pass(1);
            turn.pass(2);
            turn.pass(3);
            turn.pass(4);
        }
    }
