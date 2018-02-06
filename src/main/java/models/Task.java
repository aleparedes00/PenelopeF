package models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task extends Message { // Now extends Message as it has similar fields (a title, a content, an author) and methods

    //private models.Group group;
    private String priority;
    private UUID id;

    /*Constructor*/
    public Task(String title, String content, /*models.Group group,*/ String priority, User owner){
        super(title, content, LocalDateTime.now(), owner);
        this.setPriority(priority);
        this.id = UUID.randomUUID();
    }

    /* Getters and Setters */
    public User getOwner() { // getOwner = getAuthor, but we can keep this method if the name is more explicit
        return getAuthor();
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
