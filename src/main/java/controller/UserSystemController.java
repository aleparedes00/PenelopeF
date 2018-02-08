package controller;

import models.*;
import views.UserSystemView;

import static tools.ScannerTools.scanString;

public class UserSystemController {
    private UserSystem model;
    private UserSystemView view;

    public UserSystemController(UserSystem model, UserSystemView view) {
        this.model = model;
        this.view = view;
    }

    /* Managing Users and Groups */
    //TODO Create UserView to put the print there
    public User createUser() {
        System.out.print("First name? ");
        String firstName = scanString();
        System.out.print("Last name? ");
        String lastName = scanString();

        User user = new User(firstName, lastName);
        System.out.println("Created user " + user.getUsername() + " (" + user.getName() + ")");
        System.out.println("Password is " + user.getPassword() + ", remember it!");

        return user;
    }

    public Group createGroup() {
        System.out.print("Group name? ");
        String groupName = scanString();

        Group group = new Group(groupName);
        System.out.println("Created group " + group.getName());

        return group;
    }
}
