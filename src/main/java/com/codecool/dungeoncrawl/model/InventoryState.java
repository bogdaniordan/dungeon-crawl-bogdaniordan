package com.codecool.dungeoncrawl.model;


public class InventoryState extends BaseModel {
    private int crossesNumber;
    private int swordsNumber;
    private int keysNumber;
    private PlayerModel player;

    public InventoryState(int crossesNumber, int swordsNumber, int keysNumber, PlayerModel player) {
        this.crossesNumber = crossesNumber;
        this.swordsNumber = swordsNumber;
        this.keysNumber = keysNumber;
        this.player = player;
    }

    public int getCrossesNumber() {
        return crossesNumber;
    }

    public void setCrossesNumber(int crossesNumber) {
        this.crossesNumber = crossesNumber;
    }

    public int getSwordsNumber() {
        return swordsNumber;
    }

    public void setSwordsNumber(int swordsNumber) {
        this.swordsNumber = swordsNumber;
    }

    public int getKeysNumber() {
        return keysNumber;
    }

    public void setKeysNumber(int keysNumber) {
        this.keysNumber = keysNumber;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }
}
