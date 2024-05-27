package database.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDataUsingStatement {

    public void createTable() {
        String createTableSql = """
            CREATE TABLE IF NOT EXISTS person (
                id int PRIMARY KEY AUTO_INCREMENT,
                name varchar(40),
                age double
            )
            """;

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPerson(String name, double age) {
        String insertPersonSql = "INSERT INTO person (name, age) VALUES (?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllPersons() {
        String selectSql = "SELECT * FROM person";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while(resultSet.next()) {
                System.out.println("Id: " + resultSet.getInt(1));
                System.out.println("Name: " + resultSet.getString(2));
                System.out.println("Age: " + resultSet.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
