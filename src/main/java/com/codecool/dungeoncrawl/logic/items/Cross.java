package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Cross extends Item{
    private final int givenHp = 5;

    public int getGivenHp() {
        return givenHp;
    }

    public Cross(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "cross";
    }
}
