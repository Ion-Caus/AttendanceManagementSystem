package model;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * This class represents student user
 */
public class Student implements Serializable {
    private String name;
    private String ID;

    private Account account;
    private String className;

    /**2 argument constructor that will make sure that all arguments are valid and initialize them
     * @param name the name of the student
     * @param ID the id of the student
     * @exception IllegalArgumentException will be thrown in case an argument is not valid
     */
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

    /** This method will check if the id is valid
     * @param ID the id that will be checked
     * @return in case id is valid, it will return true, in case not it will return false
     */
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

    /**
     * this method will set className to null
     */
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
