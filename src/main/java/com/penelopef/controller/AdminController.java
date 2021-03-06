package com.penelopef.controller;

import com.penelopef.models.*;
import com.penelopef.views.AdminView;

import java.util.Map;
import java.util.UUID;

import static com.penelopef.PenelopeF.getSystemData;
import static com.penelopef.tools.PasswordTools.*;
import static com.penelopef.tools.DataTools.*;
import static com.penelopef.PenelopeF.activeUser;

class AdminController {
    private AdminView view;

    AdminController(AdminView view) {
        this.view = view;
    }

    /* Creation Methods */
    /* User Creation */
    void createUser() {
        // Input
        String[] fullName = view.enterUserToCreate();
        String firstName = fullName[0];
        String lastName = fullName[1];

        // Create user
        User user = new User(firstName, lastName);

        // Change username if needed (must be unique)
        if (!checkUsername(user.getUsername()))
            if (!fixUsername(user)) {
                String customUsername = view.enterCustomUsername();
                while (!checkUsername(customUsername))
                    customUsername = view.reEnterCustomUsername(customUsername);
                user.setUsername(customUsername);
                user.getSelfGroup().setName(customUsername);
            }

        // Confirmation message
        view.creationSuccessful(user);

        // Save to systemData
        getSystemData().getUsers().put(user.getId(), user);
        Group userSelfGroup = user.getSelfGroup();
        getSystemData().getGroups().put(userSelfGroup.getId(), userSelfGroup);
    }

    private boolean checkUsername(String username) {
        return (getUserFromUsername(username) == null);
    }

    private boolean fixUsername(User user) {
        String[] usernameParts = user.getUsername().split("_");

        for (char newLetter = 'a'; newLetter <= 'z'; newLetter++)
            if (checkUsername(usernameParts[0] + "_" + newLetter)) {
                user.setUsername(usernameParts[0] + "_" + newLetter);
                user.getSelfGroup().setName(usernameParts[0] + "_" + newLetter);
                return true;
            }
        return false;
    }

    /* Group Creation */
    void createGroup() {
        // Input
        String groupName = view.enterGroupToCreate();
        while (!checkGroupName(groupName))
            groupName = view.reEnterGroupName(groupName);

        // Create group
        Group group = new Group(groupName);
        view.creationSuccessful(group);

        // Add users to group
        addUsersToCreatedGroup(group);

        // Save to systemData
        getSystemData().getGroups().put(group.getId(), group);
    }

    private boolean checkGroupName(String groupName) {
        return (getGroupFromName(groupName) == null);
    }

    /* Add Users to Group */
    private void addUsersToCreatedGroup(Group group) {
        boolean addingMoreUsers = true;
        while (addingMoreUsers) {
            if (view.addMoreUsers().toUpperCase().equals("Y"))
                prepareAddUserToGroup(group);
            else addingMoreUsers = false;
        }
    }

    private void prepareAddUserToGroup(Group groupToBeAddedTo) {
        User userToAdd = view.selectUser();
        if (userToAdd != null)
            addUserToGroup(userToAdd, groupToBeAddedTo);
    }

    void prepareAddUserToGroup() {
        User userToAdd = view.selectUser();
        if (userToAdd != null) {
            Group groupToBeAddedTo = view.selectGroup();
            if (groupToBeAddedTo != null)
                addUserToGroup(userToAdd, groupToBeAddedTo);
        }
    }

    private void addUserToGroup(User user, Group group) {
        boolean alreadyInGroup = group.getUsersIds().contains(user.getId());
        if (alreadyInGroup) view.errorUserAlreadyInGroup(user, group);
        else {
            // Add user to group
            group.getUsersIds().add(user.getId());
            user.getGroupsIds().add(group.getId());

            // Add group's project to user
            for (Project project : getSystemData().getProjects().values()) {
                if (project.getGroupId().equals(group.getId()))
                    user.addProject(project);
            }

            view.additionSuccessful(user, group);
        }
    }

    /* Password Methods */
    void changeUserPassword() {
        User userToEdit = view.selectUser();
        if (userToEdit != null)
            changePassword(userToEdit);
    }

    void changePassword(User user) {
        String oldPwd = "";
        if (!activeUser.isAdmin()) oldPwd = view.enterOldPassword();
        String newPwd = view.enterNewPassword();

        if (user.isRightPassword(oldPwd) || activeUser.isAdmin()) {
            if (newPwd != null) {
                if (hasRightFormat(newPwd)) {
                    user.setPassword(newPwd);
                    view.passwordChangeSuccessful();
                } else view.errorWrongFormat();
            }
        } else view.errorWrongPassword();
    }

    /* Deletion Methods */
    void deleteUser() {
        User userToDelete = view.selectUser();
        if (userToDelete != null)
            if (userToDelete.equals(activeUser)) view.errorDeletingSelf();
            else if (view.confirmation()) {
                // Delete User from Users List
                getSystemData().getUsers().remove(userToDelete.getId());

                // Delete User's SelfGroup
                getSystemData().getGroups().remove(userToDelete.getSelfGroupId());

                // Delete User from the Groups they're in
                for (UUID groupId : userToDelete.getGroupsIds()) {
                    Group group = getGroupFromId(groupId);
                    group.getUsersIds().remove(userToDelete.getId());
                }

                // Delete Messages by User
                for (Map.Entry<UUID, Message> messageEntry : getSystemData().getMessages().entrySet()) {
                    if (messageEntry.getValue().getAuthorId().equals(userToDelete.getId()))
                        getSystemData().getMessages().remove(messageEntry.getKey());
                }

                view.deletionSuccessful(userToDelete);
            }
    }

    void deleteGroup() {
        Group groupToDelete = view.selectGroup();
        if (groupToDelete != null)
            if (hasActiveProjects(groupToDelete)) view.errorActiveProjects();
            else if (groupToDelete.isSelfGroup()) view.errorDeletingSelfGroup();
            else {
                if (view.confirmation()) {
                    // Delete Group from Group List
                    getSystemData().getGroups().remove(groupToDelete.getId());

                    // Delete Group from the Users inside
                    for (UUID userId : groupToDelete.getUsersIds()) {
                        User user = getUserFromId(userId);
                        user.getGroupsIds().remove(groupToDelete.getId());
                    }

                    view.deletionSuccessful(groupToDelete);
                }
            }
    }

    private boolean hasActiveProjects(Group groupToDelete) {
        for (Map.Entry<UUID, Project> projectEntry : getSystemData().getProjects().entrySet()) {
            if (projectEntry.getValue().getGroupId().equals(groupToDelete.getId()))
                return true;
        }
        return false;
    }
}
