package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Group {

    static String ADMIN_GROUP = "root";

    private String name;

    @JsonIgnore
    private ArrayList<User> users;
    private ArrayList<UUID> usersIds;

    private boolean selfGroup;

    private UUID id;

    /* Constructor by default */
    public Group() {
    }

    /* Constructor */
    public Group(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.usersIds = new ArrayList<>();
        this.selfGroup = false;
        this.id = UUID.randomUUID();
    }

    public Group(User user) {

        this.name = user.getUsername();

        this.users = new ArrayList<>();
        this.users.add(user);

        this.usersIds = new ArrayList<>();
        this.usersIds.add(user.getId());

        this.selfGroup = true;

        this.id = UUID.randomUUID();
    }

    /* Getters */
    public String getName() {
        return name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<UUID> getUsersIds() {
        return usersIds;
    }

    public boolean isSelfGroup() {
        return selfGroup;
    }

    public UUID getId() {
        return id;
    }

    /*Other Methods*///AP I think this method should be in View
    public void printUsersInGroup() {
        System.out.println("Users in group " + this.getName());
        for(User users : users)
        {
            System.out.println("\t" + users.getUsername());
        }
    }
}
