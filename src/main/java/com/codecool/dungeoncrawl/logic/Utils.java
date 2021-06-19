package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.items.Item;

public class Utils {

    public static StringBuilder addItemsInventoryScreen(GameMap map) {
        StringBuilder inventoryItemsRepresentation = new StringBuilder();
        for (Item item: map.getPlayer().getInventory()) {
            inventoryItemsRepresentation.append(item.getClass().getSimpleName()).append(" \n");
        }
        return inventoryItemsRepresentation;
    }
}
