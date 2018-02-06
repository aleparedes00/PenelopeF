import controller.HomeMenu;
import controller.LoginController;
import models.Login;
import models.SystemData;
import models.User;
import repository.ProjectRepository;
import views.LoginView;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public class PenelopeF { // executable main class
    public static void main(String[] args) {
        // Initialize Application
        //TODO make a method run() to create the login
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

        // Initialize repository
        ProjectRepository projectRepository = new ProjectRepository();
        projectRepository.initiateProgram();
        activeUser.setProjects(projectRepository.readAndLoadProjectArray());
        HomeMenu homeMenu = new HomeMenu(activeUser, projectRepository);
        homeMenu.firstMenuControl();
        // Project Screen

        /*//TODO change this function to HomeMenu. Il faut etablir qu'est-ce que projectManager va vraiment prendre en parametre IF there is no project on User, the menu should be only create project. GROUP will connect to the Project Manager by projetc group attribute.*/


        // Admin Screen
        //ALE: Tu veux mettre quoi dans cet écran ?
        // ...

    }
}