package models;

import models.User;

public class Contact {

    private User user;

    private String email;
    private String phone;

    /* Getters */
    public User getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
