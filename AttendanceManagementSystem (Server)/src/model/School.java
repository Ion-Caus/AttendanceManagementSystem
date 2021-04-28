package model;

import java.util.ArrayList;

public class School
{
  private String name;
  private ClassList classList;

  public School(String name) {
    this.name = name;
    this.classList = new ClassList();
  }

  public ClassList getClassList() {
    return classList;
  }

  public String getName() {
    return name;
  }
}
