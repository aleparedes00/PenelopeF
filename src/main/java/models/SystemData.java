package models;

import repository.*;
import test.TestData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SystemData {
    /* Main Data Maps */
    private HashMap<UUID, User> users;
    private HashMap<UUID, Group> groups;
    private HashMap<UUID, Message> messages;
    private HashMap<UUID, Project> projects;

    /* Repositories */
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private MessageRepository messageRepository;
    private ProjectRepository projectRepository;

    /* Constants */
    private final static String usersJson = "users.json";
    private final static String groupsJson = "groups.json";
    private final static String messagesJson = "messages.json";
    private final static String projectsFolder = "Project"; // TODO: Should be named "projects", leaving it as "Project" for now so it doesn't conflict with existing files for testing

    private UserSystem userSystem; // TODO: Might not be needed anymore. Revision needed, until then I'm changing the constructor so the code doesn't break.
    // private ProjectSystem? ProjectController?

    public SystemData() {
        // Initializing Empty Maps
        this.users = new HashMap<>();
        this.groups = new HashMap<>();
        this.messages = new HashMap<>();
        this.projects = new HashMap<>();

        // Initializing Repositories
        // TODO: create repos for groups and messages (?)
        this.userRepository = new UserRepository(usersJson);
        this.groupRepository = new GroupRepository(groupsJson);
        this.messageRepository = new MessageRepository(messagesJson);
        this.projectRepository = new ProjectRepository(projectsFolder, this);

        // Loading Data From Repositories
        loadSystemData();

        // Instanciating Systems....? see above
        this.userSystem = new UserSystem(users, groups);
    }

    public void loadSystemData() {  // TODO: fill everything here
        this.users = userRepository.loadUsers();
        if (!this.users.isEmpty()) System.out.println("Loaded user data.");
        this.groups = groupRepository.loadGroups();
        if (!this.groups.isEmpty()) System.out.println("Loaded group data.");
        this.messages = messageRepository.loadMessages();
        if (!this.messages.isEmpty()) System.out.println("Loaded message data.");
        projectRepository.loadProjects();
    }

    /* Loading Methods */
    //**AP**This method will be transfer to a service that wil be called by the repository (when we deserialize the json, we'll fill in)
    public void loadUserInMap(User user){
        users.put(user.getId(), user);
    }

    public void loadGroupInMap(Group group){
        groups.put(group.getId(), group);
    }

    public void loadMessageInMap(Message message){
        messages.put(message.getId(), message);
    }

    public void loadProjectInMap(Project project){
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




    /* OLD Loading Methods */
    /* Based on the previous comment: these methods will have equivalents in Repository classes, so they won't be needed here anymore
     * example: loadProjects() defined below can already be replaced by readProjects/loadProjects in ProjectRepository
     * The general idea is to have reading/loading methods in the Repo classes, then send the objects to be loaded into the maps here
     */
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


}
