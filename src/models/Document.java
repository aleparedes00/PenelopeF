package models;

import models.Project;

import java.io.File;
import java.util.UUID;

public class Document {
    private String nameOfDoc;
    private User owner;
    private UUID id;

    public Document(String name, User owner){
        this.setNameOfDoc(name);
        this.id = UUID.randomUUID();
    }

    public String getNameOfDoc() {
        return nameOfDoc;
    }

    public void setNameOfDoc(String nameOfDoc) {
        this.nameOfDoc = nameOfDoc;
    }

    public UUID getId() {
        return id;
    }
    /*

    private File file;
    private Project project; // The project this document belongs to
    private models.DocumentType type; // The type of document

    *//* Getters *//*
    public File getFile() {
        return file;
    }

    public Project getProject() {
        return project;
    }

    public models.DocumentType getType() {
        return type;
    }*/
}
