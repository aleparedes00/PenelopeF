package com.penelopef.views;

import com.penelopef.models.Group;
import com.penelopef.models.Project;
import com.penelopef.views.menus.ModifyProjectMenu;
import com.penelopef.views.menus.ProjectElements;

import java.io.File;
import java.util.ArrayList;

import static com.penelopef.PenelopeF.activeUser;
import static com.penelopef.tools.DataTools.getGroupFromName;
import static com.penelopef.tools.ScannerTools.scanInt;
import static com.penelopef.tools.ScannerTools.scanString;
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
        availableCommands.add(DOCUMENT);
        availableCommands.add(DASHBOARD);
        availableCommands.add(EDIT);
        availableCommands.add(DEACTIVATE);
        availableCommands.add(BACK);

        return availableCommands;
    }


    /*Print Project Information*/
    public ProjectElements drawProject(Project project) {
        System.out.println("===== PROJECT " + project.getName() + " =====");

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
        System.out.println();
    }

    /*Print Project Modify*/
    public ModifyProjectMenu modifyProjectMenu() {
        System.out.println("What would you like to edit?");
        System.out.println("1.- " + ModifyProjectMenu.PROJECT_NAME);
        System.out.println("2.- " + ModifyProjectMenu.GROUP);
        System.out.println("3.- " + ModifyProjectMenu.PRIORITY);
        System.out.println("4.- " + ModifyProjectMenu.BACK);
        return ModifyProjectMenu.valueOf(scanInt(1, 4));
    }

    public void drawProjectDocuments(Project project) {
        System.out.println("===== FILES OF PROJECT " + project.getName() + " =====");

        File projectRoot = new File(project.getPathToProject());
        System.out.println("|_ " + projectRoot.getName());
        if (projectRoot.exists())
            drawFilesInDirectory(projectRoot, 1);

        System.out.println();
    }

    private void drawFilesInDirectory(File directory, int recursionLevel) {
        File[] files = directory.listFiles(file -> !file.isHidden());
        if (files != null) {
            for (File file : files) {
                printTreeBranch(recursionLevel);
                System.out.println(file.getName());
                if (file.isDirectory())
                    drawFilesInDirectory(file, recursionLevel + 1);
            }
        }

    }

    private void printTreeBranch(int recursionLevel) {
        for (int i = 0; i < recursionLevel; i++) {
            System.out.print("\t");
        }
        System.out.print("|_ ");
    }

    public String editName() {
        System.out.print("New name? ");
        return scanString();
    }

    public Group editGroup() {
        System.out.print("New group? ");
        Group newGroup = getGroupFromName(scanString());
        if (newGroup == null || !activeUser.getGroups().contains(newGroup)) {
            System.out.println("Group not found. (Make sure you belong to the group you're trying to select)");
            return null;
        } else return newGroup;
    }

    public void modificationSaved() {
        System.out.println("Modification saved.");
    }

    public boolean alsoChangeDirectoryName() {
        System.out.println("Would you like to also rename the project's folder? Y/N");
        return (scanString().toUpperCase().equals("Y"));
    }

    public void errorDirectoryExists(String name) {
        System.out.println("Error: directory " + name + " already exists");
    }
}
