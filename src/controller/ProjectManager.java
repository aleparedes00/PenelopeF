package controller;

import models.Project;
import models.User;
import views.ProjectView;

import java.util.Scanner;

import static java.lang.Boolean.TRUE;
import static tools.MenuTools.showMenu;

/**
 * Created by alejandraparedes on 1/21/18.
 */

@SuppressWarnings("SpellCheckingInspection")
public class ProjectManager {

    public final ProjectView projectView;
    public User user;

    /*Constructor*/
    public ProjectManager(ProjectView projectView, User user) {
        this.projectView = projectView;
        this.user = user;

        }

    public void showProject(int projectIndex) {
        showMenu(ctx -> {
            switch (this.projectView.drawPrintProject(this.user.getProjects().get(projectIndex))) {
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
                    controlModifyProject(this.user.getProjects().get(projectIndex));
                    break;
                case DEACTIVATE:
                    System.out.println("Deactivating the project");
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
                    project.setNameOfProject(this.projectView.printStringAndReadChoice("Please, enter the new name of the project:"));
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

}
