package views;

import models.*;

public class UserSystemView {
    private UserSystem model;

    public UserSystemView(UserSystem model) {
        this.model = model;
    }

    void printUsersByGroup() {
        for (Group group : model.getGroups()) {
            group.printUsersInGroup();
        }
    }

    void printUsers() {
        for (User user : model.getUsers()) {
            System.out.println(user.getUsername());
        }
    }

    void printGroupsByUser() {
        for (User user : model.getUsers()) {
            user.printGroupsOfUser();
        }
    }

    void printGroups() {
        for (Group group : model.getGroups()) {
            System.out.println(group.getName());
        }
    }
}
