
package database.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.config.DatabaseConfiguration;
import database.entitati.Adresa;
public class AdresaRepo {

    private static Connection connection;

    private static final String INSERT_QUERY = "INSERT INTO adrese (id, tara, oras, strada) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM adrese";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM adrese WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE adrese SET tara=?, oras=?, strada=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM adrese WHERE id=?";

    public AdresaRepo() {
        connection = DatabaseConfiguration.getDatabaseConnection();
    }

    // Metoda pentru a crea o adresă în baza de date
    public void create(Adresa adresa) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setInt(1, adresa.getId());
            statement.setString(2, adresa.getTara());
            statement.setString(3, adresa.getOras());
            statement.setString(4, adresa.getStrada());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metoda pentru a citi toate adresele din baza de date
    public List<Adresa> readAll() {
        List<Adresa> adrese = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Adresa adresa = new Adresa(
                        resultSet.getInt("id"),
                        resultSet.getString("tara"),
                        resultSet.getString("oras"),
                        resultSet.getString("strada")
                );
                adrese.add(adresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adrese;
    }

    public static Adresa readById(int id) {
        Adresa adresa = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    adresa = new Adresa(
                            resultSet.getInt("id"),
                            resultSet.getString("tara"),
                            resultSet.getString("oras"),
                            resultSet.getString("strada")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adresa;
    }

    // Metoda pentru a actualiza o adresă în baza de date
    public void update(Adresa adresa) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, adresa.getTara());
            statement.setString(2, adresa.getOras());
            statement.setString(3, adresa.getStrada());
            statement.setInt(4, adresa.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metoda pentru a șterge o adresă din baza de date
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
