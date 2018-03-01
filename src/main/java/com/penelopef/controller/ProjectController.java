package com.penelopef.controller;

import com.penelopef.models.Project;
import com.penelopef.views.*;

import static com.penelopef.models.Priority.selectPriority;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static com.penelopef.tools.MenuTools.showMenu;

/**
 * Created by alejandraparedes on 1/21/18.
 */
//This controller is listener and listenable.
//It's listener because it wants to update its view/data throgh the FS (using repository) everytime someone changes something inside.
// It's listenable because the model(data) that will be updated are managed will be watched (to be able to update other views and etc)
@SuppressWarnings("SpellCheckingInspection")

public class ProjectController {

    private final ProjectView projectView;
    private Project project;
    //private FSListenable listenable;

    /*Constructor*/
    public ProjectController(ProjectView projectView, Project project) {
        this.projectView = projectView;
        this.project = project;
        //this.listenable = new FSListenable(new File(project.getPathToProject()).toPath(), this);
    }

    public void showProject() {
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
                case MODIFY:
                    controlModifyProject(project);
                    //TODO saveData
                    break;
                case DEACTIVATE:
                    controlDeactiveProject(project);
                    ctx.leaveCurrentMenu = TRUE;
                    break;
                case BACK:
                    ctx.leaveCurrentMenu = TRUE;
                    break;
            }
        });
    }

    private void controlModifyProject(Project project) {
        showMenu(ctx -> {
            switch (this.projectView.modifyProjectMenu(project)) {
                case PROJECT_NAME:
                    project.setName(PrintTools.printStringAndReadChoice("Please, enter the new name of the project:"));
                    ctx.leaveCurrentMenu = TRUE;
                    break;
                case PRIORITY:
                    project.setPriority(selectPriority());
                case GROUP:
                    System.out.println("Changing group");
                    ctx.leaveCurrentMenu = TRUE;
                    break;
                case BACK:
                    ctx.leaveCurrentMenu = TRUE;
            }
        });
    }


    private void controlDeactiveProject(Project project) {
        //this.user.addDeactiveProject(project);
        project.setActive(FALSE);
        PrintTools.printString("\nYour project has been successfully deactived\n");
    }
}
