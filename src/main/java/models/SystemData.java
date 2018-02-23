package models;

import repository.*;
import test.TestData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class SystemData {
    /* Main Data Maps */
    private HashMap<UUID, User> users;
    private HashMap<UUID, Group> groups;
    private HashMap<UUID, Message> messages;
    private HashMap<UUID, Project> projects;

    private UserSystem userSystem; // TODO: Might not be needed anymore. Revision needed, until then I'm changing the constructor so the code doesn't break.

    public SystemData() {
        // Initializing Empty Maps
        this.users = new HashMap<>();
        this.groups = new HashMap<>();
        this.messages = new HashMap<>();
        this.projects = new HashMap<>();
    }

    public void initializeUserSystem() {
        this.userSystem = new UserSystem(users, groups);

    }

    /* Loading Methods */
    //**AP**This method will be transfer to a service that wil be called by the repository (when we deserialize the json, we'll fill in)
    public void loadProjectInMap(Project project) {
        projects.put(project.getId(), project);
    }

    /* Exists Method */
    public Boolean existsProject(UUID id) { // renamed from "getProjectFromMap"
        return projects.containsKey(id);
    }

    /* Getters From ID */
    public User getUserFromId(UUID id) {
        return users.get(id);
    }

    public Group getGroupFromId(UUID id) {
        return groups.get(id);
    }

    public Project getProjectFromId(UUID id) {
        return projects.get(id);
    }

    public Message getMessageFromId(UUID id) {
        return messages.get(id);
    }

    /* Other Getters */
    public HashMap<UUID, User> getUsers() {
        return users;
    }

    public HashMap<UUID, Group> getGroups() {
        return groups;
    }

    public HashMap<UUID, Message> getMessages() {
        return messages;
    }

    public HashMap<UUID, Project> getProjects() {
        return projects;
    }

    public UserSystem getUserSystem() {
        return userSystem;
    }

    public <T> void load(HashMap<UUID, T> deserializedHashMap, Class<T> dataType) {
        if (dataType == User.class) this.users = (HashMap<UUID, User>) deserializedHashMap;
        if (dataType == Group.class) this.groups = (HashMap<UUID, Group>) deserializedHashMap;
        if (dataType == Message.class) this.messages = (HashMap<UUID, Message>) deserializedHashMap;
        if (dataType == Project.class) this.projects = (HashMap<UUID, Project>) deserializedHashMap;
    }

    public <T> HashMap getDataFromType(Class<T> dataType) {
        if (dataType == User.class) return this.users;
        if (dataType == Group.class) return this.groups;
        if (dataType == Message.class) return this.messages;
        return this.projects;
    }
}
