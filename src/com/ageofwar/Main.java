// File: com/ageofwar/Main.java
package com.ageofwar;

import com.ageofwar.logic.BattleSimulator;
import com.ageofwar.model.Platoon;
import com.ageofwar.util.InputParser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String myInput = "Spearmen#10;Militia#30;FootArcher#20;LightCavalry#1000;HeavyCavalry#120";
        String enemyInput = "Militia#10;Spearmen#10;FootArcher#1000;LightCavalry#120;CavalryArcher#100";

        List<Platoon> myArmy = InputParser.parseArmy(myInput);
        List<Platoon> enemyArmy = InputParser.parseArmy(enemyInput);

        BattleSimulator simulator = new BattleSimulator();
        List<Platoon> winningArrangement = simulator.findWinningArrangement(myArmy, enemyArmy);

        if (winningArrangement == null) {
            throw new IllegalStateException("There is no chance of winning");
        } else {
            System.out.println("Winning arrangement found:");
            for (Platoon p : winningArrangement) {
                System.out.print(p + ";");
            }
        }
    }
}
