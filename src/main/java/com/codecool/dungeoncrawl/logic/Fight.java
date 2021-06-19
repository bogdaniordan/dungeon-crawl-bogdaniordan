package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Knight;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.popups.GameVerdictPopup;

public class Fight {
    private final GameMap map;

    public Fight(GameMap map) {
        this.map = map;
    }

    public void fightEnemy(int x, int y) {
        Actor enemy = map.getCell(map.getPlayer().getX() + x, map.getPlayer().getY() + y).getActor();
        Player player = map.getPlayer();
        player.decreaseHealth(enemy.getDamage());
        enemy.decreaseHealth(map.getPlayer().getDamage());
        if (enemy.getHealth() <= 0) {
            enemy.getCell().setActor(null);
        }
        gameLost();
    }


    public void gameLost() {
        if (map.getPlayer().getHealth() <= 0) {
            System.out.println("Dead!");
            GameVerdictPopup.display("You have died!", "Play again");
        }
    }
}
