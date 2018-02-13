package models;

import java.util.UUID;

public class Task extends Message { // Now extends Message as it has similar fields (a title, a content, an author) and methods

    private String date;
    private Priority priority;
    private UUID id;

    /* Constructor by default */
    public Task() { }

    /*Constructor*/
    public Task(String title, String content, String date, /*models.Group group,*/ Priority priority, User owner){
        super(title, content, date, owner);
        this.priority = priority;
        this.id = UUID.randomUUID();
    }

    /* Getters and Setters */
    public User getOwner() { // getOwner = getAuthor, but we can keep this method if the name is more explicit
        return getAuthor();
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public UUID getId() {
        return id;
    }
}
