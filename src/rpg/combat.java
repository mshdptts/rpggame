package rpg;

import java.util.ArrayList;

import utils.util;

public class combat {

    public static void battle(player p, enemy e) {
        System.out.println("A wild " + e.name + " appears!");

        while (p.isAlive() && e.isAlive()) {
            p.displayStats();
            e.displayStats();

            String choice = util.getline("Choose action: [1] Attack  [2] Use Item  [3] Flee: ").trim();

            switch (choice) {
                case "1":
                    p.attackTarget(e);
                    break;
                case "2":
                    useItem(p);
                    break;
                case "3":
                    if (util.chance(0.7)) {
                        System.out.println(p.name + " fled the battle!");
                        return;
                    } else {
                        System.out.println(p.name + " tried to flee but couldn't get away!");
                        e.attackTarget(p);
                        if (!p.isAlive()) {
                            System.out.println(p.name + " has been defeated in battle...");
                            return;
                        }
                    }
                    continue;
                default:
                    System.out.println("Invalid choice — you hesitate and lose your turn.");
            }

            if (!e.isAlive()) {
                System.out.println(e.name + " has been defeated!");
                p.gainExp(e.expReward);
                return;
            }

            String enemyAction = e.chooseAction();
            switch (enemyAction) {
                case "flee":
                    System.out.println(e.name + " loses its nerve and flees the battle!");
                    return;
                case "heal":
                    e.heal(25);
                    break;
                default:
                    e.attackTarget(p);
            }

            if (!p.isAlive()) {
                System.out.println(p.name + " has been defeated in battle...");
                return;
            }
        }
    }

    private static void useItem(player p) {
        if (p.inventory.isEmpty()) {
            System.out.println("Inventory is empty — no items to use.");
            return;
        }

        System.out.println("Inventory: " + p.inventory);
        String itemName = util.getline("Which item do you want to use? ").trim();

        if (!p.inventory.contains(itemName)) {
            System.out.println("You don't have that item.");
            return;
        }

        if (itemName.toLowerCase().contains("potion")) {
            p.heal(30);
        } else {
            System.out.println(p.name + " used " + itemName + ", but nothing happened.");
        }

        p.inventory.remove(itemName);
    }

    // Mini manual test — run this class directly to play a test battle
    public static void main(String[] args) {
        try {
            player hero = database.loadPlayer("data/player.json");
            ArrayList<enemy> enemyPresets = database.loadEnemyPresets("data/enemies.json");
            enemy spawnedEnemy = database.chooseRandomEnemy(enemyPresets);

            battle(hero, spawnedEnemy);
            database.savePlayer("data/player-save.json", hero);

            System.out.println("\n--- Test complete ---");
            hero.displayStats();
        } catch (Exception exception) {
            System.out.println("Could not load game data: " + exception.getMessage());
        }
    }


    
}