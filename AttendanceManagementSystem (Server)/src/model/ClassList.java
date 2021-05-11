package model;

import java.util.ArrayList;

public class ClassList {
    private ArrayList<Class> classes;

    public ClassList() {
        this.classes = new ArrayList<>();
    }

    public void addClass(Class aClass) {
        classes.add(aClass);
    }

    public void removeClass(Class aClass) {
        classes.remove(aClass);
    }

    public void removeClass(String className) throws IllegalAccessException {
        for (Class aClass : classes) {
            if (aClass.getClassName().equals(className)) {
                if (aClass.getStudents().getAllStudents().isEmpty()) {
                    this.classes.remove(aClass);
                    return;
                }
                throw new IllegalAccessException("Can not delete a class with students.");
            }
        }
    }


    public ArrayList<Class> getAllClasses() {
        return classes;
    }

    //TODO Student can be just in 1 class
    public Class getClassWith(Student student) throws IllegalArgumentException {
        for (Class aClass : classes) {
            for (Student aStudent : aClass.getStudents().getAllStudents()) {
                if (aStudent.equals(student)) {
                    return aClass;
                }
            }
        }
        throw new IllegalArgumentException("This student is part of no classes");
    }

    public Class getClassByName(String name) throws IllegalArgumentException {
        for (Class aClass : classes) {
            if (aClass.getClassName().equals(name)) {
                return aClass;
            }
        }
        throw new IllegalArgumentException("No such class with the name: " + name);
    }
}
