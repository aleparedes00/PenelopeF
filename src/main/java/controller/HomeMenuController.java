package controller;

import jdk.nashorn.internal.ir.PropertyKey;
import models.FSListenable;
import models.FSListener;
import models.Project;
import models.User;
import repository.ProjectRepository;
import repository.RepositoryManager;
import views.HomeMenuView;
import views.PenelopeF;
import views.PrintTools;
import views.ProjectView;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;
import static tools.MenuTools.showMenu;
import static views.PenelopeF.activeUser;
import static views.PenelopeF.defaultProjectsPath;


public class HomeMenuController {
    private final User user;
    private final RepositoryManager repositories;
    private HomeMenuView homeMenuView = new HomeMenuView();

    /*Constructor*/
    public HomeMenuController(User user, RepositoryManager repositories) {
        this.user = user;
        this.repositories = repositories;
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
        //TODO call function to write the historic "new feed" file
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
}
