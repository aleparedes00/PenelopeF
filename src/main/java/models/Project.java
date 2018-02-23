package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.UUID;

import static views.PenelopeF.defaultProjectsPath;

public class Project {

    private String name;

//    @JsonIgnore
    private ArrayList<Task> tasks;
//    private ArrayList<UUID> tasksIds;

    private ArrayList<String> documents;

    private String pathToProject;

    //private Dashboard dashboard;
    @JsonIgnore
    private Group group;
    private UUID groupID;

    private String date;
    private Priority priority;
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
        this.documents = new ArrayList<>();
//        this.tasksIds = new ArrayList<>();
        this.pathToProject = defaultProjectsPath + "/" + name;
        this.group = group;
        this.groupID = group.getId();
        this.id = UUID.randomUUID();
        this.date = date;
        this.priority = priority;
    }

    public void addTask(Task task) {
        tasks.add(task);
//        tasksIds.add(task.getId());
    }

    public void addDocument(String docName) {
        documents.add(docName);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
//        tasksIds.removeIf(tId -> tId == task.getId());
    }

    public void setGroup(Group group) {
        this.group = group;
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
        return group;
    }

    public UUID getGroupID() {
        return groupID;
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

    /* Setters */
    public void setId(String idStr) { id = UUID.fromString(idStr); }

    public void setName(String name) {
        this.name = name;
    }


}

