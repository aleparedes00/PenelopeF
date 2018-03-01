package com.penelopef.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.Boolean.*;
import static com.penelopef.tools.DataTools.getGroupFromId;
import static com.penelopef.PenelopeF.defaultProjectsPath;

public class Project implements FSListener {

    private String name;

    private ArrayList<Task> tasks;

    @JsonIgnore
    private List<String> documents;

    private String pathToProject;

    //private Dashboard dashboard;

    private UUID groupId;

    private String date;
    private Priority priority;

    private Boolean active;
    private UUID id;

    /** ALE this is the explanation about the random ID generator.
     * Static factory to retrieve a type 4 (pseudo randomly generated) UUID.
     *
     * The UUID is generated using a cryptographically strong pseudo
     * random number generator.
     *
     * return  A randomly generated {@code UUID}
     */

    /* Constructor by default */
    public Project() {
    }
    /*Constructor*/

    public Project(String name, Group group, String date, Priority priority) {
        this.name = name;

        this.tasks = new ArrayList<>();

        this.pathToProject = defaultProjectsPath + name;

        this.groupId = group.getId();

        this.id = UUID.randomUUID();

        this.date = date;

        this.priority = priority;
        this.active = TRUE;

        this.documents = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    /* Getters */
    public String getName() {
        return name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String getPathToProject() {
        return pathToProject;
    }

    @JsonIgnore
    public Group getGroup() {
        return getGroupFromId(groupId);
    }

    public UUID getGroupId() {
        return groupId;
    }

    public String getDate() {
        return date;
    }

    public Priority getPriority() {
        return priority;
    }

    public UUID getId() {
        return id;
    }

    public Boolean isActive() {
        return active;
    }

    /* Setters */
    public void setId(String idStr) { id = UUID.fromString(idStr); }
    public void setPriority(Priority priority) {this.priority = priority; }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDocuments() {
        if (documents == null) {
            documents = new ArrayList<>();
        }
        return documents;
    }

    @Override
    public void onCreate(String pathToNewFile) {
        File file = new File(defaultProjectsPath+name+"/"+pathToNewFile);
        if (!file.isFile() || file.isHidden()) return;
        documents.add(pathToNewFile);
    }

    @Override
    public void onDelete(String pathToDeleteFile) {
        File file = new File(pathToDeleteFile);
        if (!file.isFile() && !file.isHidden()) return;
        documents.remove(pathToDeleteFile);
    }

    @Override
    public void onUpdate(String pathToUpdateFile) {
    }
}

