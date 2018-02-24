package controller;

import models.FSListener;
import models.Project;
import models.User;
import repository.ProjectRepository;
import repository.RepositoryManager;
import views.HomeMenuView;
import views.PrintTools;
import views.ProjectView;

import static java.lang.Boolean.TRUE;
import static tools.MenuTools.showMenu;


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
                //TODO change this to the way you show tasks
                case CREATE_PROJECT:
                    Project project = homeMenuView.createProject();
                    user.addProject(project);
                    repositories.createNewProject(project);
                    repositories.saveData();

                    break;
                case LIST_PROJECTS:
                    if (!(user.getProjects().isEmpty())) {
                        int projectIndex = homeMenuView.showAndSelectProject(user);
                        if (projectIndex != -1) {
                            ProjectView projectView = new ProjectView();
                            ProjectController projectController = new ProjectController(projectView, user, user.getProjects().get(projectIndex));
                            projectController.showProject();
                            //run();
                        }
                        else {
                            ctx.leaveCurrentMenu = TRUE; //TODO how to reprint this menu if I realise that the project I want isn't there or actually I need to create one OR just put a Create Project button
                        }
                    } else {
                        PrintTools.printString("No projects to show");
                    }
                    break;
                case LOGOUT:
                    ctx.leaveCurrentMenu = TRUE;
                    break;
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
