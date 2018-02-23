package views;

import controller.HomeMenuController;
import controller.LoginController;
import models.*;
import repository.ProjectRepository;
import repository.RepositoryManager;
import tools.Serializer;
import views.LoginView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
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
        //TODO make a method run() to create the login
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

        createNewTestData(systemData);
        repositories.saveData();

        // Call Home Menu
        HomeMenuController homeMenuController = new HomeMenuController(activeUser, repositories);
        homeMenuController.firstMenuControl();

        /*//TODO change this function to HomeMenuController. Il faut etablir qu'est-ce que projectManager va vraiment prendre en parametre IF there is no project on User, the menu should be only create project. GROUP will connect to the Project Manager by projetc group attribute.*/

    }

    public static void createNewTestData(SystemData os) { // Ugly, awkward test method to create users, groups etc. and test serialisation. Run, stop, run again, observe
        Scanner sc = new Scanner(System.in);

        Group dev = os.getUserSystem().getGroupFromName("dev");
        if (dev == null) dev = new Group("dev");

        os.getUserSystem().getGroups().put(dev.getId(), dev);
        System.out.print("First name? ");
        String firstName = sc.next();
        System.out.print("Last name? ");
        String lastName = sc.next();
        User user = new User(firstName, lastName);
        System.out.println("Created user " + user.getUsername() + " (" + user.getName() + ")");
        System.out.println("Password is " + user.getPassword() + ", remember it!");

        Group userSelfGroup = user.getGroups().get(0);

        user.getGroupsIds().add(dev.getId());
        dev.getUsersIds().add(user.getId());

        os.getUserSystem().getUsers().put(user.getId(), user);
        os.getUserSystem().getGroups().put(userSelfGroup.getId(), userSelfGroup);
        os.getUserSystem().getGroups().put(dev.getId(), dev);

        if (os.getProjects().isEmpty()) {
            Project testProject = new Project("Test Project", dev, now(), Priority.NORMAL);
            os.loadProjectInMap(testProject);
            for (UUID userId : testProject.getGroup().getUsersIds()) {
                os.getUserFromId(userId).getProjectsIds().add(testProject.getId());
            }
        }
    }
}
