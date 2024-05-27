package database.repository;

import database.config.DatabaseConfiguration;
import database.domain.Person;

import java.sql.*;
import java.util.Optional;

public class PersonRepository {

    //PreparedStatements
    public void insert(String name, double age) {
        String insertPersonSql = "INSERT INTO person (name, age) VALUES (?, ?)";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Person person) {
        String insertPersonSql = "INSERT INTO person (id, name, age) VALUES (null, ?, ?)";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setDouble(2, person.getAge());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Person> getById(int id) {
        String selectSql = "SELECT * FROM person p WHERE p.id = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToPerson(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private Optional<Person> mapToPerson(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double age = resultSet.getDouble("age");
            return Optional.of(new Person(id, name, age));
        }
        return Optional.empty();
    }

    public void updateName(String name, int id) {
        String updatePersonSql = "UPDATE person SET name = ? WHERE id = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(updatePersonSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //CallableStatements
    public void insertPersonProcedure(Person person) {
        String sql = "{call insertPerson(?,?,?)}";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            CallableStatement callableStatement = conn.prepareCall(sql);
            callableStatement.setString(1, person.getName());
            callableStatement.setDouble(2, person.getAge());

            callableStatement.registerOutParameter(3, Types.INTEGER); // Parametrul de ieșire este indexat de la 1
            callableStatement.execute();
            System.out.println("user was added with id: " + callableStatement.getInt(3)); // Se utilizează indexul corect pentru a obține valoarea parametrului de ieșire
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
