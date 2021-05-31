package model;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * This class represents Teacher user type
 */
public class Teacher implements Serializable {
    private String name;
    private String initials;
    private String ID;

    private Account account;

    /**2 argument constructor that will initialize Teacher with the parameters provided as arguments
     * @param name the name of the Teacher
     * @param ID the id of the Teacher
     * @exception IllegalArgumentException will be thrown in case a parameter is not valid
     */
    public Teacher(String name, String ID) {

        if (name.isBlank()) {
            throw new IllegalArgumentException("Teacher name cannot be empty");
        }

        if (ID.isBlank()) {
            throw new IllegalArgumentException("Teacher ID cannot be empty");
        }

        if(!hasValidID(ID)){
            throw new IllegalArgumentException("Teacher ID must consist of 6 digits.");
        }

        this.name = name;
        this.initials = getInitials(name);
        this.ID = ID;
    }

    /**
     * @param ID id provided as argument
     * @return in case the id is valid, it will return true, in case it is not valid it will return false
     */
    private boolean hasValidID(String ID){
        Pattern pattern = Pattern.compile("^[0-9]{6}$");
        return pattern.matcher(ID).matches();
    }

    // TODO: 31/5/2021 tomas not used method
    public void setAccount(Account account) {
        this.account = account;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getInitials() {
        return initials;
    }

    public String getID() {
        return ID;
    }


    /** This method will derive initials from Teacher's name
     * @param name Teacher's name provided as an argument
     * @return will return Teacher's initials
     */
    private String getInitials(String name) {
        String[] names = name.split(" ");
        String initials = "";
        for (String temp : names)
            initials += temp.charAt(0);
        return initials;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Teacher))
            return false;

        Teacher teacher = (Teacher) obj;
        return Objects.equals(name, teacher.name) &&
                Objects.equals(initials, teacher.initials) &&
                Objects.equals(ID, teacher.ID) &&
                Objects.equals(account, teacher.account);
    }


}
