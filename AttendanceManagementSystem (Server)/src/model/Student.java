package model;

import java.util.Objects;

public class Student
{
    private String name;
    private String ID;

    private Account account;

    public Student(String name, String ID) {
        System.out.println("the student constructor is called");
        System.out.println(name);
        if(name.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be empty");}
        this.name = name;
        this.ID = ID;
        this.account = null;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student))
            return false;

        Student student = (Student) obj;
        return Objects.equals(name, student.name) &&
                Objects.equals(ID, student.ID) &&
                Objects.equals(account, student.account);
    }
}
