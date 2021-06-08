package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Knight extends Actor {
    public directionType direction = directionType.NORTH;

    private int X;

    private int Y;


    public Knight(Cell cell, int xCoordinate, int yCoordinate) {
        super(cell);
        setCoordinates(xCoordinate, yCoordinate);
        setDamage(3);
        setHealth(13);
    }

    private void setCoordinates(int xCoordinate, int yCoordinate) {
        X = xCoordinate;
        Y = yCoordinate;
    }

    public int getKnightX() {
        return X;
    }

    public int getKnightY() {
        return Y;
    }

    public enum directionType {
        NORTH, SOUTH, WEST, EAST
    }

    public void moveKnight() {
        if (direction == directionType.NORTH) {
            X--;
            direction = directionType.WEST;
        } else if (direction == directionType.WEST) {
            Y++;
            direction = directionType.SOUTH;
        } else if (direction == directionType.SOUTH) {
            X++;
            direction = directionType.EAST;
        } else if (direction == directionType.EAST) {
            Y--;
            direction = directionType.NORTH;
        }
    }

    @Override
    public String getTileName() { return "knight"; }
}
