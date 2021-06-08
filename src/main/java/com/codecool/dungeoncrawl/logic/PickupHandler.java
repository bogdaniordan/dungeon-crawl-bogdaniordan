package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.items.Cross;
import com.codecool.dungeoncrawl.logic.items.Crown;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.logic.popups.GameVerdictPopup;
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
            itemEffect.increasePlayerDamage(cell, map;
            itemEffect.increasePlayerHealth(cell, map);
            itemEffect.pickUpCrown(cell, map);
            cell.setItem(null);
        }
        borderPane.requestFocus();
    }

//    private void increasePlayerDamage(Cell cell) {
//        if (cell.getItem() instanceof Sword) {
//            map.getPlayer().increaseDamage(((Sword) cell.getItem()).getDamage());
//        }
//    }
//
//    private void increasePlayerHealth(Cell cell) {
//        if (cell.getItem() instanceof Cross) {
//            map.getPlayer().increaseHealth(((Cross) cell.getItem()).getGivenHp());
//        }
//    }
//
//    private void pickUpCrown(Cell cell) {
//        if (cell.getItem() instanceof Crown) {
//            GameVerdictPopup.display("You found the crown!", "Play again");
//        }
//    }
}
