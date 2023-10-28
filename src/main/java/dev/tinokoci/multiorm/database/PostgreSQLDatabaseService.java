package dev.tinokoci.multiorm.database;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Properties;

@Log4j2
public class PostgreSQLDatabaseService implements DatabaseService {

    private Connection connection;
    private UriBuilder uriBuilder;

    @Override
    public PostgreSQLDatabaseService connect(UriBuilder uriBuilder) {
        this.uriBuilder = uriBuilder;
        getConnection();
        return this;
    }

    @Override
    public <T> T save(T entity) {

        return entity;
    }

    private void prepareSelectStatement() {
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM users WHERE name = ?");
            statement.setString(0, "valentino");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("found");
            } else {
                System.out.println("doesn't exist");
            }
            statement.close();
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void simpleSelectStatement() {
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            int index = 0;
            while (resultSet.next()) {
                resultSet.getString(index++);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        try {
            Properties info = new Properties();

            if (uriBuilder.getUser() != null) {
                info.put("user", uriBuilder.getUser());
            }
            if (uriBuilder.getPassword() != null) {
                info.put("password", uriBuilder.getUser());
            }
            int port = Optional.ofNullable(uriBuilder.getPort()).orElse(5432);
            String database = Optional.ofNullable(uriBuilder.getDatabase()).orElse("postgres");
            String uri = "jdbc:postgresql://" + uriBuilder.getHost() + ":" + port + "/" + database;
            connection = DriverManager.getConnection(uri, info);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
