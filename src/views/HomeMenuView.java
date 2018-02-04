package views;

import models.Project;
import models.User;
import views.menus.ProjectHomeSelection;

import java.util.ArrayList;

import static tools.ScannerTools.scanInt;
import static tools.ScannerTools.scanString;
import static views.PrintTools.printStringAndReadIntegre;

public class HomeMenuView {
    //Equal to printAvailableCommands
    public ProjectHomeSelection showAndSelectHome() {
        System.out.println("Welcome to PenelopeF\nAvailable Commands");
        System.out.println("1.-" + ProjectHomeSelection.CREATE_PROJECT);
        System.out.println("2.-" + ProjectHomeSelection.LIST_PROJECTS);
        System.out.println("3.-" + ProjectHomeSelection.LOGOUT);
        return ProjectHomeSelection.valueOf(printStringAndReadIntegre("Please select a number option between 1 and 3", 1, 3));
    }

    public Project createProject() {
        System.out.println("Welcome to the Project Manager.\nProject Name: ");
        String name = scanString();
        Project project = new Project(name);
        System.out.println("New project created. Name: " + project.getNameOfProject());
        //TODO System.out.println("Add:" + "\n1.-" + ProjectElements.TASK + "\n2.-" + ProjectElements.DOCUMENT + "\n3.-" + ProjectElements.GROUP);
        return project;
    }

    public int listProjects(User user) {
        ArrayList<String> projectNames = new ArrayList<>();
        for (Project project : user.getProjects()) {
            projectNames.add(project.getNameOfProject());
        }
        System.out.println("User: " + user.getUsername());
        System.out.println("Your projects: ");
        for (int j = 0; j < projectNames.size(); j++) {
            System.out.println((j + 1) + ".-" + projectNames.get(j));
        }
        System.out.println("\n Which project would you like to see?");
        do {
            int userChoice = scanInt(0, (projectNames.size()));
            if (userChoice <= user.getProjects().size()) {
                return userChoice - 1;
            }
        } while (true);
    }
}
