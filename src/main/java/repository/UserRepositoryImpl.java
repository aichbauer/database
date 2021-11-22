package repository;

import dao.UserDaoImpl;
import dao.TweetDaoImpl;
import model.Tweet;
import model.User;

import java.util.List;

public class UserRepositoryImpl implements Repository<User, Tweet> {
    UserDaoImpl userDaoImpl;
    TweetDaoImpl tweetDaoImpl;

    public UserRepositoryImpl(UserDaoImpl userDaoImpl, TweetDaoImpl tweetDaoImpl) {
        this.userDaoImpl = userDaoImpl;
        this.tweetDaoImpl = tweetDaoImpl;
    }

    @Override
    public User get(Integer id) {
        User user = userDaoImpl.read(id);
        List<Tweet> tweets = tweetDaoImpl.readAll(user.getId());

        user.setTweets(tweets);

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = userDaoImpl.read();

        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            List<Tweet> tweets = tweetDaoImpl.readAll(user.getId());
            user.setTweets(tweets);
        }

        return allUsers;
    }

    @Override
    public void add(User user) {
        userDaoImpl.create(user);
    }

    @Override
    public void update(User user) {
        userDaoImpl.update(user);
    }

    @Override
    public void remove(User user) {
        userDaoImpl.delete(user);
    }

    @Override
    public void addTweet(User user, String message) {
        Tweet tweet = new Tweet(message, user);
        tweetDaoImpl.create(tweet);
    }

    @Override
    public List<Tweet> getAllTweets() {
        List<Tweet> tweets = tweetDaoImpl.read();

        return tweets;
    }

    @Override
    public void removeTweet(Tweet tweet) {
        tweetDaoImpl.delete(tweet);
    }
}
