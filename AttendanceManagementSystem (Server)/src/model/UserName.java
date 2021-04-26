package model;

public class UserName
{
  private String name;

  public UserName(String username)
  {
    if (username == null || username.length() < 3)
      throw new IllegalArgumentException("Username must have at least 3 characters");
    if (username.length() > 20)
      throw new IllegalArgumentException("Username cannot have more than 20 characters");

    this.name = username;
  }

  public String getName()
  {
    return name;
  }

  @Override public String toString()
  {
    return name;
  }
}
