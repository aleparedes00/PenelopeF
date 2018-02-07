package models;

public class Contact {

    private User user;

    private String email;
    private String phone;

    /*Constructor by default*/
    public Contact() { }

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
