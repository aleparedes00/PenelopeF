package controller;

import models.FSListenable;
import models.FSListener;
import models.Project;
import models.User;
import views.HomeMenuView;
import views.PrintTools;
import views.ProjectView;
import views.TaskView;

import java.io.File;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static tools.MenuTools.showMenu;

/**
 * Created by alejandraparedes on 1/21/18.
 */
//This controller is listener and listenable.
//It's listener because it wants to update its view/data throgh the FS (using repository) everytime someone changes something inside.
// It's listenable because the model(data) that will be updated are managed will be watched (to be able to update other views and etc)
@SuppressWarnings("SpellCheckingInspection")

//TODO 1.- All controlleurs take repository     2.- every create, modify and delete function will call repository and write inside historicFile     3.- after both actions, listenable run() function ask FileSystem what changed (identify if it was create, modify or delete) and listener.onCreate() will deseliariser the file and update all list and attributes
public class ProjectController implements FSListener {

    private final ProjectView projectView;
    private User user;
    private Project project;
    //private FSListenable listenable;

    /*Constructor*/
    public ProjectController(ProjectView projectView, User user, Project project) {
        this.projectView = projectView;
        this.project = project;
        this.user = user;
        //this.listenable = new FSListenable(new File(project.getPathToProject()).toPath(), this);
    }

    public void showProject() {
        showMenu(ctx -> {
            switch (this.projectView.drawPrintProject()) {
                case PROJECT_INFO:
                    projectView.drawProjectInformation(project);
                    break;
                case TASK:
                    TaskView taskView = new TaskView();
                    TaskController taskController = new TaskController(taskView, project);
                    taskController.showListTasks();
                    break;
                case DOCUMENT:
                    System.out.println("[Documents]: Insert your file inside the folder " + project.getPathToProject());
                    break;
                case DASHBOARD:
                    System.out.println("[Dashboard] : Calling freaking Dashboard");
                    break;
                case MODIFY:
                    controlModifyProject(project);
                    //saveData
                    //listenable.run();
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
                    project.setPriority(HomeMenuView.selectPriority());
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

    @Override
    public void onCreate(String pathToNewFile) {
        System.out.println("I'm creting this" + pathToNewFile);
    }

    @Override
    public void onDelete(String pathToDeleteFile) {
        System.out.println("I'm deleating this" + pathToDeleteFile);
    }

    @Override
    public void onUpdate(String pathToUpdateFile) {
        System.out.println("I'm updating this" + pathToUpdateFile);
    }
}
