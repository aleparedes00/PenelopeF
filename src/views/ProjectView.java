package views;

import models.Project;
import models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Created by alejandraparedes on 1/21/18.
 */
public class ProjectView {
    Scanner sc = new Scanner(System.in);
    public void draw(Project project) {
        // System.out.println() x 100;
    }
    //Equal to printAvailableCommands
    public void drawAllCommands(Project project) {
        System.out.println("[PROJECT TEST] Available Commands");
        System.out.println("1.- Create Project");
        System.out.println("2.- List Projects");
        System.out.println();

    }
    public void drawPrintProject(Project project){
        System.out.println("Congrats, First project:");
        System.out.println("Project Name: " + project.getNameOfProject());
        List<String> stringList = new ArrayList<>();
        for (Task task: project.getArrayTask()) {
            stringList.add(task.getTitle());
        }//ce for fait le même code que celui commente en bas. Il récupére les titres des tasks depuis project et transforme en array (collectio)
        /*stringList = project
                .getArrayTask()
                .stream()
                .map(task -> task.getTitle())
                .collect(toList());*/
        System.out.println("The tasks are " + stringList);

        //System.out.println("");
    }
    public Project drawCreateProject() {

        System.out.println("Welcome to the Project Manager. Project Name: ");
        String name = sc.next();
        Project project1 = new Project(name);
        return project1;
    }
}
