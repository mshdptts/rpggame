package rpg;

import java.util.ArrayList;
import java.util.Random;

public class enemy extends player {
    String race;
    int expReward;
    double fleeChance;   // 0.0–1.0, chance to flee when badly hurt
    boolean canHeal;
    int spawnvalue;

    private static final Random rand = new Random();

    public enemy(String name, int health, int attack, ArrayList<String> inventory,
                 String race, int expReward, double fleeChance, boolean canHeal, int spawnValue) {
        super(name, health, 1, 0, attack, inventory);
        this.race = race;
        this.expReward = expReward;
        this.fleeChance = fleeChance;
        this.canHeal = canHeal;
        this.spawnvalue = spawnValue;
    }

    public String chooseAction() {
        double healthPercent = (double) health / maxhealth;

        if (canHeal && healthPercent < 0.4 && rand.nextDouble() < 0.5) {
            return "heal";
        }
        if (healthPercent < 0.25 && rand.nextDouble() < fleeChance) {
            return "flee";
        }
        return "attack";
    }

    // Preset enemy factory methods (unused, enemy preset already exist in enemies.json)
/* 
    public static enemy createGoblin() {
        return new enemy("Goblin", 150, 20, new ArrayList<>(), "Goblin", 30, 0.3, false, 1);
    }

    public static enemy createOgre() {
        return new enemy("Ogre", 400, 45, new ArrayList<>(), "Ogre", 100, 0.0, false, 2);
    }

    public static enemy createImp() {
        return new enemy("Imp", 100, 15, new ArrayList<>(), "Imp", 20, 0.6, false, 3);
    }

    public static enemy createMage() {
        return new enemy("Mage", 100, 50, new ArrayList<>(), "human", 75, 0.2, true, 4);
    }

    public static enemy createCursedCorpse() {
        return new enemy("Cursed Corpse", 75, 95, new ArrayList<>(), "Cursed Corpse", 50, 0.0, false, 5);
    }
*/
}
