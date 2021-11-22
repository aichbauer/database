package dao;

import model.Tweet;
import services.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TweetDaoImpl implements Dao<Tweet> {
    private final Database database;

    public TweetDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public void create(Tweet tweet) {
        PreparedStatement createUserStmt;

        String query = "INSERT INTO tweets (message, user_id) VALUES (?, ?);";

        try {
            createUserStmt = this.database.getConnection().prepareStatement(query);

            createUserStmt.setString(1, tweet.getMessage());
            createUserStmt.setInt(2, tweet.getUserId());

            createUserStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tweet> read() {
        ArrayList<Tweet> allTweets = new ArrayList();
        Statement readAllTweets;

        String query = "SELECT id, message, user_id FROM tweets;";

        try {
            readAllTweets = this.database.getConnection().createStatement();
            ResultSet resultSet = readAllTweets.executeQuery(query);

            while (resultSet.next()) {
                Tweet tweet = new Tweet(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
                );
                allTweets.add(tweet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allTweets;
    }

    @Override
    public Tweet read(Integer id) {
        Tweet tweet = null;
        PreparedStatement readUserStmt;

        String query = "SELECT id, message, user_id FROM tweets WHERE id = ?;";

        try {
            readUserStmt = this.database.getConnection().prepareStatement(query);

            readUserStmt.setInt(1, id);

            ResultSet resultSet = readUserStmt.executeQuery();

            while (resultSet.next()) {
                tweet = new Tweet(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tweet;
    }


    @Override
    public List<Tweet> readAll(Integer userId) {
        ArrayList<Tweet> allTweets = new ArrayList();
        PreparedStatement readUserStmt;

        String query = "SELECT id, message, user_id FROM tweets WHERE user_id = ?;";

        try {
            readUserStmt = this.database.getConnection().prepareStatement(query);
            readUserStmt.setInt(1, userId);

            ResultSet resultSet = readUserStmt.executeQuery();

            while (resultSet.next()) {
                Tweet tweet = new Tweet(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
                );
                allTweets.add(tweet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allTweets;
    }

    @Override
    public void update(Tweet tweet) {

    }

    @Override
    public void delete(Tweet tweet) {
        PreparedStatement deleteUserStmt;

        String query = "DELETE FROM tweets WHERE id = ?;";

        try {
            deleteUserStmt = this.database.getConnection().prepareStatement(query);

            deleteUserStmt.setLong(1, tweet.getId());

            deleteUserStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
