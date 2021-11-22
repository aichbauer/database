package dao;

import model.User;
import services.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements Dao<User> {
    private final Database database;

    public UserDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public void create(User user) {
        PreparedStatement createUserStmt;

        String query = "INSERT INTO users (user_name, first_name, last_name, email, password) VALUES (?, ?, ?, ?, ?);";

        try {
            createUserStmt = this.database.getConnection().prepareStatement(query);

            createUserStmt.setString(1, user.getUserName());
            createUserStmt.setString(2, user.getFirstName());
            createUserStmt.setString(3, user.getLastName());
            createUserStmt.setString(4, user.getEmail());
            createUserStmt.setString(5, user.getPassword());

            createUserStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> read() {
        ArrayList<User> allUsers = new ArrayList();
        Statement readAllUsers;

        String query = "SELECT id, user_name, first_name, last_name, email, password FROM users;";

        try {
            readAllUsers = this.database.getConnection().createStatement();
            ResultSet resultSet = readAllUsers.executeQuery(query);

            while (resultSet.next()) {
                User user = new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
                );
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    @Override
    public User read(Integer id) {
        User user = null;
        PreparedStatement readUserStmt;

        String query = "SELECT id, user_name, first_name, last_name, email, password  FROM users WHERE id = ?;";

        try {
            readUserStmt = this.database.getConnection().prepareStatement(query);

            readUserStmt.setInt(1, id);

            ResultSet resultSet = readUserStmt.executeQuery();

            while (resultSet.next()) {
                user = new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void update(User user) {
        PreparedStatement updateUserStmt;

        String query = "UPDATE users SET first_name = ?, last_Name = ? WHERE id = ?;";

        try {
            updateUserStmt = this.database.getConnection().prepareStatement(query);

            updateUserStmt.setLong(3, user.getId());
            updateUserStmt.setString(1, user.getFirstName());
            updateUserStmt.setString(2, user.getLastName());

            updateUserStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> readAll(Integer id) {
        User user = read().get(id);
        List<User> users = new ArrayList<>();
        users.add(user);

        return users;
    }

    @Override
    public void delete(User user) {
        PreparedStatement deleteUserStmt;

        String query = "DELETE FROM users WHERE id = ?;";

        try {
            deleteUserStmt = this.database.getConnection().prepareStatement(query);

            deleteUserStmt.setLong(1, user.getId());

            deleteUserStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
