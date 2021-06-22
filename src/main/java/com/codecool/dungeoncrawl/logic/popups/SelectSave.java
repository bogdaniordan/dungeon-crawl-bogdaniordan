package com.codecool.dungeoncrawl.logic.popups;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class SelectSave {
    public static void display(GameDatabaseManager databaseManager) {
        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);

        ChoiceBox<PlayerModel> choiceBox = new ChoiceBox<>();

        List<PlayerModel> playerSaves = databaseManager.getPersistedPlayers();
        for (PlayerModel playerModel: playerSaves) {
            choiceBox.getItems().add(playerModel);
        }

        choiceBox.setOnAction((event) -> {
            PlayerModel savedPlayerModel = choiceBox.getSelectionModel().getSelectedItem();
            System.out.println(savedPlayerModel.toString());
            GameState savedGameState = databaseManager.findByPlayerModel(savedPlayerModel);
            InventoryState savedInventoryState = databaseManager.findInventoryState(savedPlayerModel);
            Main main = new Main();
            main.loadSavedGame(savedPlayerModel, savedGameState, savedInventoryState);
            main.run(new Stage(), savedPlayerModel.getPlayerName());
            popupWindow.close();
        });

        HBox hbox = new HBox(choiceBox);

        Button button = new Button("Cancel");

        VBox layout= new VBox(10);

        layout.getChildren().addAll(button);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hbox, 300, 250);

        popupWindow.setScene(scene);

        popupWindow.showAndWait();
    }
}
