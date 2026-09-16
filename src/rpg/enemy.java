package rpg;

import java.util.ArrayList;

public class enemy extends player {
    String race;
    int expReward;

    public enemy(String name, int health, int attack,
                 ArrayList<String> inventory, String race, int expReward) {
        super(name, health, 1, 0, attack, inventory);
        this.race = race;
        this.expReward = expReward;
    }

    // Preset enemy factory methods

    public static enemy createGoblin() {
        return new enemy("Goblin", 150, 20, new ArrayList<>(), "Goblin", 30);
    }

    public static enemy createOgre() {
        return new enemy("Ogre", 400, 45, new ArrayList<>(), "Ogre", 100);
    }

    public static enemy createImp() {
        return new enemy("Imp", 100, 15, new ArrayList<>(), "Imp", 20);
    }

    public static enemy createMage() {
        return new enemy("Mage", 100, 50, new ArrayList<>(), "human", 75);
    }

    public static enemy createCursedCorpse() {
        return new enemy("Cursed Corpse", 75, 95, new ArrayList<>(), "Cursed Corpse", 50);
    }
}

    