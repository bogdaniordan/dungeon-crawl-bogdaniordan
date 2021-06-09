package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Knight extends Actor {
    public directionType direction = directionType.NORTH;

    public Knight(Cell cell) {
        super(cell);
        setDamage(3);
        setHealth(13);
    }

    public enum directionType {
        NORTH, SOUTH, WEST, EAST
    }

    public void moveKnight() {
        if (direction == directionType.NORTH) {
            move(- 1 , 0);
            direction = directionType.WEST;
        } else if (direction == directionType.WEST) {
            move(0, 1);
            direction = directionType.SOUTH;
        } else if (direction == directionType.SOUTH) {
            move(1, 0);
            direction = directionType.EAST;
        } else if (direction == directionType.EAST) {
            move(0, - 1);
            direction = directionType.NORTH;
        }
    }

    @Override
    public String getTileName() { return "knight"; }
}
