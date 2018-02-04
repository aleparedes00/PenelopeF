package models;

import java.util.ArrayList;
import java.util.UUID;
//TODO ProjectPriority
public class Project {

    private String nameOfProject;
    private ArrayList<Task> projectTask;
    private ArrayList<Document> files;
    private Group group;
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
    public Project(String nameOfProject) {
        this.setNameOfProject(nameOfProject);
        projectTask = new ArrayList<>();
        files = new ArrayList<>();
        this.id = UUID.randomUUID();
    }

    public Boolean addToArrayTask(Task task) {
        return projectTask.add(task);
    }

    public Boolean removeTask(Task task) {
        return projectTask.removeIf(taskInList -> taskInList.getId() == task.getId());
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

    public String getNameOfProject() {
        return nameOfProject;
    }

    public void setNameOfProject(String nameOfProject) {
        this.nameOfProject = nameOfProject;
    }

    public ArrayList<Document> getFiles() {
        return new ArrayList<>(files);
    }

    public ArrayList<Task> getArrayTask() {
        return new ArrayList<>(projectTask);
    }
}
