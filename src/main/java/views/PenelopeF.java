package views;

import controller.GroupsMenuController;
import controller.HomeMenuController;
import controller.LoginController;
import controller.UserSystemController;
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

    public static void main(String[] args) {
        // Initialize Application
        SystemData systemData = new SystemData();
        RepositoryManager repositories = new RepositoryManager(systemData);
        User activeUser = null;

        // Load Data
        repositories.loadData();

        // Login Screen
        systemData.initializeUserSystem();
        Login loginModel = new Login(systemData);
        LoginView loginView = new LoginView(loginModel);
        LoginController loginController = new LoginController(loginModel, loginView);
        while (activeUser == null) {
            activeUser = loginController.userLogin();
        }

        // Build Active User
        repositories.loadActiveUserProjects(activeUser);

        // Test: Create User and Project Data
        createNewTestData(repositories);

        // Test: Groups Menu
        //GroupsMenuController groupsMenuController = new GroupsMenuController(activeUser, repositories.getSystemData());
        //groupsMenuController.showGroups();

        // Call Home Menu
        //HomeMenuController homeMenuController = new HomeMenuController(activeUser, repositories);
        //homeMenuController.firstMenuControl();

        repositories.saveData();

    }

    public static void createNewTestData(RepositoryManager repositories) { // Ugly, awkward test method to create users, groups etc. and test serialisation. Run, stop, run again, observe
        Scanner sc = new Scanner(System.in);
        SystemData systemData = repositories.getSystemData();
        UserSystem userSystem = systemData.getUserSystem();
        UserSystemController os = new UserSystemController(userSystem, new UserSystemView(userSystem));

        System.out.println("Create new user? Y/N");
        if (tools.ScannerTools.scanString().toUpperCase().equals("Y"))
            os.createUser();

        System.out.println("Create new group? Y/N");
        if (tools.ScannerTools.scanString().toUpperCase().equals("Y"))
            os.createGroup();

        System.out.println("Add a user to a previous group? Y/N");
        if (tools.ScannerTools.scanString().toUpperCase().equals("Y"))
            os.prepareAddUserToGroup();

        System.out.println("Create new test project? Y/N");
        if (tools.ScannerTools.scanString().toUpperCase().equals("Y")) {
            String newProjectName = "Test Project " + (systemData.getProjects().size() + 1);
            System.out.println("Which group?");
            Group group;
            while ((group = userSystem.getGroupFromName(tools.ScannerTools.scanString())) == null)
                System.out.println("Not found, which group?");

            Project testProject = new Project(newProjectName, group, now(), Priority.NORMAL);
            systemData.loadProjectInMap(testProject);
            for (UUID userId : testProject.getGroup().getUsersIds()) {
                systemData.getUserFromId(userId).getProjectsIds().add(testProject.getId());
            }
        }

        // Save data
        repositories.saveData();
    }
}
