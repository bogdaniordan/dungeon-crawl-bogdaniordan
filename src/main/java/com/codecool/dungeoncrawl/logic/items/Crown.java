package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.popups.GameVerdictPopup;

public class Crown extends Item{
    public Crown(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "crown";
    }

    @Override
    public void applyEffect(GameMap map, Cell cell) {
        GameVerdictPopup.display("You found the crown!", "Play again");
    }
}