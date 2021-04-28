package model;

public class Account {
    private UserName username;
    private Password password;

    public Account(String username, String password) throws IllegalArgumentException {
        this.username = new UserName(username);
        this.password = new Password(password);
    }

    public UserName getUsername() {
        return username;
    }

    public void setUsername(UserName username) {
        this.username = username;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

}
