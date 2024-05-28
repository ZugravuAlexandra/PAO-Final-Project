package database.repository;

import database.config.DatabaseConfiguration;
import database.entitati.Evenimente;
import database.entitati.Adresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EvenimentRepo {
    private Connection connection;

    private static final String INSERT_QUERY = "INSERT INTO Evenimente (titlu, data, ora, adresa_id, pret) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Evenimente";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Evenimente WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE Evenimente SET titlu = ?, data = ?, ora = ?, adresa_id = ?, pret = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Evenimente WHERE id = ?";

    public EvenimentRepo() {
        connection = DatabaseConfiguration.getDatabaseConnection();
    }

    public void create(Evenimente eveniment) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, eveniment.getTitlu());
            statement.setString(2, eveniment.getData());
            statement.setString(3, eveniment.getOra());
            statement.setInt(4, eveniment.getAdresa().getId());
            statement.setInt(5, eveniment.getPret());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Evenimente> readAll() {
        List<Evenimente> evenimente = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Evenimente eveniment = mapResultSetToEveniment(resultSet);
                evenimente.add(eveniment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evenimente;
    }

    public Evenimente readById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToEveniment(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Evenimente eveniment) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, eveniment.getTitlu());
            statement.setString(2, eveniment.getData());
            statement.setString(3, eveniment.getOra());
            statement.setInt(4, eveniment.getAdresa().getId());
            statement.setInt(5, eveniment.getPret());
            statement.setInt(6, eveniment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Evenimente mapResultSetToEveniment(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String titlu = resultSet.getString("titlu");
        String data = resultSet.getString("data");
        String ora = resultSet.getString("ora");
        int adresaId = resultSet.getInt("adresa_id");
        Adresa adresa = new AdresaRepo().readById(adresaId);
        int pret = resultSet.getInt("pret");

        return new Evenimente(id, titlu, data, ora, adresa, pret);
    }
}
