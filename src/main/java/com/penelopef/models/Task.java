package com.penelopef.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static java.lang.Boolean.*;

public class Task extends Message {

    private Priority priority;
    private Boolean active;

    /* Constructor by default */
    public Task() { }

    /*Constructor*/
    public Task(String title, String content, String date, Priority priority, User owner){
        super(title, content, date, owner);
        this.priority = priority;
        this.active = TRUE;
    }

    /* Getters and Setters */
    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean b) {
        active = b;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

}
