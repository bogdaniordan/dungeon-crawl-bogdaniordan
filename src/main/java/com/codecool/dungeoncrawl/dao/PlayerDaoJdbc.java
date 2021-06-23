package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (player_name, hp, x, y, damage) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getX());
            statement.setInt(4, player.getY());
            statement.setInt(5, player.getDamage());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {
        System.out.println("BOD");
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE player SET hp = ?, x = ? , y = ?, damage = ? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, player.getHp());
            System.out.println(player.getHp());
            System.out.println(player.getPlayerName());
            st.setInt(2, player.getX());
            st.setInt(3, player.getY());
            st.setInt(4, player.getDamage());
            st.setInt(5, player.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PlayerModel get(int id) {
        return null;
    }

    @Override
    public List<PlayerModel> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, player_name, hp, x, y, damage FROM player";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<PlayerModel> result = new ArrayList<>();
            while (rs.next()) {
                //creates the player model object, sets its attributes and adds it to the result list
                PlayerModel playerModel = new PlayerModel(rs.getString(2), rs.getInt(4), rs.getInt(5));
                playerModel.setHp(rs.getInt(3));
                playerModel.setDamage(rs.getInt(6));
                playerModel.setId(rs.getInt(1));
                result.add(playerModel);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
