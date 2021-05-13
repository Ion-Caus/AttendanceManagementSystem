package model;

import java.util.Objects;

public class Student {
    private String name;
    private String ID;

    private Account account;

    //TODO 13/5 by Ion add the className
    private String className;

    public Student(String name, String ID) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        if (ID.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be empty");
        }
        this.name = name;
        this.ID = ID;
        this.account = null;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    //TODO 14/05 by Ion find a method where the student is assigned in the class and set it
    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student))
            return false;

        Student student = (Student) obj;
        return Objects.equals(name, student.name) &&
                Objects.equals(ID, student.ID) &&
                Objects.equals(className, student.className) &&
                Objects.equals(account, student.account);
    }
}
