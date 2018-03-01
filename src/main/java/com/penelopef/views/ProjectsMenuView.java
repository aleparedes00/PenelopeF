package com.penelopef.views;

import com.penelopef.models.*;
import com.penelopef.views.menus.ProjectHomeSelection;

import java.util.List;

import static com.penelopef.PenelopeF.activeUser;
import static com.penelopef.models.Priority.selectPriority;
import static com.penelopef.tools.DataTools.getGroupFromName;
import static com.penelopef.tools.DateTools.now;
import static com.penelopef.tools.ScannerTools.scanInt;
import static com.penelopef.tools.ScannerTools.scanString;

public class ProjectsMenuView {

    public Project createProject() {
        // Enter name
        System.out.print("Project Name: ");
        String name = scanString();

        // Select priority
        Priority priority = selectPriority();

        // Select group
        Group group = selectGroup();

        if (group != null) {
            // Create project
            Project project = new Project(name, group, now(), priority);
            System.out.println("New project created : " + project.getName());
            return project;
        } else return null;
    }

    private Group selectGroup() {
        System.out.print("Which group? ");
            Group groupToBeAddedTo = getGroupFromName(scanString());
            if (groupToBeAddedTo == null || !activeUser.getGroups().contains(groupToBeAddedTo)) {
                System.out.println("Group not found. (Make sure you belong to the group you're trying to select)");
                return null;
            }
            return groupToBeAddedTo;
    }

    public int showAndSelectProject(List<Project> projects) {
        System.out.println("===== PROJECTS =====");

        int i = 1;
        for (Project project : projects) {
            System.out.println(i++ + ".- " + project.getName());
        }
        System.out.println(i++ + ".- Create new project");
        System.out.println(i + ".- Back");

        int userInput = scanInt(1, i);
        return (userInput - 1);
    }
}
