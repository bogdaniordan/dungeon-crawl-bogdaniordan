package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Cross;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory;
    private String name;

    public Player(Cell cell) {
        super(cell);
        inventory = new ArrayList<>();
        setDamage(5);
        setHealth(20);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public int getCrossesNumber() {
        int count = 0;
        for (Item item: inventory) {
            if (item instanceof Cross) {
                count++;
            }
        }
        return count;
    }

    public int getSwordsNumber() {
        int count = 0;
        for (Item item: inventory) {
            if (item instanceof Sword) {
                count++;
            }
        }
        return count;
    }

    public int getKeysNumber() {
        int count = 0;
        for (Item item: inventory) {
            if (item instanceof Key) {
                count++;
            }
        }
        return count;
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public String getTileName() {
        return "player";
    }
}
