package rpg;

import utils.util;
import java.util.ArrayList;
public class player {
    String name;
    static int maxhealth = 1000;
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

    public void displayStats() {
        System.out.println("Name: " + name);
        System.out.println("Health: " + health + "/" + maxhealth);
        System.out.println("Level: " + level);
        System.out.println("Experience: " + exp);
        System.out.println("Attack: " + attack);
        System.out.println("Inventory: " + inventory);
    }
    public String getname(player p, String prompt) {
        String name = util.getline(prompt);
        p.name = name;
        return name;

    }

    public void takeDamage(int damage, player p) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        System.out.println( p.name + " took " + damage + " damage.");
        System.out.println("" + p.name + "'s health is now " + health + "/" + maxhealth);

        if(health <= 0) {
            System.out.println(p.name + " has been defeated!");
        }
    }

    public Boolean isAlive(player p) {
        return health > 0;
    }

    




    
}
