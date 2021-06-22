package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.util.List;

public class InventoryStateDaoJdbc implements InventoryStateDao{
    private DataSource dataSource;

    public InventoryStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(InventoryState state) {

    }

    @Override
    public void update(InventoryState state) {

    }

    @Override
    public InventoryState get(int id) {
        return null;
    }

    @Override
    public List<InventoryState> getAll() {
        return null;
    }

    @Override
    public InventoryState getByPlayerModel(PlayerModel playerModel) {
        return null;
    }
}
