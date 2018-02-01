import controller.*;
import models.*;
import views.*;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public class PenelopeF { // executable main class
    public static void main(String[] args) {
        // Initialize Application
        SystemData os = new SystemData();
        User activeUser = null;

        // Login Screen
        Login loginModel = new Login(os.getUserSystem());
        LoginView loginView = new LoginView(loginModel);
        LoginController loginController = new LoginController(loginModel, loginView);
        while (activeUser == null) {
            activeUser = loginController.userLogin();
        }

        // Home Screen
        // 1 - Users/Groups?
        // 2 - Projects Screen
        // 3 - Admin Screen

        // Project Screen
        ProjectManager projectManager = new ProjectManager(new ProjectView(), TestData.project1_2());

        switch (projectManager.projectView.showAndSelectHome()) {
            case CREATE_PROJECT:
                projectManager.user.addProject(projectManager.projectView.createProject());
                break;
            case LIST_PROJECTS:
                int projectIndex = projectManager.projectView.listProjects(projectManager.user);
                projectManager.projectView.drawPrintProject(projectManager.user.getProjects().get(projectIndex - 1));
                break;
        }

        // Admin Screen
        // ...




        //proj.projectView.drawPrintProject(project1);
    }
}
