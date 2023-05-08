package com.example.notesapp.dao;

import com.example.notesapp.bean.User;
import com.example.notesapp.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class.getName());

    private static UserDao instance;

    private static final String GET_USER_BY_USERNAME = "SELECT * FROM notes_app.user WHERE username = ?";
    private static final String ADD_USER = "INSERT INTO notes_app.user(username, password, first_name, last_name) VALUES (?, ?, ?, ?)";

    private static final String USER_FIRST_NAME_COLUMN_NAME = "first_name";
    private static final String USER_LAST_NAME_COLUMN_NAME = "last_name";
    private static final String USER_USERNAME_COLUMN_NAME = "username";
    private static final String USER_PASSWORD_COLUMN_NAME = "password";

    public static UserDao getInstance() {
        if (instance == null) {
            synchronized (UserDao.class) {
                if (instance == null) {
                    instance = new UserDao();
                }
            }
        }
        return instance;
    }

    public User getUser(final String username) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_USERNAME)
        ) {
            preparedStatement.setString(1, username);
            return retrieveUser(preparedStatement);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Getting connection from database has failed");
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "Connection is null");
        }

        return null;
    }

    public boolean createUser(final User user) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)
        ) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Getting connection from database has failed");
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "Connection is null");
        }

        return false;
    }

    private User retrieveUser(final PreparedStatement preparedStatement) {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                User user = new User();

                user.setFirstName(resultSet.getString(USER_FIRST_NAME_COLUMN_NAME));
                user.setLastName(resultSet.getString(USER_LAST_NAME_COLUMN_NAME));
                user.setUsername(resultSet.getString(USER_USERNAME_COLUMN_NAME));
                user.setPassword(resultSet.getString(USER_PASSWORD_COLUMN_NAME));

                return user;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Fail to execute query");
        }

        return null;
    }
}
