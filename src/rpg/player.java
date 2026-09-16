package rpg;

import utils.util;
import java.util.ArrayList;

public class player {
    String name;
    static int maxhealth = 1000;
    int health = maxhealth;
    int level = 1;
    int exp;
    int attack;
    ArrayList<String> inventory = new ArrayList<>();

    public player(String name, int health, int level, int exp, int attack, ArrayList<String> inventory) {
        this.name = name;
        this.health = health;
        this.level = level;
        this.exp = exp;
        this.attack = attack;
        this.inventory = inventory;
    }

    public void displayStats() {
        System.out.println("Name: " + name);
        System.out.println("Health: " + health + "/" + maxhealth);
        System.out.println("Level: " + level);
        System.out.println("Experience: " + exp);
        System.out.println("Attack: " + attack);
        System.out.println("Inventory: " + inventory);
    }

    public String getName(String prompt) {
        name = util.getline(prompt);
        return name;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        System.out.println(name + " took " + damage + " damage.");
        System.out.println(name + "'s health is now " + health + "/" + maxhealth);

        if (health <= 0) {
            System.out.println(name + " has been defeated!");
        }
    }

    public void attackTarget(player target) {
        int damage = this.attack;
        target.takeDamage(damage);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int gainExp(int exp) {
        this.exp += exp;
        System.out.println(name + " gained " + exp + " experience points.");

        while (this.exp >= 100) {
            level++;
            this.exp -= 100;
            System.out.println(name + " gained a level!");
        }

        return this.exp;
    }
}