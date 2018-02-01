package models;

import java.util.ArrayList;

public class Group {

    private String name;

    private ArrayList<User> users;

    /* Constructor */
    public Group(String name) {
        this.name = name;
        this.users = new ArrayList<>();
    }

    /* Getters */
    public String getName() {
        return name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    /*Other Methods*///AP I think this method should be in View
    public void printUsersInGroup() {
        System.out.println("Users in group " + this.getName());
        for (User user : this.getUsers()) {
            System.out.println("\t"+user.getUsername());
        }
    }
}
