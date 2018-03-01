package com.penelopef.views;

import com.penelopef.models.*;

import static com.penelopef.tools.ScannerTools.scanString;
import static com.penelopef.tools.DataTools.*;

public class AdminView {
    /* User Creation */
    public String[] enterUserToCreate() {
        String[] fullName = new String[2];

        System.out.print("First name? ");
        fullName[0] = scanString();
        System.out.print("Last name? ");
        fullName[1] = scanString();

        return fullName;
    }

    public String enterCustomUsername() {
        System.out.println("Couldn't generate username, please enter one manually");
        return scanString();
    }

    public String reEnterCustomUsername(String customUsername) {
        System.out.println("Username " + customUsername + " already taken, please enter another username.");
        return scanString();
    }

    public void creationSuccessful(User user) {
        System.out.println("Created user " + user.getUsername() + " (" + user.getName() + ")");
        System.out.println("Password is " + user.getPassword() + " remember it!");
    }

    /* Group Creation */
    public String enterGroupToCreate() {
        System.out.print("Group name? ");
        return scanString();
    }

    public String reEnterGroupName(String groupName) {
        System.out.println("Group name " + groupName + " is already taken. Try another name.");
        return scanString();
    }

    public void creationSuccessful(Group group) {
        System.out.println("Created group " + group.getName());
    }

    /* Add Users to Group */
    public String addMoreUsers() {
        System.out.println("Add user to created group? Y/N");
        return scanString();
    }

    public User selectUser() {
        System.out.print("Which user? ");
        User userToAdd = getUserFromUsername(scanString());

        if (userToAdd == null) System.out.println("User not found.");
        return userToAdd;
    }

    public Group selectGroup() {
        System.out.print("Which group? ");
        Group groupToBeAddedTo = getGroupFromName(scanString());
        if (groupToBeAddedTo == null) System.out.println("Group not found.");
        return groupToBeAddedTo;
    }

    public void additionSuccessful(User user, Group group) {
        System.out.println("Added user " + user.getUsername() + " to group " + group.getName());
    }

    public void errorUserAlreadyInGroup(User user, Group group) {
        System.out.println("Error: group " + group.getName() + " already contains user " + user.getUsername() + "!");
    }

    /* Password Methods */
    public String enterOldPassword() {
        System.out.print("Old password? ");
        return scanString();
    }

    public String enterNewPassword() {
        System.out.print("New password? " +
                "(Must contain at least 1 capital letter, 1 number and 1 special character) ");
        String newPwd = scanString();
        System.out.print("Confirm new password: ");
        String confirmPwd = scanString();

        if (confirmPwd.equals(newPwd))
            return newPwd;
        else {
            System.out.println("Confirmation password and new password don't match");
            return null;
        }
    }

    public void errorWrongPassword() {
        System.out.println("Wrong password.");
    }

    public void errorWrongFormat() {
        System.out.println("Password must contain at least 1 capital letter, 1 number and 1 SPECIAL character");
    }

    public void passwordChangeSuccessful() {
        System.out.println("Password successfully changed.");
    }

    /* Deletion Methods */
    public boolean confirmation() {
        System.out.print("Are you sure you want to proceed? Y/N");
        return (scanString().toUpperCase().equals("Y"));
    }

    public void deletionSuccessful(User userToDelete) {
        System.out.println("Successfully deleted user " + userToDelete.getUsername());
    }

    public void errorDeletingSelf() {
        System.out.println("Error: cannot delete yourself");
    }

    public void deletionSuccessful(Group groupToDelete) {
        System.out.println("Successfully deleted user " + groupToDelete.getName());
    }

    public void errorDeletingSelfGroup() {
        System.out.println("Error: impossible to delete user's default group.");
    }

    public void errorActiveProjects() {
        System.out.println("Error: impossible to delete group as it still has some active projects");
    }
}
