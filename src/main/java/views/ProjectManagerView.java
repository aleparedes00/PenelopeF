package views;

import models.Project;
import views.menus.ModifyProjectMenu;
import views.menus.ProjectElements;

import java.util.ArrayList;

import static tools.ScannerTools.scanInt;
import static views.menus.ProjectElements.*;

/**
 * Created by alejandraparedes on 1/21/18.
 */
public class ProjectManagerView {

    /* Get available commands depending on project */
    ArrayList<ProjectElements> getAvailableCommands(Project project) {
        ArrayList<ProjectElements> availableCommands = new ArrayList<>();

        if (!project.getProjectTasks().isEmpty()) availableCommands.add(TASK);
        if (!project.getFiles().isEmpty()) availableCommands.add(DOCUMENT);
        availableCommands.add(GROUP);
        availableCommands.add(DASHBOARD);
        availableCommands.add(MODIFY);
        availableCommands.add(DEACTIVATE);
        availableCommands.add(BACK);

        return availableCommands;
    }


    /*Print Project Information*/
    public ProjectElements drawPrintProject(Project project) {
        System.out.println("Project Name: " + project.getNameOfProject());
        ArrayList<ProjectElements> availableCommands = getAvailableCommands(project);

        for (int i = 0; i < availableCommands.size(); i++) {
            System.out.println((i+1) + ".-" + availableCommands.get(i));
        }

        return availableCommands.get(scanInt(1, availableCommands.size()) - 1);

//        System.out.println("1.-" + TASK + ":");
//        project.getProjectTasks().forEach(task -> System.out.println(task.getTitle()));
//        System.out.println("2.-" + ProjectElements.DOCUMENT + ":");
//        project.getFiles().forEach(document -> System.out.println(document.getNameOfDoc()));
//        System.out.println("3.-" + ProjectElements.GROUP + ":" + project.getGroup().getName());
//        System.out.println("4.-" + ProjectElements.DASHBOARD + ":");
//        System.out.println("5.-" + ProjectElements.MODIFY);
//        System.out.println("6.-" + ProjectElements.DEACTIVATE);
//        System.out.println("7.-" + ProjectElements.BACK);
//        System.out.println("What would you like to do? Please choose a valid number (1-7)");
//        return ProjectElements.valueOf(scanInt(1, 7));
    }


    public ModifyProjectMenu modifyProjectMenu(Project project) {
        System.out.println("What would you like to modify?");
        System.out.println("1.-" + ModifyProjectMenu.PROJECT_NAME + ":");
        System.out.println("2.-" + ModifyProjectMenu.GROUP + ":");
        System.out.println("3.-" + ModifyProjectMenu.BACK + ".");
        System.out.println("What would you like to do?");
        return ModifyProjectMenu.valueOf(scanInt(1, 3));
    }

}
