package com.codecool.dungeoncrawl.logic.popups;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Utils;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Date;

public class ExportLocationPopup {
    public static <ObjectMapper> void display(Player player, GameMap gameMap) {
        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);

        TextField exportLocation = new TextField();

        Label label = new Label("Enter location + export file");


        Button button = new Button("Export");


        button.setOnAction(e -> {
                    Platform.runLater(() -> {
                        PlayerModel playerModel = new PlayerModel(player);

                        Utils.writeMapToFile(gameMap, player.getName());


                        GameState gameState = new GameState("/" + playerModel.getPlayerName() + ".txt", new Date(System.currentTimeMillis()), playerModel);
                        InventoryState inventoryState = new InventoryState(player.getCrossesNumber(), player.getSwordsNumber(), player.getKeysNumber(), playerModel);
                        gameState.setInventoryState(inventoryState);
                        gameState.addDiscoveredMap("map.txt");
                        if (gameState.getCurrentMap().equals("/second_map.txt")) {
                            gameState.addDiscoveredMap("second_map.txt");
                        }

                        try (Writer writer = new FileWriter(exportLocation.getText())) {
                            Gson gson = new GsonBuilder().create();
                            gson.toJson(gameState, writer);
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }

                        popupWindow.close();
                    });
                }
        );


        VBox layout= new VBox(10);


        layout.getChildren().addAll(label, exportLocation, button);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 300, 250);

        popupWindow.setScene(scene1);

        popupWindow.showAndWait();
    }
}
