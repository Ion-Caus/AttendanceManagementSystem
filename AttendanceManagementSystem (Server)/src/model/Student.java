package model;

import java.util.Objects;

public class Student {
    private String name;
    private String ID;

    private Account account;
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
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void clearClassName() {
        this.className = null;
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
