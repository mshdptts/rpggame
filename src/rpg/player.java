package rpg;

import utils.util;
import java.util.ArrayList;
public class player {
    String name;
    static int maxhealth;
    int health = maxhealth;
    int level;
    int exp;
    int attack;
    ArrayList<String> inventory = new ArrayList<>();






    public player(String name, int health, int level, int exp, int attack, ArrayList<String> inventory) {
        this.name = name;
        this.health = health;
        this.level = level;
        this.exp = exp;
        this.attack = attack;
        this.inventory = new ArrayList<>();
    }


}
