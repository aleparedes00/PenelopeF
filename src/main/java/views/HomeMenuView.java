package views;

import models.*;
import views.menus.ProjectHomeSelection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static tools.DateTools.now;
import static tools.ScannerTools.scanInt;
import static tools.ScannerTools.scanString;
import static views.PenelopeF.activeUser;
import static views.PrintTools.printStringAndReadInteger;

public class HomeMenuView {
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
        int i = 1;
        System.out.println("User: " + activeUser.getName() + "\n\n Projects:");
        for (Project project : projects) {
            System.out.println(i++ + ".- " + project.getName());
        }
        System.out.println(i++ + ".- Create new project");
        System.out.println(i + ".- Back");

        Integer userInput = printStringAndReadInteger("Please, select the project you would like to see or create one", 1, i);
        return (userInput - 1);/*
        ArrayList<String> projectNames = new ArrayList<>();
        for (Project project : activeUser.getProjects()) {
            projectNames.add(project.getName());
        }
        System.out.println("User: " + activeUser.getUsername());
        System.out.println("Your projects: ");
        for (int j = 0; j < projectNames.size(); j++) {
            System.out.println((j + 1) + ".-" + projectNames.get(j));
        }
        System.out.println(projectNames.size() + 1 + ".- Back");
        System.out.println("\n Which project would you like to see?");
        int userChoice = scanInt(1, (projectNames.size() + 1));
        if (userChoice == projectNames.size() + 1) return -1;
        return userChoice - 1;*/
    }
    public static void main(String[] args) {
        HomeMenuView homeMenuView = new HomeMenuView();
        Priority priority = homeMenuView.selectPriority();
        System.out.println("This is the priority " + priority.toString());
    }
}
