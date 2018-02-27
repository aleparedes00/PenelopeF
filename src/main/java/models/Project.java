package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.UUID;

import static java.lang.Boolean.*;
import static tools.DataTools.getGroupFromId;
import static views.PenelopeF.defaultProjectsPath;

public class Project {

    private String name;

//    @JsonIgnore
    private ArrayList<Task> tasks;
//    private ArrayList<UUID> tasksIds;

    private String pathToProject;

    //private Dashboard dashboard;
//    @JsonIgnore
//    private Group group;
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
    public Project() { }
    /*Constructor*/

    public Project(String name, Group group, String date, Priority priority) {
        this.name = name;
        this.tasks = new ArrayList<>();
//        this.tasksIds = new ArrayList<>();
        this.pathToProject = defaultProjectsPath + "/" + name;
//        this.group = group;
        this.groupId = group.getId();
        this.id = UUID.randomUUID();
        this.date = date;
        this.priority = priority;
        this.active = TRUE;
    }

    public void addTask(Task task) {
        tasks.add(task);
//        tasksIds.add(task.getId());
    }

    public void removeTask(Task task) {
        tasks.remove(task);
//        tasksIds.removeIf(tId -> tId == task.getId());
    }

//    public void setGroup(Group group) {
//        this.group = group;
//    }

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
//    public ArrayList<UUID> getTasksIds() {
//        return tasksIds;
//    }

    public String getPathToProject() {
        return pathToProject;
    }

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


}

