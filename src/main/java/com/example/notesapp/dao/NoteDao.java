package com.example.notesapp.dao;

import com.example.notesapp.bean.Note;
import com.example.notesapp.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoteDao {

    private static final Logger LOGGER = Logger.getLogger(NoteDao.class.getName());

    private static NoteDao instance;

    private static final String ADD_NOTE = "INSERT INTO notes_app.note (username, text) VALUES (?, ?)";
    private static final String GET_USER_NOTES_BY_USERNAME = "SELECT * FROM notes_app.note WHERE username = ?";
    private static final String DELETE_NOTE_BY_NOTE_ID = "DELETE FROM notes_app.note WHERE id = ?";

    private static final String NOTE_ID_COLUMN_NAME = "id";
    private static final String NOTE_TEXT_COLUMN_NAME = "text";

    public static NoteDao getInstance() {
        if (instance == null) {
            synchronized (NoteDao.class) {
                if (instance == null) {
                    instance = new NoteDao();
                }
            }
        }
        return instance;
    }

    public boolean createNote(final Note note) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NOTE)
        ) {
            preparedStatement.setString(1, note.getUsername());
            preparedStatement.setString(2, note.getNoteText());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Getting connection from database has failed");
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "Connection is null");
        }

        return false;
    }

    public List<Note> getUserNotes(final String username) {
        List<Note> userNotes = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_NOTES_BY_USERNAME)
        ) {
            preparedStatement.setString(1, username);
            addUserNotesToList(userNotes, preparedStatement, username);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Getting connection from database has failed");
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "Connection is null");
        }

        return userNotes;
    }

    public boolean deleteNote(final long noteId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NOTE_BY_NOTE_ID)
        ) {
            preparedStatement.setLong(1, noteId);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Getting connection from database has failed");
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "Connection is null");
        }

        return false;
    }

    private void addUserNotesToList(final List<Note> userNotes, final PreparedStatement preparedStatement, final String username) {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Note noteToAdd = new Note();

                noteToAdd.setNoteId(resultSet.getLong(NOTE_ID_COLUMN_NAME));
                noteToAdd.setUsername(username);
                noteToAdd.setNoteText(resultSet.getString(NOTE_TEXT_COLUMN_NAME));

                userNotes.add(noteToAdd);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Fail to execute query");
        }
    }
}
