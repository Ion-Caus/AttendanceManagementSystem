package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * This is containing class for class
 */
public class ClassList {
    private ArrayList<StudentClass> classes;

    /**
     * 0 argument constructor that initializes new arraylist to contain classes
     */
    public ClassList() {
        this.classes = new ArrayList<>();
    }



    /** 1 argument constructor that will initialize the classes arraylist from the given parameter
     * @param classes
     */
    public ClassList(ArrayList<StudentClass> classes) {
        this.classes = classes;
    }

    /**
     * This method is responsible for adding class to classes arraylist, before adding it will check for duplicates and will throw an exception in case duplicates are present
     * @param aClass the class that is supposed to be added to the list
     * @throws IllegalArgumentException this exception is going to get thrown in case of duplicate class
     */

    public void addClass(StudentClass aClass) throws IllegalArgumentException {
        if (!isUnique(aClass.getClassName())) {
            throw new IllegalArgumentException("Cannot add a duplicate class.");
        }
        classes.add(aClass);
    }

    /**
     * This method is responsible for deleting a class from the list of classes based on the class name, it will also check if the class does not have any students, in case it does an exception is going to be thrown
     * @param className the name of the class that is supposed to be deleted
     * @throws IllegalAccessException this exception is going to be thrown in case a class has students associated to it
     */

    public void removeClass(String className) throws IllegalAccessException {
        for (StudentClass aClass : classes) {
            if (aClass.getClassName().equals(className)) {
                if (aClass.getStudents().getAllStudents().isEmpty()) {
                    this.classes.remove(aClass);
                    return;
                }
                throw new IllegalAccessException("Cannot delete a class with students.");
            }
        }
    }

    /**
     * @return will return the class list
     */
    public ArrayList<StudentClass> getAllClasses() {
        return classes;
    }


    private StudentClass getClassWith(Predicate<StudentClass> selector)
    {
        return classes.stream().filter(selector).findFirst().get();
    }

    /**
     * @param searchStudent the student object for which should be the associated class returned
     * @return this method will return the class that specific students is part of
     */
    public StudentClass getClassWith(Student searchStudent) {
        return getClassWith(aClass ->
                aClass.getStudents().getAllStudents()
                        .stream().anyMatch(student -> Objects.equals(student.getClassName(), searchStudent.getClassName())));
    }

    /**
     * @param name Name of the class
     * @return the class object that matches the name provided as an argument
     */
    public StudentClass getClassByName(String name){
        return getClassWith(studentClass -> studentClass.getClassName().equals(name));
    }

    /**
     * @param className The name of the class
     * @return true in case a class with the name provided as an argument already exists, false in case it does not
     */
    public boolean isUnique(String className) {
        return classes.stream().noneMatch(aClass -> aClass.getClassName().equals(className));
    }
}
