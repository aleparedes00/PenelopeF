package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Project {

    private String name;

    @JsonIgnore
    private HashMap<UUID,Task> tasks;
    private ArrayList<UUID> tasksIds;

    @JsonIgnore
    private HashMap<UUID,Document> files;
    private ArrayList<UUID> filesIds;

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
     * The {@code UUID} is generated using a cryptographically strong pseudo
     * random number generator.
     *
     * @return  A randomly generated {@code UUID}
     */

    //My proposal for this is to make just a list with the name of the Document that will display when the user enter on the DOC menu in the projet. We'll talk about it
    //ArrayList<Document> files;
    /*
    **  Note: ArrayList probably isn't the best structure to model the file hierarchy.
    **  At first, all files will be in the root folder of the project. But it would be good to be able to create folders
    **  (src, lib, resources...) and have a complete hierarchy. We'll have to think of a good way to handle this.
    **
    **  Right now models.Document objets have a java.io.File field, which can both be a file or a directory. We could imagine a
    **  recursive way of exploring the Project with that. But there are other possible implementations.
    */
    //ArrayList<Task> toDoList;

    /* Constructor by default */
    public Project() { }
    /*Constructor*/

    public Project(String nameOfProject, Group group, String date, Priority priority) {
        this.name = nameOfProject;
        this.tasks = new HashMap<>();
        this.tasksIds = new ArrayList<>();
        this.files = new HashMap<>();
        this.filesIds = new ArrayList<>();
        this.group = group;
        this.groupID = group.getId();
        this.id = UUID.randomUUID();
        this.date = date;
        this.priority = priority;
    }

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
        tasksIds.add(task.getId());
    }

    public void removeTask(Task task) {
        tasks.remove(task.getId());
        tasksIds.removeIf(tId -> tId == task.getId());
    }

    public void addDocument(Document file) {
        files.put(file.getId(), file);
        filesIds.add(file.getId());
    }

    public void removeDoc(Document file) {
        files.remove(file.getId());
        filesIds.removeIf(fId -> fId == file.getId());
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    /* Getters */
    public String getName() {
        return name;
    }

    public HashMap<UUID,Task> getTasks() {
        return tasks;
    }
    public ArrayList<UUID> getTasksIds() {
        return tasksIds;
    }

    public HashMap<UUID,Document> getFiles() {
        return files;
    }
    public ArrayList<UUID> getFilesIds() {
        return filesIds;
    }

    public Group getGroup() {
        return group;
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

    /*public void setDate(LocalDateTime date) {
        this.date = date;
    }*/




}

