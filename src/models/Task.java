package models;

import java.util.UUID;

public class Task {

    private String title;
    private String content;
    //private models.Group group;
    private String priority;
    private User owner;
    private UUID id;

    /*Constructor*/
    public Task(String title, String content, /*models.Group group,*/ String priority, User owner){
        this.setTitle(title);
        this.setContent(content);
        this.setPriority(priority);
        this.setOwner(owner);
        this.id = UUID.randomUUID();
    }

    /* Getters */

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public UUID getId() {
        return id;
    }
}
