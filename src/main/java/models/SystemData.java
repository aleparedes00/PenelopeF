package models;

import test.TestData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

public class SystemData {
    /* Main Data Lists */
    public Map<UUID, Project> uuidProjectMap;
    public Map<UUID, User> uuidUserMap;

    public ArrayList<User> users;
    private ArrayList<Group> groups;
    private ArrayList<Message> messages;
    private ArrayList<Project> projects;

    private UserSystem userSystem; // TODO: Might not be needed anymore. Revision needed, until then I'm changing the constructor so the code doesn't break.
    // private ProjectSystem? ProjectController?

    public SystemData() {
        //Create an empty map that we'll fill in in repository.
        this.uuidProjectMap = new HashMap<>();
        this.uuidUserMap = new HashMap<>();
        /*// Loading/Creating main data lists
        this.messages = loadMessages();
        this.projects = loadProjects();
*/
        this.users = loadUsers();
        this.groups = loadGroups();
        // Instanciating Systems....? see to-do above
        this.userSystem = new UserSystem(users, groups);
    }

    /* Loading Methods */
    // If JSON save exists, load it. Else, create a new empty list.


    //**AP**This method will be transfer to a service that wil be called by the repository (when we deserialize the json, we'll fill in)
    public void loadProjectsInMap(Project project){
        uuidProjectMap.put(project.getId(), project);
    }

    public Boolean getProjectFromMap(UUID id) {
        return uuidProjectMap.containsKey(id);
    }

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
        return users.get(users.stream().map(User::getId).collect(toList()).indexOf(id));
        //ça marche pas! return users.stream().filter(user -> user.getId().equals(id)).findFirst();
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
