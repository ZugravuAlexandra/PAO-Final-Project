package database.repository;

import database.config.DatabaseConfiguration;
import database.entitati.Utilizator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilizatorRepo {
    private Connection connection;

    private static final String INSERT_QUERY = "INSERT INTO Utilizator (nume, prenume, email, telefon) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Utilizator";
    private static final String UPDATE_QUERY = "UPDATE Utilizator SET nume = ?, prenume = ?, email = ?, telefon = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Utilizator WHERE id = ?";

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Utilizator WHERE id = ?";
    public UtilizatorRepo() {
        connection = DatabaseConfiguration.getDatabaseConnection();
    }

    public void create(Utilizator utilizator) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, utilizator.getNume());
            statement.setString(2, utilizator.getPrenume());
            statement.setString(3, utilizator.getEmail());
            statement.setString(4, utilizator.getTelefon());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Utilizator> readAll() {
        List<Utilizator> utilizatori = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Utilizator utilizator = new Utilizator();
                utilizator.setId(resultSet.getInt("id"));
                utilizator.setNume(resultSet.getString("nume"));
                utilizator.setPrenume(resultSet.getString("prenume"));
                utilizator.setEmail(resultSet.getString("email"));
                utilizator.setTelefon(resultSet.getString("telefon"));
                utilizatori.add(utilizator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilizatori;
    }

    public Utilizator findById(int id) {
        Utilizator utilizator = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    utilizator = new Utilizator(
                            resultSet.getInt("id"),
                            resultSet.getString("nume"),
                            resultSet.getString("prenume"),
                            resultSet.getString("email"),
                            resultSet.getString("telefon")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilizator;
    }

    public void update(Utilizator utilizator) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, utilizator.getNume());
            statement.setString(2, utilizator.getPrenume());
            statement.setString(3, utilizator.getEmail());
            statement.setString(4, utilizator.getTelefon());
            statement.setInt(5, utilizator.getId());
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
}
