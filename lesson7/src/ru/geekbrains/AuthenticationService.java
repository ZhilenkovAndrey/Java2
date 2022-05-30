package ru.geekbrains;


import java.util.Optional;
import java.util.Set;

public class AuthenticationService {

    private static final Set<User> users = Set.of(
            new User("user1", "l1", "p1"),
            new User("user2", "l2", "p2"),
            new User("user3", "l3", "p3")
    );

    public Optional<String> findUsernameByLoginAndPassword(String login, String password) {

        return users.stream()
                .filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password))
                .findFirst()
                .map(User::getUserName);
    }

    private static class User {
        private String userName;
        private String login;
        private String password;

        public User(String username, String login, String password) {
            this.userName = username;
            this.login = login;
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }
}