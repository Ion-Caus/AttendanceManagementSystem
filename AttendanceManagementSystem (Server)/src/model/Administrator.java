package model;

/**
 * A class representing the administrator
 */
public class Administrator {
    private Account account;
    private String name;
    private String ID;


    /**
     * A 2 argument constructor initializing the administrator class
     * @param name name of the administrator
     * @param ID id of the administrator
     */

    public Administrator(String name, String ID) {
        this.name = name;
        this.ID = ID;
        this.account = null;
    }


    public void setAccount(Account account) {
        this.account = account;
    }

}
