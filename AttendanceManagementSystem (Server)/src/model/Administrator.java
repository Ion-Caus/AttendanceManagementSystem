package model;

public class Administrator
{
  private Account account;
  private String name;
  private String ID;

  public Administrator(String name,  String ID) {
    this.name = name;
    this.ID = ID;
    this.account = null;
  }

  public void setAccount(Account account){
    this.account = account;
  }

}
