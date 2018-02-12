package models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task extends Message { // Now extends Message as it has similar fields (a title, a content, an author) and methods

    //private models.Group group;
    private Priority priority;
    private UUID id;

    /* Constructor by default */
    public Task() { }
//TODO Remplacer le LocalDateTime par un LocalDateTime.now.toString au moment de l'appel du constructeur
    /*Constructor*/
    public Task(String title, String content, /*models.Group group,*/ Priority priority, User owner){
        super(title, content, LocalDateTime.now(), owner);
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
