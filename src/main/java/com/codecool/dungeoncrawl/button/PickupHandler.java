package com.codecool.dungeoncrawl.button;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;
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
            Item item = cell.getItem();
            item.applyEffect(map, cell);
            map.getPlayer().addToInventory(cell.getItem());
            cell.setItem(null);
        }
        borderPane.requestFocus();
    }
}
