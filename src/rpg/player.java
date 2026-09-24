package rpg;

import utils.util;
import java.util.ArrayList;

public class player {
    String name;
    int maxhealth;
    int health;
    int level = 1;
    int exp;
    int attack;
    ArrayList<String> inventory = new ArrayList<>();

    private static final int HEALTH_PER_LEVEL = 20;
    private static final int ATTACK_PER_LEVEL = 5;

    public player(String name, int health, int level, int exp, int attack, ArrayList<String> inventory) {
        this.name = name;
        this.maxhealth = health;
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

    public void heal(int amount) {
        health = Math.min(health + amount, maxhealth);
        System.out.println(name + " healed " + amount + " HP! Health is now " + health + "/" + maxhealth);
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
            this.level++;
            this.exp -= 100;
            this.maxhealth += HEALTH_PER_LEVEL;
            this.health += HEALTH_PER_LEVEL;
            this.attack += ATTACK_PER_LEVEL;
            System.out.println(name + " gained a level!");
            System.out.println("Stats increased: +" + HEALTH_PER_LEVEL + " max health, +"
                    + ATTACK_PER_LEVEL + " attack.");
        }

        return this.exp;
    }

    public int plus1000dmg() {
        return this.attack + 100;
    }
}