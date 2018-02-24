package models;

import static tools.ScannerTools.*;
import static tools.PasswordTools.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserSystem {
    private HashMap<UUID, User> users;
    private HashMap<UUID, Group> groups;

    public UserSystem(HashMap<UUID, User> users, HashMap<UUID, Group> groups) {
        this.users = users;
        this.groups = groups;

        if (users.isEmpty()) {
            User root = new User("", "", "root", "root");

            Group admin = root.getSelfGroup();

            this.users.put(root.getId(), root);
            this.groups.put(admin.getId(), admin);
        }
    }

    /* Getters */
    public HashMap<UUID,User> getUsers() {
        return users;
    }

    public HashMap<UUID, Group> getGroups() {
        return groups;
    }

    public Group getGroupFromName(String name) {
        for (Map.Entry<UUID,Group> group : groups.entrySet()) {
            if (group.getValue().getName().equals(name))
                return group.getValue();
        }
        return null;
    }

    public User getUserFromUsername(String username) {
        for (Map.Entry<UUID,User> user : users.entrySet()) {
            if (user.getValue().getUsername().equals(username))
                return user.getValue();
        }
        return null;
    }

    /* Password Methods */
    private void changeuserpassword() {
        System.out.print("Which user? ");
        User userToEdit = getUserFromUsername(scanString());
        if (userToEdit != null) {
            changepassword(userToEdit, true);
        } else System.out.println("User not found.");
    }

    private void changepassword(User user, boolean fromAdmin) {
        String oldPwd = "";
        if (!fromAdmin) {
            System.out.print("Old password? ");
            oldPwd = scanString();
        }
        System.out.print("New password? " +
                "(Must contain at least 1 capital letter, 1 number and 1 SPECIAL character) ");
        String newPwd = scanString();
        System.out.print("Confirm new password: ");
        String confirmPwd = scanString();

        if (user.isRightPassword(oldPwd) || fromAdmin) {
            if (hasRightFormat(newPwd)) {
                if (confirmPwd.equals(newPwd)) {
                    user.setPassword(newPwd);
                    System.out.println("Password successfully changed.");
                } else System.out.println("Confirmation password and new password don't match");
            } else
                System.out.println("Password must contain at least 1 capital letter, 1 number and 1 SPECIAL character");
        } else System.out.println("Wrong password.");
    }

    public void changepassword(User user) {
        changepassword(user, false);
    }

    private boolean hasRightFormat(String pwd) {
        boolean hasCap = false;
        boolean hasNum = false;
        boolean hasSpecial = false;

        for (int i = 0; i < pwd.length(); i++) {
            if (ALPHA_CAPS.contains("" + pwd.charAt(i)))
                hasCap = true;
            if (NUM.contains("" + pwd.charAt(i)))
                hasNum = true;
            if (SPECIAL.contains("" + pwd.charAt(i)))
                hasSpecial = true;
        }

        return (hasCap && hasNum && hasSpecial);
    }

//    /* Printing Methods */
//    void printUsersByGroup() {
//        for (Group group : groups) {
//            group.printUsersInGroup();
//        }
//    }
//
//    void printUsers() {
//        for (User user : users) {
//            System.out.println(user.getUsername());
//        }
//    }
//
//    void printGroupsByUser() {
//        for (User user : users) {
//            user.printGroupsOfUser();
//        }
//    }
//
//    void printGroups() {
//        for (Group group : groups) {
//            System.out.println(group.getName());
//        }
//    }
//
//    /* Logging In */
//    User userLogin(User currentUser) {
//        System.out.print("Username? ");
//        String username = scanString();
//        System.out.print("Password? ");
//        String pwd = scanString();
//
//        User user = getUserFromUsername(username);
//        if (user != null && user.isRightPassword(pwd)) {
//            if (currentUser.equals(user))
//                System.out.println("Already logged in as user " + currentUser.getUsername());
//            else
//                System.out.println("Successfully logged in as user " + user.getUsername());
//            return user;
//        }
//        System.out.println("Error, wrong username or password.");
//        return null;
//    }
//
//    /* System Commands */
//    void printAvailableCommands(User user) {
//        boolean admin = user.isAdmin();
//        System.out.println("Available commands:");
//
//        if (admin) System.out.println("addusertogroup\t\tAdds a user to a group");
//        System.out.println("changepassword\t\tChanges your password");
//        if (admin) System.out.println("changeuserpassword\tChanges the password of another user");
//        if (admin) System.out.println("creategroup\t\t\tCreates a new group");
//        if (admin) System.out.println("createuser\t\t\tCreates a new user");
//        System.out.println("groups\t\t\t\tLists current user's groups");
//        System.out.println("help\t\t\t\tLists available commands");
//        if (admin) System.out.println("listgroups\t\t\tPrints detailed list of groups");
//        if (admin)
//            System.out.println("listgroupsbyuser\tPrints detailed list of users and the groups they belong to");
//        if (admin) System.out.println("listusers\t\t\tPrints detailed list of users");
//        if (admin) System.out.println("listusersbygroup\tPrints detailed list of groups and the users in them");
//        System.out.println("shutdown\t\t\tShuts down the system");
//        System.out.println("switchuser\t\t\tSwitch user");
//    }
//
//    User waitForAction(User user) {
//        boolean admin = user.isAdmin();
//
//        System.out.print(user.getUsername() + "@test:~$ ");
//        String input = scanString();
//
//        switch (input) {
//            case "addusertogroup":
//                if (admin) prepareAddUserToGroup();
//                else unknowncommand();
//                break;
//            case "changeuserpassword":
//                if (admin) changeuserpassword();
//                else unknowncommand();
//                break;
//            case "changepassword":
//                changepassword(user);
//                break;
//            case "creategroup":
//                if (admin) groups.add(createGroup());
//                else unknowncommand();
//                break;
//            case "createuser":
//                if (admin) users.add(createUser());
//                else unknowncommand();
//                break;
//            case "groups":
//                user.printGroupsOfUser();
//                break;
//            case "help":
//                printAvailableCommands(user);
//                break;
//            case "listgroups":
//                if (admin) printGroups();
//                else unknowncommand();
//                break;
//            case "listgroupsbyuser":
//                if (admin) printGroupsByUser();
//                else unknowncommand();
//                break;
//            case "listusers":
//                if (admin) printUsers();
//                else unknowncommand();
//                break;
//            case "listusersbygroup":
//                if (admin) printUsersByGroup();
//                else unknowncommand();
//                break;
//            case "shutdown":
//                System.out.println("Shutting down...");
//                return null;
//            case "switchuser":
//                User switched = userLogin(user);
//                if (switched != null) return switched;
//                break;
//            default:
//                unknowncommand();
//        }
//
//        return user;
//    }
//
//    private void unknowncommand() {
//        System.out.println("Command not recognised. Enter \"help\" for a list of available commands");
//    }
//
//    public static void main(String[] args) {
//        // Setting "OS"
//        UserSystem os = new UserSystem();
//        User currentUser = os.getUsers().get(0); // root
//        boolean running = true;
//
//        while (running) {
//            currentUser = os.waitForAction(currentUser);
//            running = (currentUser != null);
//            System.out.println();
//        }
//    }
}
