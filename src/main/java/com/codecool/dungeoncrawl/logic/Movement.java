package com.codecool.dungeoncrawl.logic;

public class Movement {

    private final GameMap map;

    public Movement(GameMap map) {
        this.map = map;
    }

    private boolean cellIsOccupied(int x, int y) {
        return map.getCell(map.getPlayer().getX() + x, map.getPlayer().getY() + y).getActor() != null;
    }

    private boolean cellIsNotWall(int x, int y) {
        return map.getCell(map.getPlayer().getX() + x, map.getPlayer().getY() + y).getType() == CellType.FLOOR ||
                map.getCell(map.getPlayer().getX() + x, map.getPlayer().getY() + y).getType() == CellType.STAIRS;
    }

    public boolean movementCheck(int x, int y) {
        if (cellIsNotWall(x, y) && !cellIsOccupied(x, y)) {
            return true;
        };
        return false;
    }

}
