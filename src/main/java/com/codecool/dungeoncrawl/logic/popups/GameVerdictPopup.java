package com.codecool.dungeoncrawl.logic.popups;

import com.codecool.dungeoncrawl.Main;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameVerdictPopup {

    public static void display(String gameVerdict, String buttonMessage)
    {
        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);


        Label label1 = new Label(gameVerdict);


        Button button1= new Button(buttonMessage);


        button1.setOnAction(e -> {
                    popupWindow.close();
                    Platform.runLater(() -> {
                        Main main = new Main();
                        main.run(new Stage(), "Sergei Mizil");
                    });
                }
        );


        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 300, 250);

        popupWindow.setScene(scene1);

        popupWindow.showAndWait();

    }
}
