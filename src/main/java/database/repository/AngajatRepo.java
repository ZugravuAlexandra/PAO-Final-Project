package database.repository;

import database.config.DatabaseConfiguration;
import database.entitati.Angajat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AngajatRepo {
    private Connection connection;

    private static final String INSERT_QUERY = "INSERT INTO angajati (id, nume, prenume, email, telefon, data_angajare, pozitie) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM angajati";
    private static final String UPDATE_QUERY = "UPDATE angajati SET nume=?, prenume=?, email=?, telefon=?, data_angajare=?, pozitie=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM angajati WHERE id=?";

    public AngajatRepo() {
        connection = DatabaseConfiguration.getDatabaseConnection();
    }

    public void create(Angajat angajat) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setInt(1, angajat.getId());
            statement.setString(2, angajat.getNume());
            statement.setString(3, angajat.getPrenume());
            statement.setString(4, angajat.getEmail());
            statement.setString(5, angajat.getTelefon());
            statement.setString(6, angajat.getDataAngajare());
            statement.setString(7, angajat.getPozitie());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Angajat> readAll() {
        List<Angajat> angajati = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Angajat angajat = new Angajat(
                        resultSet.getInt("id"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("email"),
                        resultSet.getString("telefon"),
                        resultSet.getString("data_angajare"),
                        resultSet.getString("pozitie")
                );
                angajati.add(angajat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return angajati;
    }

    // metoda getById
    public Angajat getById(int id) {
        String query = "SELECT * FROM angajati WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Angajat(
                            resultSet.getInt("id"),
                            resultSet.getString("nume"),
                            resultSet.getString("prenume"),
                            resultSet.getString("email"),
                            resultSet.getString("telefon"),
                            resultSet.getString("data_angajare"),
                            resultSet.getString("pozitie")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Angajat angajat) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, angajat.getNume());
            statement.setString(2, angajat.getPrenume());
            statement.setString(3, angajat.getEmail());
            statement.setString(4, angajat.getTelefon());
            statement.setString(5, angajat.getDataAngajare());
            statement.setString(6, angajat.getPozitie());
            statement.setInt(7, angajat.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(int id) {
        boolean success = false;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

}
