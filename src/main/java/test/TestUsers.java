package test;

import models.Group;
import models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestUsers {

    static class UserSystem {

        private ArrayList<User> users;
        private ArrayList<Group> groups;

        Scanner sc = new Scanner(System.in);

        UserSystem() {
            users = new ArrayList<>();
            groups = new ArrayList<>();

            User root = new User("", "", "root", "root");
            Group admin = root.getGroups().get(0);

            users.add(root);
            groups.add(admin);
        }

        /* Getters */
        ArrayList<User> getUsers() {
            return users;
        }

        public ArrayList<Group> getGroups() {
            return groups;
        }

        /* Managing Users and Groups */
        User createUser() {
            System.out.print("First name? ");
            String firstName = sc.next();
            System.out.print("Last name? ");
            String lastName = sc.next();

            User user = new User(firstName, lastName);
            System.out.println("Created user " + user.getUsername() + " (" + user.getName() + ")");
            System.out.println("Password is " + user.getPassword() + ", remember it!");

            return user;
        }

        Group createGroup() {
            System.out.print("models.Group name? ");
            String groupName = sc.next();

            Group group = new Group(groupName);
            System.out.println("Created group " + group.getName());

            return group;
        }

        Group getGroupFromName(String name) {
            for (Group group : groups) {
                if (group.getName().equals(name))
                    return group;
            }
            return null;
        }

        User getUserFromLogin(String login) {
            for (User user : users) {
                if (user.getUsername().equals(login))
                    return user;
            }
            return null;
        }

        void prepareAddUserToGroup() {
            System.out.print("Which user? ");
            User userToAdd = getUserFromLogin(sc.next());
            if (userToAdd != null) {
                System.out.print("Which group? ");
                Group groupToBeAddedTo = getGroupFromName(sc.next());
                if (groupToBeAddedTo != null)
                    addUserToGroup(userToAdd, groupToBeAddedTo);
                else System.out.println("models.Group not found.");
            } else System.out.println("models.User not found.");
        }

        void addUserToGroup(User user, Group group) {
            group.getUsers().add(user);
            user.addGroup(group);
            System.out.println("Added user " + user.getUsername() + " to group " + group.getName());
        }

        /* Password Methods */
        private void changeuserpassword() {
            System.out.print("Which user? ");
            User userToEdit = getUserFromLogin(sc.next());
            if (userToEdit != null) {
                changepassword(userToEdit, true);
            } else System.out.println("models.User not found.");
        }

        void changepassword(User user, boolean fromAdmin) {
            String oldPwd = "";
            if (!fromAdmin) {
                System.out.print("Old password? ");
                oldPwd = sc.next();
            }
            System.out.print("New password? " +
                    "(Must contain at least 1 capital letter, 1 number and 1 SPECIAL character) ");
            String newPwd = sc.next();
            System.out.print("Confirm new password: ");
            String confirmPwd = sc.next();

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

        void changepassword(User user) {
            changepassword(user, false);
        }

        boolean hasRightFormat(String pwd) {
            boolean hasCap = false;
            boolean hasNum = false;
            boolean hasSpecial = false;

            for (int i = 0; i < pwd.length(); i++) {
                if (User.ALPHA_CAPS.contains("" + pwd.charAt(i)))
                    hasCap = true;
                if (User.NUM.contains("" + pwd.charAt(i)))
                    hasNum = true;
                if (User.SPECIAL.contains("" + pwd.charAt(i)))
                    hasSpecial = true;
            }

            return (hasCap && hasNum && hasSpecial);
        }

        /* Printing Methods */
        void printUsersByGroup() {
            for (Group group : groups) {
                group.printUsersInGroup();
            }
        }

        void printUsers() {
            for (User user : users) {
                System.out.println(user.getUsername());
            }
        }

        void printGroupsByUser() {
            for (User user : users) {
                user.printGroupsOfUser();
            }
        }

        void printGroups() {
            for (Group group : groups) {
                System.out.println(group.getName());
            }
        }

        /* Logging In */
        User userLogin(User currentUser) {
            System.out.print("Login? ");
            String login = sc.next();
            System.out.print("Password? ");
            String pwd = sc.next();

            User user = getUserFromLogin(login);
            if (user != null && user.isRightPassword(pwd)) {
                if (currentUser.equals(user))
                    System.out.println("Already logged in as user " + currentUser.getUsername());
                else
                    System.out.println("Successfully logged in as user " + user.getUsername());
                return user;
            }
            System.out.println("Error, wrong login or password.");
            return null;
        }

        /* System Commands */
        void printAvailableCommands(User user) {
            boolean admin = user.isAdmin();
            System.out.println("Available commands:");

            if (admin) System.out.println("addusertogroup\t\tAdds a user to a group");
            System.out.println("changepassword\t\tChanges your password");
            if (admin) System.out.println("changeuserpassword\tChanges the password of another user");
            if (admin) System.out.println("creategroup\t\t\tCreates a new group");
            if (admin) System.out.println("createuser\t\t\tCreates a new user");
            System.out.println("groups\t\t\t\tLists current user's groups");
            System.out.println("help\t\t\t\tLists available commands");
            if (admin) System.out.println("listgroups\t\t\tPrints detailed list of groups");
            if (admin)
                System.out.println("listgroupsbyuser\tPrints detailed list of users and the groups they belong to");
            if (admin) System.out.println("listusers\t\t\tPrints detailed list of users");
            if (admin) System.out.println("listusersbygroup\tPrints detailed list of groups and the users in them");
            System.out.println("shutdown\t\t\tShuts down the system");
            System.out.println("switchuser\t\t\tSwitch user");
        }

        User waitForAction(User user) {
            boolean admin = user.isAdmin();

            System.out.print(user.getUsername() + "@test:~$ ");
            String input = sc.next();

            switch (input) {
                case "addusertogroup":
                    if (admin) prepareAddUserToGroup();
                    else unknowncommand();
                    break;
                case "changeuserpassword":
                    if (admin) changeuserpassword();
                    else unknowncommand();
                    break;
                case "changepassword":
                    changepassword(user);
                    break;
                case "creategroup":
                    if (admin) groups.add(createGroup());
                    else unknowncommand();
                    break;
                case "createuser":
                    if (admin) users.add(createUser());
                    else unknowncommand();
                    break;
                case "groups":
                    user.printGroupsOfUser();
                    break;
                case "help":
                    printAvailableCommands(user);
                    break;
                case "listgroups":
                    if (admin) printGroups();
                    else unknowncommand();
                    break;
                case "listgroupsbyuser":
                    if (admin) printGroupsByUser();
                    else unknowncommand();
                    break;
                case "listusers":
                    if (admin) printUsers();
                    else unknowncommand();
                    break;
                case "listusersbygroup":
                    if (admin) printUsersByGroup();
                    else unknowncommand();
                    break;
                case "shutdown":
                    System.out.println("Shutting down...");
                    return null;
                case "switchuser":
                    User switched = userLogin(user);
                    if (switched != null) return switched;
                    break;
                default:
                    unknowncommand();
            }

            return user;
        }

        private void unknowncommand() {
            System.out.println("Command not recognised. Enter \"help\" for a list of available commands");
        }

    }

    private static void waitForEnter() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Setting "OS"
        UserSystem os = new UserSystem();
        User currentUser = os.getUsers().get(0); // root
        boolean running = true;

        while (running) {
            currentUser = os.waitForAction(currentUser);
            running = (currentUser != null);
            System.out.println();
        }
    }

    /* Old test main */
    public static void exampleMain() {
        Scanner sc = new Scanner(System.in);

        /* Create a user and add it to a group */
        System.out.println("Create new models.User."); // Alexandre Hyvernaud
        System.out.print("First name? ");
        String firstName = sc.next();
        System.out.print("Last name? ");
        String lastName = sc.next();

        User user1 = new User(firstName, lastName);
        System.out.println("Created user " + user1.getUsername() + " (" + user1.getName() + ")");
        System.out.println("Password is " + user1.getPassword() + ", remember it!");

        Group etna = new Group("ETNA");
        etna.getUsers().add(user1);
        user1.addGroup(etna);
        System.out.println("Added user " + user1.getUsername() + " to group " + etna.getName());
        waitForEnter();

        /* Create another user and add it to the same group */
        System.out.println("Create new models.User."); // Hilda Paredes
        System.out.print("First name? ");
        firstName = sc.next();
        System.out.print("Last name? ");
        lastName = sc.next();

        User user2 = new User(firstName, lastName);
        System.out.println("Created user " + user2.getUsername() + " (" + user2.getName() + ")");

        etna.getUsers().add(user2);
        user2.addGroup(etna);
        System.out.println("Added user " + user2.getUsername() + " to group " + etna.getName());
        waitForEnter();

        /* Create a new group and add user1 to it */
        Group mappy = new Group("Mappy");
        mappy.getUsers().add(user1);
        user1.addGroup(mappy);
        System.out.println("Added user " + user1.getUsername() + " to group " + mappy.getName());
        waitForEnter();

        /* Print users in each group */
        System.out.println("Users in group " + etna.getName());
        for (int i = 0; i < etna.getUsers().size(); i++) {
            System.out.println(etna.getUsers().get(i).getName());
        }
        System.out.println();
        System.out.println("Users in group " + mappy.getName());
        for (int i = 0; i < mappy.getUsers().size(); i++) {
            System.out.println(mappy.getUsers().get(i).getName());
        }
        waitForEnter();

        /* Print groups for each user */
        System.out.println("Groups of user " + user1.getName());
        for (int i = 0; i < user1.getGroups().size(); i++) {
            System.out.println(user1.getGroups().get(i).getName());
        }
        System.out.println();
        System.out.println("Groups of user " + user2.getName());
        for (int i = 0; i < user2.getGroups().size(); i++) {
            System.out.println(user2.getGroups().get(i).getName());
        }
        waitForEnter();

        /* Try logging in with password */
        boolean logged = false;
        while (!logged) {
            System.out.print("Enter password for hyvern_a");
            String pwd = sc.next();
            if (pwd.equals(user1.getPassword())) {
                System.out.println("Successfully logged in.");
                logged = true;
            } else
                System.out.println("Wrong password.");
        }
    }
}
