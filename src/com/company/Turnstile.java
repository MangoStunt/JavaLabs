package com.company;

import java.io.IOException;
import java.util.HashMap;

public class Turnstile {
    private static int idCounter;
    private static HashMap<Integer, Turnstile> Items;

    static {
        idCounter = 0;
        Items = new HashMap<>(10);
    }

    private int id;
    private PassSystem system;

    public int getID() {
        return (id);
    }

    public Turnstile(PassSystem system) {
        id = idCounter++;
        this.system = system;
        Items.put(id, this);
    }

    public void pass(int passID)
            throws IOException {
        system.pass(passID, this.getID());
    }
}

