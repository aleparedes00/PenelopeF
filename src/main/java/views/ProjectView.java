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
public class ProjectView {

    /* Get available commands depending on project */
    private ArrayList<ProjectElements> getAvailableCommands(Project project) {
        ArrayList<ProjectElements> availableCommands = new ArrayList<>();

        availableCommands.add(PROJECT_INFO);
        if (!project.getTasks().isEmpty())
            availableCommands.add(TASK);
        //TODO: if the doc folder is empty?
        availableCommands.add(DOCUMENT);
        availableCommands.add(DASHBOARD);
        availableCommands.add(MODIFY);
        availableCommands.add(DEACTIVATE);
        availableCommands.add(BACK);

        return availableCommands;
    }


    /*Print Project Information*/
    public ProjectElements drawPrintProject(Project project) {
        ArrayList<ProjectElements> availableCommands = getAvailableCommands(project);

        for (int i = 0; i < availableCommands.size(); i++) {
            System.out.println((i+1) + ".-" + availableCommands.get(i));
        }

        return availableCommands.get(scanInt(1, availableCommands.size()) - 1);

    }

    /*Print ProjectInfo*/
    public void drawProjectInformation(Project project) {
        System.out.println("Name: " + project.getName());
        System.out.println("Group: " + project.getGroup().getName());
        System.out.println("Created on: " + project.getDate());
        System.out.println("Priority: " + project.getPriority().toString());
    }

    /*Print Project Modify*/
    public ModifyProjectMenu modifyProjectMenu(Project project) {
        System.out.println("What would you like to modify?");
        System.out.println("1.-" + ModifyProjectMenu.PROJECT_NAME + ":");
        System.out.println("2.-" + ModifyProjectMenu.GROUP + ":");
        System.out.println("3.-" + ModifyProjectMenu.BACK + ".");
        System.out.println("What would you like to do?");
        return ModifyProjectMenu.valueOf(scanInt(1, 3));
    }

}
