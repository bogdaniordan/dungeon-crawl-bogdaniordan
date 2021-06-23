package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Knight;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Cross;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
    Movement movement =  new Movement(gameMap, new Fight(gameMap));
    Knight knight = new Knight(gameMap.getCell(2,2));

    @Test
    void moveUpdatesCells() {
        Player player = new Player(gameMap.getCell(1, 1));
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
        assertNull(gameMap.getCell(1, 1).getActor());
        assertEquals(player, gameMap.getCell(2, 1).getActor());
    }

    @Test
    void cannotMoveIntoWall() {
        gameMap.getCell(2, 1).setType(CellType.WALL);
        Player player = new Player(gameMap.getCell(1, 1));
        gameMap.setPlayer(player);
        if (movement.movementCheck(1, 0)) {
            player.move(1, 0);
        }
        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void cannotMoveOutOfMap() {
        Player player = new Player(gameMap.getCell(2, 1));
        gameMap.setPlayer(player);
        if (player.getX() + 1 > gameMap.getWidth()) {
            if (movement.movementCheck(1, 0)) {
                player.move(1, 0);
            }
        }
        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void cannotMoveIntoAnotherActor() {
        Player player = new Player(gameMap.getCell(1, 1));
        gameMap.setPlayer(player);
        Skeleton skeleton = new Skeleton(gameMap.getCell(2, 1));
        if (movement.movementCheck(1, 0)) {
            player.move(1, 0);
        }
        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
        assertEquals(2, skeleton.getX());
        assertEquals(1, skeleton.getY());
        assertEquals(skeleton, gameMap.getCell(2, 1).getActor());
    }

    @Test
    void newActorPositionAfterMapLoading() {
        Player player = new Player(gameMap.getCell(2,2));
        gameMap.setPlayer(player);
        player.newPosition(gameMap, 1,1);
        assertEquals(1, gameMap.getPlayer().getX());
        assertEquals(1, gameMap.getPlayer().getY());
    }

    @Test
    void knightMovesWest() {
        knight.moveKnight();
        assertEquals(1, knight.getX());
        assertEquals(2, knight.getY());
    }


    @Test
    void knightMovesSouth() {
        gameMap = new GameMap(4, 4, CellType.FLOOR);
        knight = new Knight(gameMap.getCell(2,2));
        knight.moveKnight();
        knight.moveKnight();
        assertEquals(1, knight.getX());
        assertEquals(3, knight.getY());
    }

    @Test
    void knightMovesEast() {
        gameMap = new GameMap(4, 4, CellType.FLOOR);
        knight = new Knight(gameMap.getCell(2,2));
        knight.moveKnight();
        knight.moveKnight();
        knight.moveKnight();
        assertEquals(2, knight.getX());
        assertEquals(3, knight.getY());
    }


    @Test
    void knightMovesNorth() {
        gameMap = new GameMap(4, 4, CellType.FLOOR);
        knight = new Knight(gameMap.getCell(2,2));
        knight.moveKnight();
        knight.moveKnight();
        knight.moveKnight();
        knight.moveKnight();
        assertEquals(2, knight.getX());
        assertEquals(2, knight.getY());
    }


    @Test
    void instantiateKnight() {
        gameMap = new GameMap(4, 4, CellType.FLOOR);
        knight = new Knight(gameMap.getCell(3,3));
        assertTrue(gameMap.getCell(3,3).getActor() instanceof Knight);
    }

    @Test
    void countInventoryItems() {
        Player player = new Player(gameMap.getCell(2,2));
        player.addToInventory(new Cross());
        player.addToInventory(new Cross());
        player.addToInventory(new Sword());
        player.addToInventory(new Key());
        assertEquals(2, player.getCrossesNumber());
        assertEquals(1, player.getSwordsNumber());
        assertEquals(1, player.getKeysNumber());
    }
}