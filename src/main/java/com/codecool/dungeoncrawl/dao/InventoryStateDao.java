package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public interface InventoryStateDao {
    void add(InventoryState state);
    void update(InventoryState state);
    InventoryState get(int id);
    List<InventoryState> getAll();
    InventoryState getByPlayerModel(PlayerModel playerModel);
}
