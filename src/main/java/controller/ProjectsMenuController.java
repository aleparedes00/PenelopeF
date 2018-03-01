package controller;

import models.Project;
import repository.RepositoryManager;
import views.ProjectsMenuView;
import views.ProjectView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static tools.MenuTools.showMenu;
import static views.PenelopeF.activeUser;


public class ProjectsMenuController {
    private final RepositoryManager repositories;
    private ProjectsMenuView projectsMenuView = new ProjectsMenuView();

    /*Constructor*/
    public ProjectsMenuController(RepositoryManager repositories) {
        this.repositories = repositories;
    }

    public void showProjects() {
        showMenu((ctx) -> {
            List<Project> activeProjects = activeUser.getProjects()
                    .stream()
                    .filter(Project::isActive)
                    .collect(Collectors.toList());
            activeProjects.sort(Comparator.comparing(Project::getDate));
            int selectedProjectIndex = projectsMenuView.showAndSelectProject(activeProjects);
            if (selectedProjectIndex < activeProjects.size()) {
                ProjectView projectView = new ProjectView();
                ProjectController projectController = new ProjectController(projectView, activeProjects.get(selectedProjectIndex));
                projectController.showProject();
            } else if (selectedProjectIndex == activeProjects.size())
                createProject();
            else ctx.leaveCurrentMenu = true;
        });
    }

    public void createProject() {
        Project project = projectsMenuView.createProject();
        activeUser.addProject(project);
        repositories.createNewProject(project);
        repositories.saveData();
        //TODO call function to write the historic "new feed" file
    }
}
