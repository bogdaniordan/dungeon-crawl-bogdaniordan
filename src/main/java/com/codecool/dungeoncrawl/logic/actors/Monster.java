package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Monster extends Actor{
    public Monster(Cell cell) {
        super(cell);
    }

    public void attackPlayer(GameMap map) {
        if (map.getPlayer().getX() < getX() && map.getCell(getX() - 1, getY()).getType() != CellType.WALL && map.getCell(getX() - 1, getY()).getType() != CellType.TREE) {
           move(- 1, 0);
           System.out.println("MOVE");
        } else if (map.getPlayer().getX() > getX() && map.getCell(getX() +1, getY()).getType() != CellType.WALL && map.getCell(getX() + 1, getY()).getType() != CellType.TREE) {
            move(1, 0);
        } else if (map.getPlayer().getY() < getY() && map.getCell(getX(), getY() - 1).getType() != CellType.WALL && map.getCell(getX() - 1, getY() - 1).getType() != CellType.TREE) {
            move(0, - 1);
        } else if (map.getPlayer().getY() > getY()&& map.getCell(getX(), getY() + 1).getType() != CellType.WALL && map.getCell(getX() -1, getY() + 1).getType() != CellType.TREE ) {
            move(0, 1);
        }
    }

    @Override
    public String getTileName() {
        return "monster";
    }
}
