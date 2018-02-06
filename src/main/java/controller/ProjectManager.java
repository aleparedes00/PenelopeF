package controller;

import models.Project;
import models.User;
import views.PrintTools;
import views.ProjectManagerView;

import static java.lang.Boolean.TRUE;
import static tools.MenuTools.showMenu;

/**
 * Created by alejandraparedes on 1/21/18.
 */

@SuppressWarnings("SpellCheckingInspection")
public class ProjectManager {

    public final ProjectManagerView projectManagerView;
    public User user;
    public Project project;

    /*Constructor*/
    public ProjectManager(ProjectManagerView projectManagerView, User user, Project project) {
        this.projectManagerView = projectManagerView;
        this.project = project;
        this.user = user;
    }

    public void showProject() {
        showMenu(ctx -> {
            switch (this.projectManagerView.drawPrintProject(project)) {
                case TASK:
                    System.out.println("More information about Task");
                    break;
                case DOCUMENT:
                    System.out.println("[Documents]: more information");
                    break;
                case GROUP:
                    System.out.println("[Group] : changes? Modify? deactivate?");
                    break;
                case DASHBOARD:
                    System.out.println("[Dashboard] : Calling freaking Dashboard");
                    break;
                case MODIFY:
                    controlModifyProject(project);
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
            switch (this.projectManagerView.modifyProjectMenu(project)) {
                case PROJECT_NAME:
                    project.setNameOfProject(PrintTools.printStringAndReadChoice("Please, enter the new name of the project:"));
                    ctx.leaveCurrentMenu = TRUE;
                    break;
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
        this.user.addDeactiveProject(project);
        this.user.removeProject(project);
        PrintTools.printString("Your project has been successfully deactive");
    }

}
