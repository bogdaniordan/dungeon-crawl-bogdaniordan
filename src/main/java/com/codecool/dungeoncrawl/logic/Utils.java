package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Knight;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.BufferedWriter;
import java.io.File;
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
//            FileWriter myObj = new FileWriter("src/main/resources/filename.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(myObj);
                StringBuilder s = new StringBuilder();
                s.append(map.getWidth());
                s.append(" ");
                s.append(map.getHeight());
                s.append(" ".repeat(19));
                bufferedWriter.write(String.valueOf(s));
                bufferedWriter.newLine();
                s.setLength(0);
                for (int i = 0; i < map.getHeight(); i++) {
                    for (int j = 0; j < map.getWidth(); j++) {
                        if (map.getCell(j, i).getType() == CellType.EMPTY) {
                            s.append(" ");
                        } else if (map.getCell(j, i).getType() == CellType.WALL) {
                            s.append('#');
                        } else if (map.getCell(j, i).getType() == CellType.TREE) {
                            s.append("t");
                        } else if (map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getActor() instanceof Skeleton) {
                            s.append("s");
                        } else if (map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getItem() instanceof Sword) {
                            s.append("z");
                        } else if(map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getItem() instanceof Key) {
                            s.append("k");
                        } else if (map.getCell(j, i).getType() == CellType.STAIRS) {
                            s.append("-");
                        } else if (map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getItem() instanceof Cross) {
                            s.append("c");
                        } else if (map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getItem() instanceof Crown) {
                            s.append("x");
                        } else if (map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getActor() instanceof Knight) {
                            s.append("n");
                        } else if (map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getActor() instanceof Monster) {
                            s.append("m");
                        } else if (map.getCell(j, i).getType() == CellType.CLOSED_DOOR) {
                            s.append("d");
                        } else if (map.getCell(j, i).getType() == CellType.FIRE) {
                            s.append("f");
                        } else if (map.getCell(j, i).getType() == CellType.FLOOR && map.getCell(j, i).getActor() instanceof Player) {
                            s.append("@");
                        }  else if (map.getCell(j, i).getType() == CellType.FLOOR) {
                            s.append(".");
                        }
                        if (j == 24) {
                            bufferedWriter.write(String.valueOf(s));
//                            System.out.println(String.valueOf(s));
                            bufferedWriter.newLine();
                            s.setLength(0);
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
