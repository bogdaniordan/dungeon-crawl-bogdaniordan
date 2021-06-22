package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.items.Cross;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static StringBuilder addItemsInventoryScreen(GameMap map) {
        StringBuilder inventoryItemsRepresentation = new StringBuilder();
        for (Item item: map.getPlayer().getInventory()) {
            inventoryItemsRepresentation.append(item.getClass().getSimpleName()).append(" \n");
        }
        return inventoryItemsRepresentation;
    }

    public static List<Item> loadItemsFromDB(int crosses, int keys, int swords) {
        List<Item> newInventory = new ArrayList<>();
        for (int i = 0; i < crosses; i++) {
            newInventory.add(new Cross());
        }
        for(int j = 0; j < keys; j++) {
            newInventory.add(new Key());
        }
        for(int x = 0; x < swords; x++) {
            newInventory.add(new Sword());
        }
        return newInventory;
    }

}
