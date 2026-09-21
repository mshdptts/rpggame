package rpg;

import java.io.IOException;
import java.util.ArrayList;

import utils.util;

public class game {
    private final player player;
    private final ArrayList<enemy> enemyPresets;

    public game(player player, ArrayList<enemy> enemyPresets) {
        this.player = player;
        this.enemyPresets = enemyPresets;
    }

    public void start() {
        boolean playing = true;
        util.slowPrint("Welcome, " + player.name + ". Your adventure begins...", 12);

        while (playing && player.isAlive()) {
            util.banner("ADVENTURE");
            util.menuOption("1", "Story");
            util.menuOption("2", "Explore");
            util.menuOption("3", "View stats");
            util.menuOption("4", "Quit");

            switch (util.getline("Choose an option: ").trim()) {
                case "1":
                    playStory();
                    break;
                case "2":
                    explore();
                    break;
                case "3":
                    player.displayStats();
                    break;
                case "4":
                    playing = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        if (!player.isAlive()) {
            System.out.println("Your adventure has ended.");
        }
    }

    private void playStory() {
        util.banner("STORY: THE FORKED ROAD");
        util.slowPrint("You arrive at a fork in the road. Smoke rises beyond the hills.", 8);
        String firstChoice = util.getline("  [1] Follow the smoke  [2] Take the forest path: ").trim();

        if ("1".equals(firstChoice)) {
            util.slowPrint("You find a wounded scout guarding a ruined watchtower.", 8);
            String secondChoice = util.getline("  [1] Help the scout  [2] Search the tower: ").trim();
            if ("1".equals(secondChoice)) {
                storyBattle("The scout points toward a lurking threat.", 0);
                rewardItem("Scout's Badge");
            } else if ("2".equals(secondChoice)) {
                rewardItem("Rusty Key");
                storyBattle("Your search wakes the creature beneath the tower.", 1);
            } else {
                System.out.println("You hesitate, and the chance is lost.");
            }
            return;
        }

        if ("2".equals(firstChoice)) {
            util.slowPrint("The forest path leads to a locked stone gate.", 8);
            String secondChoice = util.getline("  [1] Force the gate  [2] Look for another way: ").trim();
            if ("1".equals(secondChoice)) {
                storyBattle("The noise draws a forest guardian.", 1);
            } else if ("2".equals(secondChoice)) {
                rewardItem("Hidden Map");
                System.out.println("You find a safe route around the gate.");
            }else if("112211".equals(secondChoice)){
                player.attack = player.plus1000dmg();
                System.out.println("You have unlocked a secret cheat code! Your attack is now " + player.attack + ".");

            }
             else {
                System.out.println("You lose the trail and return to the fork.");
            }
            return;
        }

        System.out.println("You stay at the fork until nightfall.");
    }

    private void explore() {
        int difficulty = util.randomInt(1, 11);
        int encounterCount = 1 + (difficulty - 1) / 3;
        if (difficulty >= 9) {
            encounterCount++;
        }

        util.banner("EXPLORATION");
        util.slowPrint("You explore the area...", 8);
        System.out.println("Difficulty roll: " + difficulty + "/10");
        System.out.println("You may encounter " + encounterCount + " enemy "
                + (encounterCount == 1 ? "or" : "s") + " and find useful items.");

        for (int encounter = 0; encounter < encounterCount && player.isAlive(); encounter++) {
            enemy randomEnemy = createExplorationEnemy(difficulty);
            combat.battle(player, randomEnemy);
        }

        if (player.isAlive() && util.chance(0.65)) {
            rewardItem(randomExplorationItem());
        } else if (player.isAlive()) {
            System.out.println("You find nothing useful this time.");
        }

        
    }

    private void storyBattle(String message, int minimumEnemyIndex) {
        util.section("Encounter");
        util.slowPrint(message, 8);
        combat.battle(player, copyEnemy(enemyPresets.get(minimumEnemyIndex)));
    }

    private enemy createExplorationEnemy(int difficulty) {
        if (difficulty >= 9) {
            return copyEnemy(database.chooseRandomEnemy(enemyPresets));
        }

        int maximumIndex = Math.min(enemyPresets.size() - 1,
                difficulty <= 3 ? 1 : difficulty <= 6 ? 2 : difficulty <= 8 ? 3 : enemyPresets.size() - 1);
        return copyEnemy(enemyPresets.get(util.randomInt(0, maximumIndex + 1)));
    }

    private enemy copyEnemy(enemy source) {
        return new enemy(source.name, source.maxhealth, source.attack, new ArrayList<>(),
                source.race, source.expReward, source.fleeChance, source.canHeal);
    }

    private void rewardItem(String itemName) {
        player.inventory.add(itemName);
        util.section("Item Found");
        util.slowPrint("+ " + itemName + " added to your inventory. It has no effect yet.", 8);
    }

    private String randomExplorationItem() {
        String[] items = {"Old Coin", "Herbal Charm", "Traveler's Journal", "Strange Gem"};
        return items[util.randomInt(0, items.length)];
    }

    public static game load(String playerFile, String enemyFile) throws IOException {
        return new game(database.loadPlayer(playerFile), database.loadEnemyPresets(enemyFile));
    }

    public player getPlayer() {
        return player;
    }

}
