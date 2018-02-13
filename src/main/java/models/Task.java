package models;

public class Task extends Message {

    private Priority priority;

    /* Constructor by default */
    public Task() { }

    /*Constructor*/
    public Task(String title, String content, String date, Priority priority, User owner){
        super(title, content, date, owner);
        this.priority = priority;
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

}
