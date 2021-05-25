package model;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

public class Student implements Serializable {
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

        if(!hasValidID(ID)){
            throw new IllegalArgumentException("Student ID must consist of 6 digits.");
        }

        this.name = name;
        this.ID = ID;
        this.account = null;
    }

    private boolean hasValidID(String ID){
        Pattern pattern = Pattern.compile("^[0-9]{6}$");
        return pattern.matcher(ID).matches();
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
