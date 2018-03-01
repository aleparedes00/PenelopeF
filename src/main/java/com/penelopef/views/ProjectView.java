package com.penelopef.views;

import com.penelopef.models.Project;
import com.penelopef.views.menus.ModifyProjectMenu;
import com.penelopef.views.menus.ProjectElements;

import java.util.ArrayList;

import static com.penelopef.tools.ScannerTools.scanInt;
import static com.penelopef.views.menus.ProjectElements.*;

/**
 * Created by alejandraparedes on 1/21/18.
 */
public class ProjectView {

    /* Get available commands depending on project */
    private ArrayList<ProjectElements> getAvailableCommands() {
        ArrayList<ProjectElements> availableCommands = new ArrayList<>();

        availableCommands.add(PROJECT_INFO);
        availableCommands.add(TASK);
        //TODO: dont show Documents of File file = new File (project.pathTOFolder return null
        availableCommands.add(DOCUMENT);
        availableCommands.add(DASHBOARD);
        availableCommands.add(EDIT);
        availableCommands.add(DEACTIVATE);
        availableCommands.add(BACK);

        return availableCommands;
    }


    /*Print Project Information*/
    public ProjectElements drawProject() {
        ArrayList<ProjectElements> availableCommands = getAvailableCommands();

        for (int i = 0; i < availableCommands.size(); i++) {
            System.out.println((i + 1) + ".- " + availableCommands.get(i));
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
    public ModifyProjectMenu modifyProjectMenu() {
        System.out.println("What would you like to edit?");
        System.out.println("1.- " + ModifyProjectMenu.PROJECT_NAME + ":");
        System.out.println("2.- " + ModifyProjectMenu.GROUP + ":");
        System.out.println("3.- " + ModifyProjectMenu.BACK + ".");
        //TODO add change priority
        System.out.println("What would you like to do?");
        return ModifyProjectMenu.valueOf(scanInt(1, 3));
    }

    public void drawProjectDocuments(Project project) {
        if (project.getDocuments().isEmpty()) {
            System.out.println("-> No documents!!");
            return;
        }
        System.out.println("List of documents:");
        project.getDocuments().forEach(d -> System.out.println("- " + d));
    }
}
