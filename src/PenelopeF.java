import controller.LoginController;
import controller.ProjectManager;
import models.Login;
import models.SystemData;
import models.TestData;
import models.User;
import views.LoginView;
import views.ProjectView;

import static java.lang.Boolean.TRUE;
import static tools.MenuTools.showMenu;

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

        showMenu((ctx) -> {
            switch (projectManager.projectView.showAndSelectHome()) {
                case CREATE_PROJECT:
                    projectManager.user.addProject(projectManager.projectView.createProject());
                    break;
                case LIST_PROJECTS:
                    int projectIndex = projectManager.projectView.listProjects(projectManager.user);
                    projectManager.projectView.drawPrintProject(projectManager.user.getProjects().get(projectIndex - 1));
                    break;
                case LOGOUT:
                    ctx.leaveCurrentMenu = TRUE;
                    break;
            }
        });

        // Admin Screen
        //ALE: Tu veux mettre quoi dans cet écran ?
        // ...

    }
}
