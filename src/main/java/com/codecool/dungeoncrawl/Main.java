package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.popups.NamePopup;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label nameLabel = new Label();
    Movement movement = new Movement(map);
    private String name = "Crawl";


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        NamePopup.display("Enter your name:", "Play");
//        GridPane ui = new GridPane();
//        ui.setPrefWidth(200);
//        ui.setPadding(new Insets(10));
//
//        ui.add(new Label("Health: "), 0, 0);
//        ui.add(healthLabel, 1, 0);
//
//        BorderPane borderPane = new BorderPane();
//
//        borderPane.setCenter(canvas);
//        borderPane.setRight(ui);
//
//        Scene scene = new Scene(borderPane);
//        primaryStage.setScene(scene);
//        refresh();
//        scene.setOnKeyPressed(this::onKeyPressed);
//
//        primaryStage.setTitle("Dungeon Crawl");
//        primaryStage.show();
    }

    public void run(Stage primaryStage, String name) {
        this.name = name;

        GridPane ui = new GridPane();
        ui.setPrefWidth(300);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        ui.add(new Label("Name: "), 0, 1);
        ui.add(nameLabel, 1, 1);

        BorderPane borderPane = new BorderPane();

        Button pickUpButton = new Button("Pick item");
        pickUpButton.setOnAction(new PickupHandler(map, borderPane));
        ui.add(pickUpButton, 0 , 20);

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        borderPane.requestFocus();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                if (movement.movementCheck(0, - 1) || name.equals("Bogdan")) {
                    map.getPlayer().move(0, -1);
                }
                refresh();
                break;
            case DOWN:
                if (movement.movementCheck(0, 1) || name.equals("Bogdan")) {
                    map.getPlayer().move(0, 1);
                }
                refresh();
                break;
            case LEFT:
                if (movement.movementCheck(- 1, 0) || name.equals("Bogdan")) {
                    map.getPlayer().move(-1, 0);
                }
                refresh();
                break;
            case RIGHT:
                if (movement.movementCheck(1, 0) || name.equals("Bogdan")) {
                    map.getPlayer().move(1,0);
                }
                refresh();
                break;
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        nameLabel.setText("" + name);
    }
}
