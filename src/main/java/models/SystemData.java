package models;

import test.TestData;

import java.util.ArrayList;
import java.util.UUID;

public class SystemData {
    /* Main Data Lists */
    public ArrayList<User> users;
    private ArrayList<Group> groups;
    private ArrayList<Message> messages;
    private ArrayList<Project> projects;

    private UserSystem userSystem; // TODO: Might not be needed anymore. Revision needed, until then I'm changing the constructor so the code doesn't break.
    // private ProjectSystem? ProjectController?

    public SystemData() {
        // Loading/Creating main data lists
        this.users = loadUsers();
        this.groups = loadGroups();
        this.messages = loadMessages();
        this.projects = loadProjects();

        // Instanciating Systems....? see to-do above
        this.userSystem = new UserSystem(users, groups);
    }

    /* Loading Methods */
    // If JSON save exists, load it. Else, create a new empty list.

    private ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        // temporary: load data from TestData.java
        users.add(TestData.project1_2());
        return users;
    }

    private ArrayList<Group> loadGroups() {
        ArrayList<Group> groups = new ArrayList<>();
        return groups;
    }

    private ArrayList<Message> loadMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        return messages;
    }

    private ArrayList<Project> loadProjects() {
        ArrayList<Project> projects = new ArrayList<>();
        return projects;
    }

    /* Getters From ID */
    public User getUserFromId(UUID id) {
        return users.get(users.indexOf(id));
    }

    public Group getGroupFromId(UUID id) {
        return groups.get(groups.indexOf(id));
    }

    public Project getProjectFromId(UUID id) {
        return projects.get(projects.indexOf(id));
    }
    public Message getMessageFromId(UUID id) {
        return messages.get(messages.indexOf(id));
    }

    /* Other Getters */
    public UserSystem getUserSystem() {
        return userSystem;
    }
}
