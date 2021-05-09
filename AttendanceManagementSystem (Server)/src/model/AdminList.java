package model;

import java.util.ArrayList;

public class AdminList {

    private ArrayList<Administrator> admins;

    public AdminList() {
        this.admins = new ArrayList<>();
    }

    public void addAdmin(Administrator adminstrator) {
        admins.add(adminstrator);
    }

    public void removeAdmin(Administrator adminstrator) {
        admins.remove(adminstrator);
    }

    public ArrayList<Administrator> getAllAdmins() {
        return admins;
    }
}
