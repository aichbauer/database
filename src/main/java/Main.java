import dao.TweetDaoImpl;
import dao.UserDaoImpl;
import model.Tweet;
import model.User;
import repository.UserRepositoryImpl;
import services.Database;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database databaseService = new Database();
        UserDaoImpl userDao = new UserDaoImpl(databaseService);
        TweetDaoImpl tweetDao = new TweetDaoImpl(databaseService);
        UserRepositoryImpl userRepository = new UserRepositoryImpl(userDao, tweetDao);

        List<User> allUser = userRepository.getAll();
        List<Tweet> allTweets = userRepository.getAllTweets();

        for (int i = 0; i < allTweets.size(); i++) {
            userRepository.removeTweet(allTweets.get(i));
        }
        for (int i = 0; i < allUser.size(); i++) {
            userRepository.remove(allUser.get(i));
        }

        User maxi99 = new User("maxi99", "Max", "Mustermann", "max@mustermann.at", "password");
        User franzi77 = new User("franzi77", "Franziska", "Musterfrau", "franzi@musterfrau.at", "1234");
        User hugo = new User("hugo", "Jan", "Hugo", "jan@hugo.at", "0000");

        userRepository.add(maxi99);
        userRepository.add(franzi77);
        userRepository.add(hugo);

        List<User> allUser2 = userRepository.getAll();

        System.out.println(allUser2.toString());

        User hugoWithId = userRepository.get(allUser2.get(2).getId());

        System.out.println(hugoWithId.toString());

        hugoWithId.setFirstName("Kevin");
        hugoWithId.setLastName("Maier");
        userRepository.addTweet(hugoWithId, "some message");
        userRepository.addTweet(hugoWithId, "some message two");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        userRepository.update(hugoWithId);

        User newHugo = userRepository.get(allUser2.get(2).getId());

        System.out.println(newHugo.toString());

        userRepository.remove(allUser2.get(0));

        List<User> allUserWithoutMaxi99 = userRepository.getAll();

        System.out.println(allUserWithoutMaxi99.toString());
    }
}
