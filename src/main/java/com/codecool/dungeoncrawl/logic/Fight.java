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

//    public void fightEnemy(int x, int y){
//        Actor nextCell = map.getCell(map.getPlayer().getX() + x, map.getPlayer().getY() + y).getActor();
//        Player player = (Player) map.getPlayer();
//        if(nextCell != null){
//            if(nextCell instanceof Skeleton){
//                Skeleton skeleton = (Skeleton) nextCell;
//                player.decreaseHealth(skeleton.getDamage());
//                skeleton.decreaseHealth(player.getDamage());
//                if(skeleton.getHealth() <= 0){
//                    skeleton.getCell().setActor(null);
//                }
//                if(player.getHealth() <= 0){
//                    GameVerdictPopup.display("You have died!", "Play again");
//                }
//            } else if(nextCell instanceof Knight) {
//                Knight knight = (Knight) nextCell;
//                player.decreaseHealth(knight.getDamage());
//                knight.decreaseHealth(player.getDamage());
//                System.out.println(knight.getHealth());
//                if(knight.getHealth() <= 0){
//                    knight.getCell().setActor(null);
//                }
//                if(player.getHealth() <= 0){
//                    GameVerdictPopup.display("You have died!", "Play again");
//                }
//            }
//        }
//    }

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
