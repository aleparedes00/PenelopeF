package com.penelopef.controller;

import com.penelopef.models.Project;
import com.penelopef.views.ProjectsMenuView;
import com.penelopef.views.ProjectView;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.penelopef.PenelopeF.getRepositories;
import static com.penelopef.tools.DataTools.getUserFromId;
import static com.penelopef.tools.MenuTools.showMenu;
import static com.penelopef.PenelopeF.activeUser;


class ProjectsMenuController {
    private ProjectsMenuView projectsMenuView = new ProjectsMenuView();

    void showProjects() {
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

    private void createProject() {
        Project project = projectsMenuView.createProject();
        if (project != null) {
            for (UUID userId : project.getGroup().getUsersIds()) {
                getUserFromId(userId).addProject(project);
            }
            getRepositories().createNewProject(project);
            getRepositories().saveData();
        }
        //TODO call function to write the historic "new feed" file
    }
}
