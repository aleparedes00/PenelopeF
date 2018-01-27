package views;

import models.HomeSelection;
import models.Project;
import models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

/**
 * Created by alejandraparedes on 1/21/18.
 */
public class ProjectView {
    Scanner sc = new Scanner(System.in);

    public void draw(Project project) {
        // System.out.println() x 100;
    }

    public int scanInt(int minimal, int max) {
        int userInput = -1;
        do {
            if (userInput != -1) {
                System.out.println("mardito, entre " + minimal);
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
        System.out.println("1.- Create Project");
        System.out.println("2.- List Projects");
        System.out.println("Please select a number option between 1 and 2");
        return HomeSelection.valueOf(scanInt(1, 2));
    }

    /*Print Project Informaiton*/
    public void drawPrintProject(Project project) {
        System.out.println("Congrats, First project:");
        System.out.println("Project Name: " + project.getNameOfProject());
        List<String> stringList = new ArrayList<>();
        for (Task task : project.getArrayTask()) {
            stringList.add(task.getTitle());
        }//ce for fait le même code que celui commente en bas. Il récupére les titres des tasks depuis project et transforme en array (collection)
        List<String> stringDocs = project
                .getFiles()
                .stream()
                .map(document -> document.getNameOfDoc())
                .collect(toList());
        
        /*stringList = project
                .getArrayTask()
                .stream()
                .map(task -> task.getTitle())
                .collect(toList());*/
        System.out.println("The tasks are " + stringList);
        System.out.println("Documents available are " + stringDocs);
        System.out.println("Group: " + project.getGroup().getName());
    }

    public Project drawCreateProject() {
        System.out.println("Welcome to the Project Manager. Project Name: ");
        String name = sc.next();
        Project project1 = new Project(name);
        return project1;
    }
}
