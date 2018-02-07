package models;

import java.util.ArrayList;
import java.util.UUID;
//TODO La date posse un problème au moment de la deserialization car "Cannot construct instance of `java.time.LocalDateTime` (no Creators, like default construct, exist): cannot deserialize from Object value (no delegate- or property-based Creator)"
public class Project {

    private String nameOfProject;
    private ArrayList<Task> projectTasks;
    private ArrayList<Document> files;
    private Group group;
    private UUID id;
    //private LocalDateTime date;
    private Priority priority;
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

    public Project(String nameOfProject, Group group, Priority priority) {
        this.nameOfProject = nameOfProject;
        this.projectTasks = new ArrayList<>();
        this.files = new ArrayList<>();
        this.group = group;
        this.id = UUID.randomUUID();
        //this.date = LocalDateTime.now();
        this.priority = priority;
    }

    public Boolean addToArrayTask(Task task) {
        return projectTasks.add(task);
    }

    public Boolean removeTask(Task task) {
        return projectTasks.removeIf(taskInList -> taskInList.getId() == task.getId());
    }

    public Boolean addToArrayDoc(Document file) {
        return files.add(file);
    }

    public Boolean removeDoc(Document file) {
        return files.removeIf(document -> document.getId() == file.getId());
    }


    public void setGroup(Group group) {
        this.group = group;
    }

    /* Getters */
    public Group getGroup() {
        return group;
    }

    public UUID getId() {
        return id;
    }

    public void setId(String idStr) { id = UUID.fromString(idStr); }

    public String getNameOfProject() {
        return nameOfProject;
    }

    public void setNameOfProject(String nameOfProject) {
        this.nameOfProject = nameOfProject;
    }

    public ArrayList<Document> getFiles() {
        return new ArrayList<>(files);
    }

    /*public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }*/

    public ArrayList<Task> getProjectTasks() {
        return new ArrayList<>(projectTasks);
    }

    public Priority getPriority() {
        return priority;
    }
}

