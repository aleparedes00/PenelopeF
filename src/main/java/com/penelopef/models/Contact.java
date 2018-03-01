package com.penelopef.models;

public class Contact {

    private String email;
    private String phone;

    /*Constructor by default*/
    public Contact() {
        this.email = "";
        this.phone = "";
    }

    /* Getters */
    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    /* Setters */
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /* toString */
    public String toString() {
        return "E-mail: " + email + "\nPhone: " + phone;
    }
}
