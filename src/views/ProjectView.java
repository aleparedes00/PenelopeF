package views;

import com.sun.org.apache.xerces.internal.xs.StringList;
import models.HomeSelection;
import models.Project;
import models.Task;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by alejandraparedes on 1/21/18.
 */
public class ProjectView {
    Scanner sc = new Scanner(System.in);

    public String scanString() {
        return sc.next();
    }

    public int scanInt(int minimal, int max) {
        int userInput = -1;
        do {
            if (userInput != -1) {
                System.out.println("mardito, entre " + minimal + "y " + max);
            }
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Escribe bien mardito");
            }
            userInput = sc.nextInt();

        } while (userInput < minimal || userInput > max);
        return userInput;
    }

    //Equal to printAvailableCommands
    public HomeSelection showAndSelectHome() {
        System.out.println("[PROJECT TEST] \nAvailable Commands");
//        for (HomeSelection option: HomeSelection.values()) {
//            System.out.println(option);
//        }
        System.out.println("1.-" + HomeSelection.CREATE_PROJECT);
        System.out.println("2.-" + HomeSelection.LIST_PROJECTS);
        System.out.println("Please select a number option between 1 and 2");
        return HomeSelection.valueOf(scanInt(1, 2));
    }

    /*Print Project Informaiton*/
    public void drawPrintProject(Project project) {
        //System.out.println("Congrats, First project:");
        System.out.println("Project Name: " + project.getNameOfProject());
        List<String> stringList = new ArrayList<>();
        for (Task task : project.getArrayTask()) {
            stringList.add(task.getTitle());
        }//ce for fait le même code que celui commente en bas. Il récupére les titres des tasks depuis project et transforme en array (collection)

        /*stringList = project
                .getArrayTask()
                .stream()
                .map(task -> task.getTitle())
                .collect(toList());*/
        System.out.println("The tasks are ");
        project.getArrayTask().forEach(task -> System.out.println(task.getTitle()));
        System.out.println("Documents available are ");
        project.getFiles().forEach(document -> System.out.println(document.getNameOfDoc()));
        System.out.println("Group: " + project.getGroup().getName());
    }

    public int listProjects(User user) {
        //List<String> projectList = user.getProjects().stream().map(project -> project.getNameOfProject()).collect(Collectors.toList());
        /*for(int i = 0; i< projectList.size(); i++)
        {
            System.out.println(i + ".- " + projectList[i]);
        }*/
        ArrayList<String> projectNames = new ArrayList<>();
        for (Project project : user.getProjects()) {
            projectNames.add(project.getNameOfProject());
        }
        System.out.println("User: " + user.getLogin());
        System.out.println("Your projects: ");
        for (int j = 0; j < projectNames.size(); j++)
                {
                    System.out.println((j + 1) + ".-" + projectNames.get(j));
                }
//        System.out.println(user.getProjects().indexOf(user.getProjects()) + ".-" + projectList);
        System.out.println("\n What would you like to see?");
        do {
            int userChoice = scanInt(0, (projectNames.size()));
            if (userChoice <= user.getProjects().size())
            {
                return userChoice;
         //       System.out.println("Bien joue");
//                drawPrintProject(user.getProjects().get(userChoice));
//                break;
            }
        } while (true);
        //user.getProjects().forEach(project -> System.out.println(project.getNameOfProject()));
//        System.out.println("The project number 1 is: " + projectList[0]);
    }

    public Project createProject() {
        System.out.println("Welcome to the Project Manager.\nProject Name: ");
        String name = scanString();
        Project project = new Project(name);
        System.out.println("New project created. Name: " + project.getNameOfProject());
        //System.out.println("Add:" + "\n1.-" + ProjectElements.TASK + "\n2.-" + ProjectElements.DOCUMENT + "\n3.-" + ProjectElements.GROUP);
        return project;
    }
}
