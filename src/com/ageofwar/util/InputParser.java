package com.ageofwar.util;

import com.ageofwar.model.Platoon;
import java.util.*;

public class InputParser {
    public static List<Platoon> parseArmy(String input) {
        String[] parts = input.split(";");
        List<Platoon> army = new ArrayList<>();
        for (String part : parts) {
            String[] tokens = part.split("#");
            army.add(new Platoon(tokens[0], Integer.parseInt(tokens[1])));
        }
        return army;
    }
}