package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Knight;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static StringBuilder addItemsInventoryScreen(GameMap map) {
        StringBuilder inventoryItemsRepresentation = new StringBuilder();
        for (Item item: map.getPlayer().getInventory()) {
            inventoryItemsRepresentation.append(item.getClass().getSimpleName()).append(" \n");
        }
        return inventoryItemsRepresentation;
    }

    public static List<Item> loadItemsFromDB(int crosses, int keys, int swords) {
        List<Item> newInventory = new ArrayList<>();
        for (int i = 0; i < crosses; i++) {
            newInventory.add(new Cross());
        }
        for(int j = 0; j < keys; j++) {
            newInventory.add(new Key());
        }
        for(int x = 0; x < swords; x++) {
            newInventory.add(new Sword());
        }
        return newInventory;
    }

    public static void writeMapToFile(GameMap map, String filename) {
        try {
            System.out.println("Writing map to file...");
            FileWriter myObj = new FileWriter("src/main/resources/" + filename + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(myObj);
                StringBuilder mapStringRepresentation = new StringBuilder();
                mapStringRepresentation.append(map.getWidth());
                mapStringRepresentation.append(" ");
                mapStringRepresentation.append(map.getHeight());
                mapStringRepresentation.append(" ".repeat(19));
                bufferedWriter.write(String.valueOf(mapStringRepresentation));
                bufferedWriter.newLine();
                mapStringRepresentation.setLength(0);
                for (int i = 0; i < map.getHeight(); i++) {
                    for (int j = 0; j < map.getWidth(); j++) {
                        if (map.getCell(j, i).getType() == CellType.EMPTY) {
                            mapStringRepresentation.append(" ");
                        } else if (map.getCell(j, i).getType() == CellType.WALL) {
                            mapStringRepresentation.append('#');
                        } else if (map.getCell(j, i).getType() == CellType.TREE) {
                            mapStringRepresentation.append("t");
                        } else if (map.getCell(j, i).getType() == CellType.OPEN_DOOR) {
                           mapStringRepresentation.append("g");
                        } else if (map.getCell(j, i).getType() == CellType.STAIRS) {
                            mapStringRepresentation.append("-");
                        } else if (map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getActor() instanceof Skeleton) {
                            mapStringRepresentation.append("s");
                        } else if (map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getItem() instanceof Sword) {
                            mapStringRepresentation.append("z");
                        } else if(map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getItem() instanceof Key) {
                            mapStringRepresentation.append("k");
                        }  else if (map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getItem() instanceof Cross) {
                            mapStringRepresentation.append("c");
                        } else if (map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getItem() instanceof Crown) {
                            mapStringRepresentation.append("x");
                        } else if (map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getActor() instanceof Knight) {
                            mapStringRepresentation.append("n");
                        } else if (map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getActor() instanceof Monster) {
                            mapStringRepresentation.append("m");
                        } else if (map.getCell(j, i).getType() == CellType.CLOSED_DOOR) {
                            mapStringRepresentation.append("d");
                        } else if (map.getCell(j, i).getType() == CellType.FIRE) {
                            mapStringRepresentation.append("f");
                        } else if (map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getActor() instanceof Player) {
                            mapStringRepresentation.append("@");
                        }  else if (map.getCell(j, i).getType() == CellType.FLOOR) {
                            mapStringRepresentation.append(".");
                        }
                        if (j == map.getWidth() - 1) {
                            // writes every line for map text representation
                            bufferedWriter.write(String.valueOf(mapStringRepresentation));
                            bufferedWriter.newLine();
                            mapStringRepresentation.setLength(0);
                        }
                    }
                }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
