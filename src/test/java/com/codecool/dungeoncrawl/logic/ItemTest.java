package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Cross;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    GameMap gameMap = new GameMap(10, 10, CellType.FLOOR);
    Player player = new Player(gameMap.getCell(2, 2));

    @BeforeEach
    void setup() {
        gameMap.setPlayer(player);
    }

    @Test
    void crossGivesHp() {
        Cross cross = new Cross(gameMap.getCell(3, 3));
        cross.applyEffect(gameMap, gameMap.getCell(3, 3));
        assertEquals(25, player.getHealth());
    }

    @Test
    void swordIncreasesDamage() {

        Sword sword = new Sword(gameMap.getCell(3, 3));
        sword.applyEffect(gameMap, gameMap.getCell(3, 3));
        assertEquals(6, player.getDamage());
    }

    @Test
    void keyOpensDoors() {
        gameMap.setCellType(CellType.CLOSED_DOOR, 5,5);
        Key key = new Key(gameMap.getCell(3,3));
        key.applyEffect(gameMap, gameMap.getCell(3,3));
        int closedDoorCount = 0;
        for (int i = 0; i < gameMap.getWidth(); i++) {
            for (int j = 0; j < gameMap.getHeight(); j++) {
                if (gameMap.getCell(i,j).getType() == CellType.CLOSED_DOOR) {
                    closedDoorCount++;
                }
            }
        }
        assertEquals(0, closedDoorCount);
    }
}
