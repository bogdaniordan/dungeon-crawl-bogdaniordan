package com.codecool.dungeoncrawl.logic.popups;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Date;

public class OverwritePopup {
    public void display(GameDatabaseManager dbManager, PlayerModel playerModel, String currentMap, Player player)
    {
        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);

        Label label1 = new Label("Would you like to overwrite the already existing state?");


        Button yesButton= new Button("Yes");

        Button noButton = new Button("No");


        yesButton.setOnAction(e -> {
                    popupWindow.close();
                    Platform.runLater(() -> {
                        System.out.println("updating");

                        PlayerModel newPlayerModel = new PlayerModel(player);
                        newPlayerModel.setId(playerModel.getId());

                        dbManager.updatePlayer(newPlayerModel);//update player

                        //write file map with player's name
                        // pass the new filemap as the currentMap

                        GameState gameState = new GameState(currentMap, new Date(System.currentTimeMillis()), newPlayerModel);

                        InventoryState inventoryState = new InventoryState(player.getCrossesNumber(), player.getSwordsNumber(), player.getKeysNumber(), newPlayerModel);
                        inventoryState.setId(playerModel.getId());

                        dbManager.updateGameState(gameState);//update game state
                        dbManager.updateInventory(inventoryState); //update inventory state
                    });
                }
        );

        noButton.setOnAction(e -> {
            //take input for player name
            //save new player
            // save new game state with new player saved id(maybe retrieve the player)
            newSavePopup(dbManager, currentMap, player);
            popupWindow.close();
        });


        VBox layout= new VBox(10);

        layout.getChildren().addAll(label1, yesButton, noButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 250);

        popupWindow.setScene(scene);

        popupWindow.showAndWait();

    }

    private void newSavePopup(GameDatabaseManager dbManager, String currentMap, Player player) {
        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);

        TextField nameInput = new TextField();

        Label label = new Label("Enter a new save name: ");


        Button button1= new Button("Save");


        button1.setOnAction(e -> {
                    Platform.runLater(() -> {
                        player.setName(nameInput.getText());
                        PlayerModel playerModel = dbManager.savePlayer(player);

                        //write file map with player's name
                        // pass the new filemap as the currentMap

                        dbManager.saveGameState(new GameState(currentMap, new Date(System.currentTimeMillis()), playerModel)); //save game state linked to the player id
                        dbManager.saveInventoryState(new InventoryState(player.getCrossesNumber(), player.getSwordsNumber(), player.getKeysNumber(), playerModel));
                        popupWindow.close();
                    });
                }
        );


        VBox layout = new VBox(10);


        layout.getChildren().addAll(label, nameInput, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene= new Scene(layout, 300, 250);

        popupWindow.setScene(scene);

        popupWindow.showAndWait();
    }
}
