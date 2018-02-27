package views;

import controller.*;
import models.*;
import repository.RepositoryManager;

import java.util.Scanner;
import java.util.UUID;

import static tools.DateTools.now;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public class PenelopeF { // executable main class
    public static String defaultProjectsPath = "projects";
    public final static String usersJson = "users.json";
    public final static String groupsJson = "groups.json";
    public final static String messagesJson = "messages.json";

    public static User activeUser;
    public static SystemData systemData;

    public static void main(String[] args) {
        // Initialize Application
        systemData = new SystemData();
        RepositoryManager repositories = new RepositoryManager(systemData);

        // Load Data
        repositories.loadData();
        systemData.initializeUserSystem();

        // Test: Create User and Project Data
        //createNewTestData(repositories);

        // Login Screen
        LoginController loginController = new LoginController(new LoginView());
        while (activeUser == null) {
            activeUser = loginController.userLogin();
        }

        // Build Active User
        repositories.loadActiveUserProjects(activeUser);

        if (!activeUser.isAdmin()) {
            // Test: Groups Menu
            //GroupsMenuController groupsMenuController = new GroupsMenuController(repositories.getSystemData());
            //groupsMenuController.showGroups();

            // Test: Projects Menu
            //HomeMenuController homeMenuController = new HomeMenuController(activeUser, repositories);
            //homeMenuController.firstMenuControl();

            // Test: Profile Menu
            ProfileMenuController profileMenuController = new ProfileMenuController(repositories);
            profileMenuController.showProfileMenu();
        } else {
            // Test: Admin Menu
            AdminMenuController adminMenuController = new AdminMenuController(repositories);
            adminMenuController.showAdminMenu();
        }

        // Save upon shutdown
        repositories.saveData();
    }

    public static void createNewTestData(RepositoryManager repositories) { // Ugly, awkward test method to create users, groups etc. and test serialisation. Run, stop, run again, observe
        Scanner sc = new Scanner(System.in);
        SystemData systemData = repositories.getSystemData();
        Admin admin = new Admin(systemData);
        AdminController os = new AdminController(admin, new AdminView());

        boolean creating = true;
        while (creating) {
            System.out.println("Create new user? Y/N");
            if (tools.ScannerTools.scanString().toUpperCase().equals("Y"))
                os.createUser();
            else creating = false;
        }

        creating = true;
        while (creating) {
            System.out.println("Create new group? Y/N");
            if (tools.ScannerTools.scanString().toUpperCase().equals("Y"))
                os.createGroup();
            else creating = false;
        }

        creating = true;
        while (creating) {
            System.out.println("Add a user to a previous group? Y/N");
            if (tools.ScannerTools.scanString().toUpperCase().equals("Y"))
                os.prepareAddUserToGroup();
            else creating = false;
        }

        creating = true;
        while (creating) {
            System.out.println("Create new test project? Y/N");
            if (tools.ScannerTools.scanString().toUpperCase().equals("Y")) {
                String newProjectName = "Test Project " + (systemData.getProjects().size() + 1);
                System.out.println("Which group?");
                Group group;
                while ((group = tools.DataTools.getGroupFromName(tools.ScannerTools.scanString())) == null)
                    System.out.println("Not found, which group?");

                Project testProject = new Project(newProjectName, group, now(), Priority.NORMAL);
                repositories.createNewProject(testProject);
                for (UUID userId : testProject.getGroup().getUsersIds()) {
                    tools.DataTools.getUserFromId(userId).getProjectsIds().add(testProject.getId());
                }

                System.out.println("Created " + newProjectName);
            }
            else creating = false;
        }

        // Save data
        repositories.saveData();
    }
}
