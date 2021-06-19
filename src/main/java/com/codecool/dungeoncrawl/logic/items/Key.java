package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Key extends Item {
    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public void applyEffect(GameMap map, Cell cell) {
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

    @Override
    public String getTileName() {
        return "key";
    }
}
