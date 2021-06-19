package com.codecool.dungeoncrawl.logic;



public class Movement {

    private final GameMap map;
    private final Fight fight;

    public Movement(GameMap map, Fight fight) {
        this.map = map;
        this.fight = fight;
    }

    private boolean cellIsOccupied(int x, int y) {
        return map.getCell(map.getPlayer().getX() + x, map.getPlayer().getY() + y).getActor() != null;
    }

    private boolean cellIsNotWall(int x, int y) {
        return map.getCell(map.getPlayer().getX() + x, map.getPlayer().getY() + y).getType() == CellType.FLOOR ||
                map.getCell(map.getPlayer().getX() + x, map.getPlayer().getY() + y).getType() == CellType.STAIRS ||
                map.getCell(map.getPlayer().getX() + x, map.getPlayer().getY() + y).getType() == CellType.OPEN_DOOR ||
                map.getCell(map.getPlayer().getX() + x, map.getPlayer().getY() + y).getType() == CellType.FIRE;
    }

    public boolean movementCheck(int x, int y) {
        fireCheck(x, y);
        if(cellIsOccupied(x,y)){
            fight.fightEnemy(x, y);
        }
        if (cellIsNotWall(x, y) && !cellIsOccupied(x, y)) {
            return true;
        };
        return false;
    }

    private void fireCheck(int x, int y) {
        if (map.getCell(map.getPlayer().getX() + x, map.getPlayer().getY() + y).getType() == CellType.FIRE) {
            map.getPlayer().decreaseHealth(1);
            fight.gameLost();
        }
    }
}
