package com.penelopef.controller;

import com.penelopef.models.Project;
import com.penelopef.views.*;

import static com.penelopef.models.Priority.selectPriority;
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
            switch (this.projectView.drawProject()) {
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
                    System.out.println("[Dashboard] : Calling freaking Dashboard");
                    break;
                case EDIT:
                    controlModifyProject(project);
                    //TODO saveData
                    break;
                case DEACTIVATE:
                    controlDeactiveProject(project);
                    ctx.leaveCurrentMenu = true;
                    break;
                case BACK:
                    ctx.leaveCurrentMenu = true;
                    break;
            }
        });
    }

    private void controlModifyProject(Project project) {
        showMenu(ctx -> {
            switch (this.projectView.modifyProjectMenu()) {
                case PROJECT_NAME:
                    project.setName(PrintTools.printStringAndReadChoice("Please, enter the new name of the project:"));
                    ctx.leaveCurrentMenu = true;
                    break;
                case PRIORITY:
                    project.setPriority(selectPriority());
                case GROUP:
                    System.out.println("Changing group");
                    ctx.leaveCurrentMenu = true;
                    break;
                case BACK:
                    ctx.leaveCurrentMenu = true;
            }
        });
    }


    private void controlDeactiveProject(Project project) {
        project.setActive(false);
        PrintTools.printString("\nYour project has been successfully deactived\n");
    }
}
