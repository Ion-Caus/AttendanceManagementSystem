package model;

import java.util.Objects;

public class Teacher {
    private String name;
    private String initials;
    private String ID;

    private Account account;

    public Teacher(String name, String initials, String ID) {
        this.name = name;
        this.initials = initials;
        this.ID = ID;
    }

    public void setAccount(Account account) {
        this.account = account;
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
