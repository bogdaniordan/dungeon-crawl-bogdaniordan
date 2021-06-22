package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.GameMap;

public abstract class Actor implements Drawable {
    private int damage;
    private Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

//    public void newPosition(GameMap map, int x, int y) {
//        Cell cell = map.getCell(x, y);
//        this.cell.setActor(null);
//        cell.setActor(this);
//        this.cell = cell;
//    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {return damage;}

    public void increaseDamage(int increaseValue) {
        damage += increaseValue;
    }

    public void increaseHealth(int health) {
        this.health += health;
    }

    public void decreaseHealth(int health) {this.health -= health;}

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
