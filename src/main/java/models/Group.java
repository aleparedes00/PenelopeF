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
    private HashMap<UUID,User> users;
    private ArrayList<UUID> usersIds;

    private UUID id;

    /* Constructor by default */
    public Group() {
    }

    /* Constructor */
    public Group(String name) {
        this.name = name;
        this.users = new HashMap<>();
        this.usersIds = new ArrayList<>();
        this.id = UUID.randomUUID();
    }

    /* Getters */
    public String getName() {
        return name;
    }

    public HashMap<UUID,User> getUsers() {
        return users;
    }
    public ArrayList<UUID> getUsersIds() {
        return usersIds;
    }

    public UUID getId() {
        return id;
    }

    /*Other Methods*///AP I think this method should be in View
    public void printUsersInGroup() {
        System.out.println("Users in group " + this.getName());
        for (Map.Entry<UUID, User> user : this.getUsers().entrySet()) {
            System.out.println("\t"+user.getValue().getUsername());
        }
    }
}
