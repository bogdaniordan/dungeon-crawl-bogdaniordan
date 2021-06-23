package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.button.LoadButton;
import com.codecool.dungeoncrawl.button.PickupHandler;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.Knight;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Cross;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.logic.popups.NamePopup;
import com.codecool.dungeoncrawl.logic.popups.SavePopup;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private String name = "Sergei Mizil";
    private String currentMap = "/map.txt";

    GameMap map = MapLoader.loadMap(currentMap);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label nameLabel = new Label();
    Label damageLabel = new Label();
    Label inventoryItems = new Label();
    Fight fight = new Fight(map);
    Movement movement = new Movement(map, fight);
    GameDatabaseManager dbManager;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        NamePopup.display("Enter your name:", "Play");
    }

    public void run(Stage primaryStage, String name) {
        setupDbManager();
        this.name = name;

        map.getPlayer().setName(name);

        GridPane ui = new GridPane();
        ui.setPrefWidth(300);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        ui.add(new Label("Damage: "), 0, 1);
        ui.add(damageLabel, 1, 1);

        ui.add(new Label("Inventory: "), 0, 2);
        ui.add(inventoryItems, 1, 2);

        ui.add(new Label("Name: "), 0, 3);
        ui.add(nameLabel, 1, 3);


        BorderPane borderPane = new BorderPane();

        Button pickUpButton = new Button("Pick item");
        pickUpButton.setOnAction(new PickupHandler(map, borderPane));
        ui.add(pickUpButton, 0 , 20);

        Button loadButton = new Button("Load game");
        loadButton.setOnAction(new LoadButton(dbManager));
        ui.add(loadButton, 0 , 60);

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased); //new

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        borderPane.requestFocus();
    }


    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        }
    }

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        moveEnemies();
        switch (keyEvent.getCode()) {
            case UP:
                if (movement.movementCheck(0, - 1) || name.equals("Bogdan")) {
                    map.getPlayer().move(0, - 1);
                    teleportToSecondMap(map.getPlayer().getHealth(), map.getPlayer().getInventory(),map.getPlayer().getDamage());
                }
                refresh();
                break;
            case DOWN:
                if (movement.movementCheck(0, 1) || name.equals("Bogdan")) {
                    map.getPlayer().move(0, 1);
                    teleportToSecondMap(map.getPlayer().getHealth(),map.getPlayer().getInventory(), map.getPlayer().getDamage());
                }
                refresh();
                break;
            case LEFT:
                if (movement.movementCheck(- 1, 0) || name.equals("Bogdan")) {
                    map.getPlayer().move(- 1, 0);
                    teleportToSecondMap(map.getPlayer().getHealth(), map.getPlayer().getInventory(),map.getPlayer().getDamage());
                }
                refresh();
                break;
            case RIGHT:
                if (movement.movementCheck(1, 0) || name.equals("Bogdan")) {
                    map.getPlayer().move(1,0);
                    teleportToSecondMap(map.getPlayer().getHealth(), map.getPlayer().getInventory(),map.getPlayer().getDamage());
                }
                refresh();
                break;
            case S:
                Player player = map.getPlayer();
                SavePopup.display(name, dbManager, player, currentMap);
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
        damageLabel.setText("" + map.getPlayer().getDamage());
        inventoryItems.setText("" + Utils.addItemsInventoryScreen(map));
        nameLabel.setText("" + name);
    }

    private void teleportToSecondMap(int oldHealth, List<Item> oldItems, int oldDamage) {
        if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType() == CellType.STAIRS) {
            currentMap = "/second_map.txt";
            map = MapLoader.loadMap(currentMap);
            fight = new Fight(map);
            movement = new Movement(map, fight);
            map.getPlayer().setDamage(oldDamage);
            map.getPlayer().setHealth(oldHealth);
            map.getPlayer().setInventory(oldItems);
            run(new Stage(), name);
        }
    }

    public void loadSavedGame(PlayerModel playerModel, GameState gameState, InventoryState inventoryState) {
        //loads map, name and player stats
        currentMap = gameState.getCurrentMap();
        map = MapLoader.loadMap(currentMap);
        name = playerModel.getPlayerName();
        map.getPlayer().newPosition(map, playerModel.getX(), playerModel.getY());
        map.getPlayer().setHealth(playerModel.getHp());

        //loads new inventory
        List<Item> newInventory = Utils.loadItemsFromDB(inventoryState.getCrossesNumber(), inventoryState.getKeysNumber(), inventoryState.getSwordsNumber());
        map.getPlayer().setInventory(newInventory);

        fight = new Fight(map);
        movement = new Movement(map, fight);
    }

    private void moveEnemies() {
        boolean moveKnight = true;
        boolean moveMonster = true;
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    if (cell.getActor() instanceof Knight) {
                        if (moveKnight) {
                            ((Knight) cell.getActor()).moveKnight();
                            moveKnight = false;
                        }
                    } else if (cell.getActor() instanceof Monster) {
                        if (moveMonster) {
                            ((Monster) cell.getActor()).attackPlayer(map);
                            moveMonster = false;
                        }
                    }
                }
            }
        }
    }
}
