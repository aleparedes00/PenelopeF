package com.penelopef.controller;

import com.penelopef.models.FSListenable;
import com.penelopef.models.Group;
import com.penelopef.models.Project;
import com.penelopef.views.*;

import java.io.File;
import java.util.UUID;

import static com.penelopef.PenelopeF.activeUser;
import static com.penelopef.PenelopeF.defaultProjectsPath;
import static com.penelopef.PenelopeF.getRepositories;
import static com.penelopef.models.Priority.selectPriority;
import static com.penelopef.tools.DataTools.getUserFromId;
import static com.penelopef.tools.MenuTools.showMenu;

/**
 * Created by alejandraparedes on 1/21/18.
 */

public class ProjectController {

    private final ProjectView projectView;
    private Project project;

    /*Constructor*/
    public ProjectController(ProjectView projectView, Project project) {
        this.projectView = projectView;
        this.project = project;
    }

    void showProject() {
        showMenu(ctx -> {
            switch (this.projectView.drawProject(project)) {
                case PROJECT_INFO:
                    projectView.drawProjectInformation(project);
                    break;
                case TASK:
                    TaskView taskView = new TaskView();
                    TaskController taskController = new TaskController(taskView, project);
                    taskController.showTasks();
                    break;
                case DOCUMENT:
                    projectView.drawProjectDocuments(project);
                    break;
                case DASHBOARD:
                    DashboardView dashboardView = new DashboardView(project.getDashboard().getMessagesIds());
                    DashboardController dashboardController = new DashboardController(dashboardView, project);
                    dashboardController.showDashboard();
                    break;
                case EDIT:
                    controlModifyProject();
                    break;
                case DEACTIVATE:
                    controlDeactiveProject();
                    ctx.leaveCurrentMenu = true;
                    break;
                case BACK:
                    ctx.leaveCurrentMenu = true;
                    break;
            }
        });
    }

    private void controlModifyProject() {
        showMenu(ctx -> {
            switch (this.projectView.modifyProjectMenu()) {
                case PROJECT_NAME:
                    String newName = projectView.editName();
                    changeName(newName);
                    getRepositories().saveData();
                    projectView.modificationSaved();
                    project.getDashboard().createNewSystemMessage(activeUser.getName() + " has modified the name of the project.\nProject name: " + project.getName());
                    break;
                case PRIORITY:
                    project.setPriority(selectPriority());
                    getRepositories().saveData();
                    projectView.modificationSaved();
                    project.getDashboard().createNewSystemMessage("The priority has been changed for -> " + project.getPriority().toString());
                    break;
                case GROUP:
                    Group newGroup = projectView.editGroup();
                    if (newGroup != null) {
                        changeGroup(newGroup);
                        getRepositories().saveData();
                        projectView.modificationSaved();
                    }
                    project.getDashboard().createNewSystemMessage("The group of the project has been modified for -> " + project.getGroup().getName());
                    break;
                case BACK:
                    ctx.leaveCurrentMenu = true;
            }
        });
    }

    private void changeName(String newName) {
        // Change Project name
        project.setName(newName);

        // Change Project directory
        if (projectView.alsoChangeDirectoryName()) {
            String newDirectoryName = defaultProjectsPath + newName;

            File projectDirectory = new File(project.getPathToProject());
            File newDirectory = new File(newDirectoryName);

            if (newDirectory.exists())
                projectView.errorDirectoryExists(newDirectoryName);
            else {
                if (!projectDirectory.exists()) projectDirectory.mkdir();
                projectDirectory.renameTo(newDirectory);

                // Update listeners
                FSListenable.removeListener(project, new File(project.getPathToProject()).toPath());
                FSListenable.addListener(project, new File(newDirectoryName).toPath());

                project.setPathToProject(defaultProjectsPath + newName);
            }
        }
    }

    private void changeGroup(Group newGroup) {
        // Remove Project from previous owners
        for (UUID userId : project.getGroup().getUsersIds()) {
            getUserFromId(userId).removeProject(project);
        }

        // Set new Group ID
        project.setGroupId(newGroup.getId());

        // Add Project to new owners
        for (UUID userId : project.getGroup().getUsersIds()) {
            getUserFromId(userId).addProject(project);
        }
    }

    private void controlDeactiveProject() {
        project.setActive(false);
        getRepositories().saveData();
        PrintTools.printString("\nYour project has been successfully deactivated\n");
    }
}
