package com.penelopef.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.*;

import static java.lang.Math.min;
import static com.penelopef.models.SystemData.ADMIN_GROUP;
import static com.penelopef.tools.PasswordTools.generatePassword;

public class User {

    private String username;

    private String firstName;
    private String lastName;

    private String password;

    private Contact contactInfo;

    @JsonIgnore
    private ArrayList<Project> projects;
    private ArrayList<UUID> projectsIds;

    @JsonIgnore
    private Group selfGroup;
    private UUID selfGroupId;

    @JsonIgnore
    private ArrayList<Group> groups;
    private ArrayList<UUID> groupsIds;

    private UUID id;

    /* Constructor by default */
    public User() {
    }

    /* Constructor */
    public User(String firstName, String lastName, String... additional) {
        this.id = UUID.randomUUID();

        this.firstName = firstName;
        this.lastName = lastName;
        this.groups = new ArrayList<>();
        this.groupsIds = new ArrayList<>();

        this.username = (additional.length >= 1) ? additional[0] :
                lastName.toLowerCase().substring(0, min(6, lastName.length())) + "_" + firstName.toLowerCase().charAt(0);

        this.password = (additional.length >= 2) ? additional[1] : generatePassword();

        this.contactInfo = new Contact();

        Group selfGroup = new Group(this);
        this.selfGroup = selfGroup;
        this.selfGroupId = selfGroup.getId();

        this.projects = new ArrayList<>();
        this.projectsIds = new ArrayList<>();
    }

    /* Getters */
    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonIgnore
    public String getName() {
        return firstName + " " + lastName;
    }

    public String getPassword() {
        return password;
    }

    public Contact getContactInfo() {
        return contactInfo;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public ArrayList<UUID> getProjectsIds() {
        return projectsIds;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public ArrayList<UUID> getGroupsIds() {
        return groupsIds;
    }

    public Group getSelfGroup() {
        return selfGroup;
    }

    public UUID getSelfGroupId() {
        return selfGroupId;
    }

    public UUID getId() {
        return id;
    }

    /* Setters */
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public void setSelfGroup(Group selfGroup) {
        this.selfGroup = selfGroup;
    }

    /* Other Methods */
    public void addProject(Project project) {
        if (this.projects != null) // activeUser
            this.projects.add(project);
        if (!this.projectsIds.contains(project.getId()))
            this.projectsIds.add(project.getId());
    }

    public void removeProject(Project project) {
        if (this.projects != null) // activeUser
            this.projects.remove(project);
        this.projectsIds.removeIf(pId -> pId.equals(project.getId()));
    }

    public void addGroup(Group group) {
        this.groups.add(group);
        this.groupsIds.add(group.getId());
    }

    @JsonIgnore
    public Boolean isAdmin() {
        if (selfGroupId.equals(ADMIN_GROUP)) return true;
        for (UUID groupId : groupsIds)
            if (groupId.equals(ADMIN_GROUP)) return true;
        return false;
    }

    public boolean isRightPassword(String pwd) {
        return this.getPassword().equals(pwd);
    }

    /* Message Methods */
    public void writeNewMessage(Dashboard dash) { // TODO MVC/move/delete
        Scanner sc = new Scanner(System.in);

        System.out.println("Add a message. Title [Enter] Content [Enter]");
        String title = sc.nextLine();
        String msg = sc.nextLine();
        Message newMessage = new Message(title, msg, LocalDateTime.now().toString(), this);
        dash.addMessage(newMessage);
    }
}
