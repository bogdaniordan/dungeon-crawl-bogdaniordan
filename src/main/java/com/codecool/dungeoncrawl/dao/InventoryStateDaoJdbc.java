package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class InventoryStateDaoJdbc implements InventoryStateDao{
    private DataSource dataSource;

    public InventoryStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(InventoryState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO inventory_state (crosses_number, swords_number, keys_number, player_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, state.getCrossesNumber());
            statement.setInt(2, state.getSwordsNumber());
            statement.setInt(3, state.getKeysNumber());
            statement.setInt(4, state.getPlayer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(InventoryState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE inventory_state SET crosses_number = ?, swords_number = ?, keys_number = ? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, state.getCrossesNumber());
            st.setInt(2, state.getSwordsNumber());
            st.setInt(3, state.getKeysNumber());
            st.setInt(4, state.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        try (Connection conn = dataSource.getConnection()) {

            String sql = "SELECT id, crosses_number, swords_number, keys_number FROM inventory_state WHERE player_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, playerModel.getId());
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }

            int inventoryStateId = rs.getInt(1);
            int crossesNumber = rs.getInt(2);
            int swordsNumber = rs.getInt(3);
            int keysNumber = rs.getInt(4);

            InventoryState inventoryState = new InventoryState(crossesNumber, swordsNumber, keysNumber, playerModel);

            inventoryState.setId(inventoryStateId);
            return inventoryState;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
