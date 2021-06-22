package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;
    private InventoryStateDao inventoryStateDao;


    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
        inventoryStateDao = new InventoryStateDaoJdbc(dataSource);
    }

    public PlayerModel savePlayer(Player player) {
        PlayerModel playerModel = new PlayerModel(player);
        playerDao.add(playerModel);
        return playerModel;
    }

    public GameState findByPlayerModel(PlayerModel playerModel) {
        return gameStateDao.getByPlayerModel(playerModel);
    }

    public void updateGameState(GameState gameState) {
        gameStateDao.update(gameState);
    }

    public void updatePlayer(PlayerModel player)  {
        playerDao.update(player);
    }

    public List<PlayerModel> getPersistedPlayers() {
        return playerDao.getAll();
    }

    public void saveGameState(GameState gameState) {
        gameStateDao.add(gameState);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("DB");
        String user = System.getenv("USER");
        String password = System.getenv("PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
