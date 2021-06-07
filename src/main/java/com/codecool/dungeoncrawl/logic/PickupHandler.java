package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Sword;
import javafx.scene.layout.BorderPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;

public class PickupHandler implements EventHandler<ActionEvent> {
    private final GameMap map;
    private final BorderPane borderPane;

    public PickupHandler(GameMap map, BorderPane borderPane) {
        this.map = map;
        this.borderPane = borderPane;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Cell cell = map.getCell(map.getPlayer().getX(), map.getPlayer().getY());
        if (cell.getItem() != null) {
            map.getPlayer().addToInventory(cell.getItem());
            increasePlayerDamage(cell);
            cell.setItem(null);
        }
        borderPane.requestFocus();
    }

    private void increasePlayerDamage(Cell cell) {
        if (cell.getItem() instanceof Sword) {
            map.getPlayer().increaseDamage(((Sword) cell.getItem()).getDamage());
        }
    }
}
