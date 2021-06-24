package com.codecool.dungeoncrawl.logic.popups;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Utils;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.List;

public class SavePopup {

    public static void display(String playerName, GameDatabaseManager dbManager, Player player, GameMap gameMap) {
        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);


        Label label = new Label("Save game");


        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        cancelButton.setOnAction(e -> {
            popupWindow.close();
        });

        saveButton.setOnAction(e -> {
                    popupWindow.close();
                    Platform.runLater(() -> {
                        List<PlayerModel> savedPlayers = dbManager.getPersistedPlayers();
                        for (PlayerModel playerModel: savedPlayers) {
                            if (playerModel.getPlayerName().equals(playerName)) {
                                //if there is a saved player with the same name, show overwrite popup
                                OverwritePopup overwritePopup = new OverwritePopup();
                                overwritePopup.display(dbManager, playerModel, playerName, player, gameMap);
                                return;
                            }
                        }
                        // if the player name doesn't exist in the db, the player and game state get saved

                        //write file map with player's name as the filename
                        Utils.writeMapToFile(gameMap, playerName);
                        // pass the new filemap as the currentMap

                        PlayerModel playerModel = dbManager.savePlayer(player);
                        dbManager.saveGameState(new GameState("/" + playerName + ".txt", new Date(System.currentTimeMillis()), playerModel)); //save game state linked to the player id
                        dbManager.saveInventoryState(new InventoryState(player.getCrossesNumber(), player.getSwordsNumber(), player.getKeysNumber(), playerModel));
                        popupWindow.close();
                    });
                }
        );


        VBox layout= new VBox(10);

        layout.getChildren().addAll(label, saveButton, cancelButton );

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 250);

        popupWindow.setScene(scene);

        popupWindow.showAndWait();
    }
}
