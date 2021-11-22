package model;

import lombok.Getter;
import lombok.Setter;


public class Tweet {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private Integer userId;
    @Getter
    @Setter
    private String message;

    public Tweet(String message, User user) {
        this.message = message;
        this.userId = user.getId();
    }

    public Tweet(Integer id, String message, User user) {
        this.id = id;
        this.message = message;
        this.userId = user.getId();
    }

    public Tweet(Integer id, String message, Integer userId) {
        this.id = id;
        this.message = message;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Tweets{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
