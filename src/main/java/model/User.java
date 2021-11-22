package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class User {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    private String password;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private List<Tweet> tweets;

    public User(String userName, String firstName, String lastName, String email, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setPassword(password);
    }

    public User(Integer id, String userName, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setPassword(password);
    }

    public void setPassword(String password) {
        // here hash the password
        this.password = hashPassword(password);
    }

    private String hashPassword(String password) {
        // hashfunction
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", tweets='" + tweets + '\'' +
                '}';
    }
}
