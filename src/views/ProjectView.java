package views;

import models.Project;
import models.menus.ModifyProjectMenu;
import models.menus.ProjectElements;
import models.User;
import models.menus.ProjectHomeSelection;

import java.util.ArrayList;

import static tools.ScannerTools.scanInt;
import static tools.ScannerTools.scanString;


/**
 * Created by alejandraparedes on 1/21/18.
 */
public class ProjectView {

    //Equal to printAvailableCommands
    public ProjectHomeSelection showAndSelectHome() {
        System.out.println("[PROJECT TEST] \nAvailable Commands");
        System.out.println("1.-" + ProjectHomeSelection.CREATE_PROJECT);
        System.out.println("2.-" + ProjectHomeSelection.LIST_PROJECTS);
        System.out.println("Please select a number option between 1 and 2");
        return ProjectHomeSelection.valueOf(scanInt(1, 2));
    }

    /*Print Project Information*/
    public ProjectElements drawPrintProject(Project project) {
        System.out.println("Project Name: " + project.getNameOfProject());
        /*List<String> stringList = new ArrayList<>();
        for (Task task : project.getArrayTask()) {
            stringList.add(task.getTitle());
        }*///ce for fait le même code que celui commente en bas. Il récupére les titres des tasks depuis project et transforme en array (collection)

        /*stringList = project
                .getArrayTask()
                .stream()
                .map(task -> task.getTitle())
                .collect(toList());*/
        System.out.println("1.-" + ProjectElements.TASK + ":");
        project.getArrayTask().forEach(task -> System.out.println(task.getTitle()));
        System.out.println("2.-" + ProjectElements.DOCUMENT + ":");
        project.getFiles().forEach(document -> System.out.println(document.getNameOfDoc()));
        System.out.println("3.-" + ProjectElements.GROUP + ":" + project.getGroup().getName());
        System.out.println("4.-" + ProjectElements.DASHBOARD + ":");
        System.out.println("5.-" + ProjectElements.MODIFY);
        System.out.println("redirect to Modify");
        System.out.println("6.-" + ProjectElements.DEACTIVATE);
        System.out.println("7.-" + ProjectElements.BACK);
        System.out.println("What would you like to do? Please choose a valid number (1-7)");
        return ProjectElements.valueOf(scanInt(1, 7));
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
        System.out.println("\n What would you like to see?");
        do {
            int userChoice = scanInt(0, (projectNames.size()));
            if (userChoice <= user.getProjects().size()) {
                return userChoice;
            }
        } while (true);
    }

    public Project createProject() {
        System.out.println("Welcome to the Project Manager.\nProject Name: ");
        String name = scanString();
        Project project = new Project(name);
        System.out.println("New project created. Name: " + project.getNameOfProject());
        //System.out.println("Add:" + "\n1.-" + ProjectElements.TASK + "\n2.-" + ProjectElements.DOCUMENT + "\n3.-" + ProjectElements.GROUP);
        return project;
    }

    public ModifyProjectMenu modifyProjectMenu(Project project) {
        System.out.println("What would you like to modify?");
        System.out.println("1.-" + ModifyProjectMenu.PROJECT_NAME + ":");
        System.out.println("2.-"+ ModifyProjectMenu.GROUP + ":");
        System.out.println("3.-" + ModifyProjectMenu.BACK + ".");
        System.out.println("What would you like to do?");
        return ModifyProjectMenu.valueOf(scanInt(1, 3));
    }

    public String printStringAndReadChoice(String sentenceToPrint) {
        System.out.println(sentenceToPrint);
        return scanString();
    }
}
