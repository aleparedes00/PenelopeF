package controller;

import models.Group;
import models.User;
import models.UserSystem;
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
    //TODO BETTER REFACTORING FOR MVC!!!
    public void createUser() {
        // Input
        System.out.print("First name? ");
        String firstName = scanString();
        System.out.print("Last name? ");
        String lastName = scanString();

        // Create user
        User user = new User(firstName, lastName);
        if (!checkUsername(user.getUsername())) {
            if (!fixUsername(user)) {
                System.out.println("Couldn't generate username, please enter one manually");
                String customUsername = scanString();
                while (!checkUsername(customUsername)) {
                    System.out.println("Username " + customUsername + " already taken, please enter another username.");
                    customUsername = scanString();
                }
                user.setUsername(customUsername);
                user.getSelfGroup().setName(customUsername);
            }
        }
        System.out.println("Created user " + user.getUsername() + " (" + user.getName() + ")");
        System.out.println("Password is " + user.getPassword() + " remember it!");

        // Save to systemData
        model.getUsers().put(user.getId(), user);
        Group userSelfGroup = user.getSelfGroup();
        model.getGroups().put(userSelfGroup.getId(), userSelfGroup);
    }

    private boolean checkUsername(String username) {
        return (model.getUserFromUsername(username) == null);
    }

    private boolean fixUsername(User user) {
        String[] usernameParts = user.getUsername().split("_");

        for (char newLetter = 'a'; newLetter <= 'z'; newLetter++) {
            if (checkUsername(usernameParts[0] + "_" + newLetter)) {
                user.setUsername(usernameParts[0] + "_" + newLetter);
                user.getSelfGroup().setName(usernameParts[0] + "_" + newLetter);
                return true;
            }
        }
        return false;
    }

    public void createGroup() {
        // Input
        System.out.print("Group name? ");
        String groupName = scanString();
        while (!checkGroupName(groupName)) {
            System.out.println("Group name " + groupName + " is already taken. Try another name.");
            groupName = scanString();
        }

        // Create group
        Group group = new Group(groupName);
        System.out.println("Created group " + group.getName());

        // Add users to group
        boolean addingUsers = true;
        while (addingUsers) {
            System.out.println("Add user to created group? Y/N"); // TODO: improve this
            if (scanString().toUpperCase().equals("Y"))
                prepareAddUserToGroup(group);
            else addingUsers = false;
        }

        // Save to systemData
        model.getGroups().put(group.getId(), group);
    }

    private boolean checkGroupName(String groupName) {
        return (model.getGroupFromName(groupName) == null);
    }

    public void prepareAddUserToGroup(Group groupToBeAddedTo) {
        System.out.print("Which user? ");
        User userToAdd = model.getUserFromUsername(scanString());
        if (userToAdd != null) {
            addUserToGroup(userToAdd, groupToBeAddedTo);
        } else System.out.println("User not found.");
    }

    public void prepareAddUserToGroup() {
        System.out.print("Which user? ");
        User userToAdd = model.getUserFromUsername(scanString());
        if (userToAdd != null) {
            System.out.print("Which group? ");
            Group groupToBeAddedTo = model.getGroupFromName(scanString());
            if (groupToBeAddedTo != null)
                addUserToGroup(userToAdd, groupToBeAddedTo);
            else System.out.println("Group not found.");
        } else System.out.println("User not found.");
    }

    private void addUserToGroup(User user, Group group) {
        if (group.getUsersIds().contains(user.getId()))
            System.out.println("Group " + group.getName() + " already contains user " + user.getUsername() + "!");
        else {
            //group.getUsers().add(user);
            group.getUsersIds().add(user.getId());
            //user.addGroup(group);
            user.getGroupsIds().add(group.getId());

            System.out.println("Added user " + user.getUsername() + " to group " + group.getName());
        }
    }
}
