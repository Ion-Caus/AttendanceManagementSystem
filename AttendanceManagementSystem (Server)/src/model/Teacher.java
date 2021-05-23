package model;

import java.util.Objects;

public class Teacher {
    private String name;
    private String initials;
    private String ID;

    private Account account;

    public Teacher(String name, String ID) {

        if (name.isBlank()) {
            throw new IllegalArgumentException("Teacher name cannot be empty");
        }
        if (ID.isBlank()) {
            throw new IllegalArgumentException("Teacher ID cannot be empty");
        }

        this.name = name;
        this.initials = getInitials(name);
        this.ID = ID;
    }

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
