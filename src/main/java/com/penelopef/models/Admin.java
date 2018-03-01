package com.penelopef.models;

import java.util.HashMap;
import java.util.UUID;

public class Admin {
    private SystemData systemData;
    private HashMap<UUID, User> users;
    private HashMap<UUID, Group> groups;

    public Admin(SystemData systemData) {
        this.systemData = systemData;
        this.users = systemData.getUsers();
        this.groups = systemData.getGroups();
    }

    /* Getters */
    public HashMap<UUID,User> getUsers() {
        return users;
    }

    public HashMap<UUID, Group> getGroups() {
        return groups;
    }

    public SystemData getSystemData() {
        return systemData;
    }
}
