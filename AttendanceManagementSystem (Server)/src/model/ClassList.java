package model;

import java.util.ArrayList;

public class ClassList
{
  private ArrayList<Class> classes;

  public ClassList(){
    this.classes = new ArrayList<>();
  }

  public void addClass(Class temp){
    classes.add(temp);
  }

  public void removeClass(Class temp){
    classes.remove(temp);
  }

  public ArrayList<Class> getAllClasses() {
    return classes;
  }
}
