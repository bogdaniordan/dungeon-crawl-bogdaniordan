package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.items.Cross;
import com.codecool.dungeoncrawl.logic.items.Crown;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.logic.popups.GameVerdictPopup;

public class ItemEffect {

    public void increasePlayerDamage(Cell cell, GameMap map) {
        if (cell.getItem() instanceof Sword) {
            map.getPlayer().increaseDamage(((Sword) cell.getItem()).getDamage());
        }
    }

    public void increasePlayerHealth(Cell cell, GameMap map) {
        if (cell.getItem() instanceof Cross) {
            map.getPlayer().increaseHealth(((Cross) cell.getItem()).getGivenHp());
        }
    }

    public void pickUpCrown(Cell cell) {
        if (cell.getItem() instanceof Crown) {
            GameVerdictPopup.display("You found the crown!", "Play again");
        }
    }

    public void openDoor(Cell cell, GameMap map) {
        if (cell.getItem() instanceof Key) {
            for (int i = 0; i < map.getCells().length; i ++) {
                for (int j = 0; j < map.getCells()[0].length; j++) {
                    if (map.getCell(i, j).getType() == CellType.CLOSED_DOOR) {
                        map.getCell(i, j).setType(CellType.OPEN_DOOR);
                    }
                }
            }
        }
    }
}
