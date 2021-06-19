package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Monster extends Actor{
    public Monster(Cell cell) {
        super(cell);
        setDamage(2);
        setHealth(13);
    }

    public void attackPlayer(GameMap map) {
        if (map.getPlayer().getX() < getX() && map.getCell(getX() - 1, getY()).getType() != CellType.WALL && map.getCell(getX() - 1, getY()).getType() != CellType.TREE && map.getCell(getX() - 1, getY()).getActor() == null) {
            if (getX() - 1 == map.getPlayer().getX() && getY() == map.getPlayer().getY()) {
                return;
            }
           move(- 1, 0);
        } else if (map.getPlayer().getX() > getX() && map.getCell(getX() + 1, getY()).getType() != CellType.WALL && map.getCell(getX() + 1, getY()).getType() != CellType.TREE && map.getCell(getX() + 1, getY()).getActor() == null) {
            if (getX() + 1 == map.getPlayer().getX() && getY() == map.getPlayer().getY()) {
                return;
            }
            move(1, 0);
        } else if (map.getPlayer().getY() < getY() && map.getCell(getX(), getY() - 1).getType() != CellType.WALL && map.getCell(getX() - 1, getY() - 1).getType() != CellType.TREE && map.getCell(getX(), getY() - 1 ).getActor() == null) {
            if (getX()  == map.getPlayer().getX() && getY() - 1 == map.getPlayer().getY()) {
                return;
            }
            move(0, - 1);
        } else if (map.getPlayer().getY() > getY()&& map.getCell(getX(), getY() + 1).getType() != CellType.WALL && map.getCell(getX() -1, getY() + 1).getType() != CellType.TREE && map.getCell(getX(), getY() + 1).getActor() == null) {
            if (getX() == map.getPlayer().getX() && getY() + 1 == map.getPlayer().getY()) {
                return;
            }
            move(0, 1);
        }
    }

    @Override
    public String getTileName() {
        return "monster";
    }
}
