package com.ageofwar.logic;

import com.ageofwar.model.Platoon;

import java.util.*;

public class BattleSimulator {
    private final Map<String, List<String>> advantageMap = new HashMap<>();

    public BattleSimulator() {
        advantageMap.put("Militia", Arrays.asList("Spearmen", "LightCavalry"));
        advantageMap.put("Spearmen", Arrays.asList("LightCavalry", "HeavyCavalry"));
        advantageMap.put("LightCavalry", Arrays.asList("FootArcher", "CavalryArcher"));
        advantageMap.put("HeavyCavalry", Arrays.asList("Militia", "FootArcher", "LightCavalry"));
        advantageMap.put("CavalryArcher", Arrays.asList("Spearmen", "HeavyCavalry"));
        advantageMap.put("FootArcher", Arrays.asList("Militia", "CavalryArcher"));
    }

    public List<Platoon> findWinningArrangement(List<Platoon> myArmy, List<Platoon> enemyArmy) {
        List<List<Platoon>> permutations = new ArrayList<>();
        generatePermutations(myArmy, 0, permutations);

        for (List<Platoon> arrangement : permutations) {
            int winCount = 0;
            for (int i = 0; i < 5; i++) {
                Platoon mine = arrangement.get(i);
                Platoon enemy = enemyArmy.get(i);
                int myPower = mine.count;
                int enemyPower = enemy.count;

                if (hasAdvantage(mine.type, enemy.type)) {
                    myPower *= 2;
                } else if (hasAdvantage(enemy.type, mine.type)) {
                    enemyPower *= 2;
                }

                if (myPower > enemyPower) {
                    winCount++;
                }
            }
            if (winCount >= 3) {
                return arrangement;
            }
        }
        return null;
    }

    private boolean hasAdvantage(String attacker, String defender) {
        return advantageMap.containsKey(attacker) && advantageMap.get(attacker).contains(defender);
    }

    private void generatePermutations(List<Platoon> arr, int index, List<List<Platoon>> result) {
        if (index == arr.size()) {
            result.add(new ArrayList<>(arr));
            return;
        }
        for (int i = index; i < arr.size(); i++) {
            Collections.swap(arr, i, index);
            generatePermutations(arr, index + 1, result);
            Collections.swap(arr, i, index);
        }
    }
}