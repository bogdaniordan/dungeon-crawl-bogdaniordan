package com.codecool.dungeoncrawl.logic.popups;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WrongFilePopup {
    public static void display() {
        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label("IMPORT ERROR! Unfortunately the given file is in wrong format. Please try another one!");


        Button button= new Button("OK");


        button.setOnAction(e -> {
                    Platform.runLater(popupWindow::close);
                }
        );


        VBox layout = new VBox(10);


        layout.getChildren().addAll(label, button);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 700, 350);

        popupWindow.setScene(scene);

        popupWindow.showAndWait();
    }
}
