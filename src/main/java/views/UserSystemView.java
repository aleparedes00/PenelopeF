package views;

import models.*;

import java.util.Map;
import java.util.UUID;

public class UserSystemView {
    private UserSystem model;

    public UserSystemView(UserSystem model) {
        this.model = model;
    }

    void printUsersByGroup() {
        for (Map.Entry<UUID, Group> group : model.getGroups().entrySet()) {
            group.getValue().printUsersInGroup();
        }
    }

    void printUsers() {
        for (Map.Entry<UUID, User> user : model.getUsers().entrySet()) {
            System.out.println(user.getValue().getUsername());
        }
    }

    void printGroupsByUser() {
        for (Map.Entry<UUID, User> user : model.getUsers().entrySet()) {
            user.getValue().printGroupsOfUser();
        }
    }

    void printGroups() {
        for (Map.Entry<UUID, Group> group : model.getGroups().entrySet()) {
            System.out.println(group.getValue().getName());
        }
    }
}
