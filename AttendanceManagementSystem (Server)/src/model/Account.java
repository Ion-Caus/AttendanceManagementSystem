package model;

public class Account
{
  private UserName username;
  private Password password;
  private final String accessType;

  public Account(String username, String password, String accessType) throws IllegalArgumentException {
    this.username = new UserName(username);
    this.password = new Password(password);
    this.accessType = accessType;
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
