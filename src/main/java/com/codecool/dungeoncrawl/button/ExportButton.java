package com.codecool.dungeoncrawl.button;


import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.popups.ExportLocationPopup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ExportButton implements EventHandler<ActionEvent> {
    private final GameDatabaseManager gameDatabaseManager;
    private final Player player;
    private final String currentMap;

    public ExportButton(GameDatabaseManager gameDatabaseManager, Player player, String currentMap) {
        this.gameDatabaseManager = gameDatabaseManager;
        this.player = player;
        this.currentMap = currentMap;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ExportLocationPopup.display(gameDatabaseManager, player, currentMap);
    }
}
