package com.codecool.dungeoncrawl.logic.popups;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryState;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoadJSONPopup {
    public static void display() {
        Stage popupWindow = new Stage();;

        popupWindow.initModality(Modality.APPLICATION_MODAL);


        Label label1 = new Label("Select a saved file to load");


        Button selectFileBtn = new Button("Select file");
        Button cancelBtn = new Button("Cancel");

        FileChooser fileChooser = new FileChooser();

        selectFileBtn.setOnAction(e -> {
                    Platform.runLater(() -> {
                        File selectedFile = fileChooser.showOpenDialog(popupWindow);
                        if ((selectedFile.toString().endsWith("json"))) {
                            try {
                                Gson gson = new Gson();
                                JsonReader jsonReader = new JsonReader(new FileReader(selectedFile.toString()));
                                GameState gameState = gson.fromJson(jsonReader, GameState.class);
                                InventoryState inventoryState = gameState.getInventoryState();

                                Main main = new Main();
                                main.loadSavedGame(gameState.getPlayer(), gameState, inventoryState);
                                main.run(new Stage(), gameState.getPlayer().getPlayerName());
                                popupWindow.close();
                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            }
                        } else {
                            WrongFilePopup.display();
                        }
                    });
                }
        );
        cancelBtn.setOnAction(e -> {
            Platform.runLater(popupWindow::close);
        });

        VBox layout = new VBox(10);


        layout.getChildren().addAll(label1, selectFileBtn);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 250);

        popupWindow.setScene(scene);

        popupWindow.showAndWait();
    }
}
