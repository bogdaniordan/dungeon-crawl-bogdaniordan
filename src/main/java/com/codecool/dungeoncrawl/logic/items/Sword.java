package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Sword extends Item {
    private int damage = 1;

    public int getDamage() {
        return damage;
    }

    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public void applyEffect(GameMap map, Cell cell) {
        map.getPlayer().increaseDamage(((Sword) cell.getItem()).getDamage());
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
