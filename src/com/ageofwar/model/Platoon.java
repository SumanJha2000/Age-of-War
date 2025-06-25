package com.ageofwar.model;

public class Platoon {
    public String type;
    public int count;

    public Platoon(String type, int count) {
        this.type = type;
        this.count = count;
    }

    public String toString() {
        return type + "#" + count;
    }
}