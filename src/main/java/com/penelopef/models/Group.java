package com.penelopef.models;

import java.util.ArrayList;
import java.util.UUID;

public class Group {

    private String name;

    private ArrayList<UUID> usersIds;

    private boolean selfGroup;

    private UUID id;

    /* Constructor by default */
    public Group() {
    }

    /* Constructor */
    public Group(String name) {
        this.name = name;
        this.usersIds = new ArrayList<>();
        this.selfGroup = false;
        this.id = UUID.randomUUID();
    }

    public Group(User user) {

        this.name = user.getUsername();

        this.usersIds = new ArrayList<>();
        this.usersIds.add(user.getId());

        this.selfGroup = true;

        this.id = UUID.randomUUID();
    }

    /* Getters */
    public String getName() {
        return name;
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

    /* Setters */
    public void setName(String name) {
        this.name = name;
    }
}
