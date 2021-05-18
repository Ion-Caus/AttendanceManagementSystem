package model;

import dao.ClassesDAOImpl;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassList {
    private ArrayList<Class> classes;

    public ClassList() {
        this.classes = new ArrayList<>();
    }

    public void addClass(Class aClass) throws IllegalArgumentException {
        if (!isUnique(aClass.getClassName())) {
            throw new IllegalArgumentException("Can not add a duplicate class.");
        }
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


    public ArrayList<Class> getAllClasses() throws SQLException
    {
        //return classes;
        return (ArrayList<Class>) ClassesDAOImpl.getInstance().readClasses();
    }

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

    public boolean isUnique(String className) {
        return classes.stream().noneMatch(aClass -> aClass.getClassName().equals(className));
    }

}
