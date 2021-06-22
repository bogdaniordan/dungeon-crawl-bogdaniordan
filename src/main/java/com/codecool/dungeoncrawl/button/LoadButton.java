package com.codecool.dungeoncrawl.button;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.popups.SelectSave;
import javafx.event.EventHandler;

import javafx.event.ActionEvent;

public class LoadButton implements EventHandler<ActionEvent> {
    private final GameDatabaseManager databaseManager;

    public LoadButton(GameDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        SelectSave.display(databaseManager);
    }
}
