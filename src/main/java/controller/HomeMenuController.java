package controller;

import models.FSListener;
import models.Project;
import models.User;
import repository.ProjectRepository;
import repository.RepositoryManager;
import views.HomeMenuView;
import views.PrintTools;
import views.ProjectView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;
import static tools.MenuTools.showMenu;
import static views.PenelopeF.activeUser;


public class HomeMenuController implements FSListener {
    //como declarar esto como un listener también
    private final User user;
    private final RepositoryManager repositories;
    private HomeMenuView homeMenuView = new HomeMenuView();
    //private FSListenable listenable;

    /*Constructor*/
    public HomeMenuController(User user, RepositoryManager repositories) {
        this.user = user;
        this.repositories = repositories;
        //listenable = new FSListenable(repositories.getPath(), );
    }

    /*Getters*/
    public User getUser() {
        return user;
    }

    //AFTER LOGIN or even the login could be here
    public void firstMenuControl() {
        showMenu((ctx) -> {
            switch (homeMenuView.showAndSelectHome()) {
                case CREATE_PROJECT:
                    createProject();
                    /*Project project = homeMenuView.createProject();
                    user.addProject(project);
                    repositories.createNewProject(project);
                    repositories.saveData();
*/
                    break;
                case LIST_PROJECTS:
                    listProjects();
                    break;
                case LOGOUT:
                    ctx.leaveCurrentMenu = TRUE;
                    break;
            }
        });
    }

    public void createProject() {
        Project project = homeMenuView.createProject();
        activeUser.addProject(project);
        repositories.createNewProject(project);
        repositories.saveData();
    }

    public void listProjects() {
        showMenu(ctx -> {
            List<Project> activeProjects = activeUser.getProjects()
                    .stream()
                    .filter(Project::isActive)
                    .collect(Collectors.toList());
            Integer userInput = this.homeMenuView.showAndSelectProject(activeProjects);
            if (userInput == activeProjects.size()) {
                this.createProject();
            } else if (userInput >= activeProjects.size()) {
                ctx.leaveCurrentMenu = TRUE;
            } else {
                int i = 0;
                for (Project project : activeUser.getProjects()) {
                    if (project.isActive()) {
                        if (i == userInput) {
                            ProjectView projectView = new ProjectView();
                            ProjectController projectController = new ProjectController(projectView, activeUser, activeUser.getProjects().get(userInput));
                            projectController.showProject();
                            break;
                        }
                        i++;
                    }
                }
            }
        });
    }

    @Override
    public void onCreate(String pathToNewFile) {
        System.out.println("Creating");
    }

    @Override
    public void onDelete(String pathToDeleteFile) {
        System.out.println("deleting");
    }

    @Override
    public void onUpdate(String pathToUpdateFile) {
        System.out.println("updating");
    }
}
