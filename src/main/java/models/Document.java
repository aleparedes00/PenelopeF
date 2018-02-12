package models;

import java.util.UUID;

public class Document {
    private String nameOfDoc;
    //private File file;
    private User owner;
    //private Project project; // The project this document belongs to [Update 2018-02-06: might not be needed anymore, see to-do below]
    //private models.DocumentType type; // The type of document
    private UUID id;

    /*Constructor by default*/
    public Document() {}

    public Document(String name, User owner, Project project){
        this.setNameOfDoc(name);
        this.owner = owner;
        //this.project = project; //TODO I'm not sure if this is redundant because we have an ArrayList<Documents> -> Alex: Yeah, we might not need this after all. Like, we won't need to access a Project from a Document. I say we comment that field for now and eventually remove it. Leaving the TODO here so we don't forget
        this.id = UUID.randomUUID();
    }

    public String getNameOfDoc() {
        return nameOfDoc;
    }

    public void setNameOfDoc(String nameOfDoc) {
        this.nameOfDoc = nameOfDoc;
    }




    /* Getters */
    /*public File getFile() {
        return file;
    }*/

    public String getName() {
        return nameOfDoc;
    }

    public User getOwner() {
        return owner;
    }

    public UUID getId() {
        return id;
    }

//    public Project getProject() {
//        return project;
//    }

   /* public models.DocumentType getType() {
        return type;
    }*/
}
