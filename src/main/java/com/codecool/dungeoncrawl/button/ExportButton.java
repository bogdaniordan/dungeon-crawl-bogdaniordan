package com.codecool.dungeoncrawl.button;


import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.popups.ExportLocationPopup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ExportButton implements EventHandler<ActionEvent> {
    private final Player player;
    private final GameMap gameMap;

    public ExportButton(Player player, GameMap gameMap) {
        this.player = player;
        this.gameMap = gameMap;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ExportLocationPopup.display(player, gameMap);
    }
}
