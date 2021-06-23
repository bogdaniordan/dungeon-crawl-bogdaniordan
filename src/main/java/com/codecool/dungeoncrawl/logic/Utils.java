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

    public static void writeMapToFile(GameMap map) {
        try {
            System.out.println(map.getWidth());
            System.out.println(map.getHeight());
            System.out.println("writing...");
            FileWriter myObj = new FileWriter("filename.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(myObj);
                StringBuilder s = new StringBuilder();
                s.append(map.getWidth());
                s.append(" ");
                s.append(map.getHeight());
                s.append(" ".repeat(19));
                bufferedWriter.write(String.valueOf(s));
                bufferedWriter.newLine();
                s.setLength(0);

                for (int i = 0; i < map.getWidth(); i++) {
                    for (int j = 0; j < map.getHeight(); j++) {
                        if (map.getCell(i,j).getType() == CellType.EMPTY) {
                            s.append(" ");
                        } else if (map.getCell(i,j).getType() == CellType.WALL) {
                            s.append('#');
                        } else if (map.getCell(i,j).getType() == CellType.TREE) {
                            s.append("t");
                        } else if (map.getCell(i,j).getType() == CellType.FLOOR && map.getCell(i, j).getActor() instanceof Skeleton) {
                            s.append("s");
                        } else if (map.getCell(i,j).getType() == CellType.FLOOR && map.getCell(i, j).getItem() instanceof Sword) {
                            s.append("z");
                        } else if(map.getCell(i,j).getType() == CellType.FLOOR && map.getCell(i, j).getItem() instanceof Key) {
                            s.append("k");
                        } else if (map.getCell(i,j).getType() == CellType.STAIRS) {
                            s.append("-");
                        } else if (map.getCell(i,j).getType() == CellType.FLOOR && map.getCell(i, j).getItem() instanceof Cross) {
                            s.append("c");
                        } else if (map.getCell(i,j).getType() == CellType.FLOOR && map.getCell(i, j).getItem() instanceof Crown) {
                            s.append("x");
                        } else if (map.getCell(i,j).getType() == CellType.FLOOR && map.getCell(i, j).getActor() instanceof Knight) {
                            s.append("n");
                        } else if (map.getCell(i,j).getType() == CellType.FLOOR && map.getCell(i, j).getActor() instanceof Monster) {
                            s.append("m");
                        } else if (map.getCell(i, j).getType() == CellType.CLOSED_DOOR) {
                            s.append("d");
                        } else if (map.getCell(i, j).getType() == CellType.FIRE) {
                            s.append("f");
                        } else if (map.getCell(i,j).getType() == CellType.FLOOR && map.getCell(i, j).getActor() instanceof Player) {
                            s.append("@");
                        }  else if (map.getCell(i,j).getType() == CellType.FLOOR) {
                            s.append(".");
                        }
                        if (j == 19) {
                            bufferedWriter.write(String.valueOf(s));
                            System.out.println(String.valueOf(s));
//                            myObj.write(System.getProperty("line.separator"));
                            bufferedWriter.newLine();
                            s.setLength(0);

                        }
                    }
                }
//                myObj.write(String.valueOf(s));
//                myObj.close();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
