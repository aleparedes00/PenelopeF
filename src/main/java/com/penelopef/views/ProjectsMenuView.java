package com.penelopef.views;

import com.penelopef.models.*;
import com.penelopef.views.menus.ProjectHomeSelection;

import java.util.List;

import static com.penelopef.tools.DateTools.now;
import static com.penelopef.tools.ScannerTools.scanInt;
import static com.penelopef.tools.ScannerTools.scanString;
import static com.penelopef.views.PrintTools.printStringAndReadInteger;

public class ProjectsMenuView {
    //Equal to printAvailableCommands
    public ProjectHomeSelection showAndSelectHome() {
        System.out.println("Welcome to PenelopeF\nAvailable Commands");
        System.out.println("1.-" + ProjectHomeSelection.CREATE_PROJECT);
        System.out.println("2.-" + ProjectHomeSelection.LIST_PROJECTS);
        System.out.println("3.-" + ProjectHomeSelection.LOGOUT);
        return ProjectHomeSelection.valueOf(printStringAndReadInteger("Please select a number option between 1 and 3", 1, 3));
    }

    public Project createProject() {
        System.out.println("Welcome to the Project Manager.\nProject Name: ");
        String name = scanString();
        Priority priority = selectPriority();
        Project project = new Project(name, new Group("default"), now(), priority); // TODO: Group selection, from current user's list of groups
        System.out.println("New project created. Name: " + project.getName());
        //TODO System.out.println("Add:" + "\n1.-" + ProjectElements.TASK + "\n2.-" + ProjectElements.DOCUMENT + "\n3.-" + ProjectElements.GROUP);
        return project;
    }

    public static Priority selectPriority() {
        System.out.println("Please, select the priority of your project");
        System.out.println("1.-" + Priority.HIGH);
        System.out.println("2.-" + Priority.NORMAL);
        System.out.println("3.-" + Priority.LOW);
        return Priority.optionsPriority(printStringAndReadInteger("Enter a number between " + 1 + " and " + Priority.values().length, 1, 3));
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
    public static void main(String[] args) {
        ProjectsMenuView projectsMenuView = new ProjectsMenuView();
        Priority priority = projectsMenuView.selectPriority();
        System.out.println("This is the priority " + priority.toString());
    }
}
