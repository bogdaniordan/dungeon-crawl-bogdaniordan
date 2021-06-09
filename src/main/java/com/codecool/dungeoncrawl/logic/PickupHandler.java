package com.codecool.dungeoncrawl.logic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;

public class PickupHandler implements EventHandler<ActionEvent> {
    private final GameMap map;
    private final BorderPane borderPane;
    private final ItemEffect itemEffect;

    public PickupHandler(GameMap map, BorderPane borderPane, ItemEffect itemEffect) {
        this.map = map;
        this.borderPane = borderPane;
        this.itemEffect = itemEffect;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Cell cell = map.getCell(map.getPlayer().getX(), map.getPlayer().getY());
        if (cell.getItem() != null) {
            itemEffect.increasePlayerDamage(cell, map);
            itemEffect.increasePlayerHealth(cell, map);
            itemEffect.pickUpCrown(cell);
            itemEffect.openDoor(cell, map);
            cell.setItem(null);
        }
        borderPane.requestFocus();
    }


}
