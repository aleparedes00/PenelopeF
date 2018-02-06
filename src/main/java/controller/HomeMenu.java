package controller;

import models.Project;
import models.User;
import repository.ProjectRepository;
import views.HomeMenuView;
import views.PrintTools;
import views.ProjectManagerView;

import static java.lang.Boolean.TRUE;
import static tools.MenuTools.showMenu;


public class HomeMenu {

    public final User user;
    public final ProjectRepository repository;
    HomeMenuView homeMenuView = new HomeMenuView();

    /*Constructor*/
    public HomeMenu(User user, ProjectRepository repository) {
        this.user = user;
        this.repository = repository;
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
                    Project project = homeMenuView.createProject();
                    user.addProject(project);
                    repository.createNew(project);
                    break;
                case LIST_PROJECTS:
                    if (!(user.getProjects().isEmpty())) {
                        int projectIndex = homeMenuView.showAndSelectProject(user);
                        if (projectIndex != -1) {
                            ProjectManagerView projectManagerView = new ProjectManagerView();
                            ProjectManager projectManager = new ProjectManager(projectManagerView, user, user.getProjects().get(projectIndex));
                            projectManager.showProject();
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
}
