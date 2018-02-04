package models;

import models.Project;

import java.io.File;
import java.util.UUID;

public class Document {
    private String nameOfDoc;
    private File file;
    private User owner;
    private Project project; // The project this document belongs to
    //private models.DocumentType type; // The type of document
    private UUID id;

    public Document(String name, User owner, Project project){
        this.setNameOfDoc(name);
        this.owner = owner;
        this.project = project;
        this.id = UUID.randomUUID();
    }

    public String getNameOfDoc() {
        return nameOfDoc;
    }

    public void setNameOfDoc(String nameOfDoc) {
        this.nameOfDoc = nameOfDoc;
    }




    /* Getters */
    public File getFile() {
        //this.file.getName() TODO on recupère le fichier
        return file;
    }

    public User getOwner() {
        return owner;
    }

    public UUID getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

   /* public models.DocumentType getType() {
        return type;
    }*/
}
