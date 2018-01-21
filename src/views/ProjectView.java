package views;

import models.Project;

import java.util.Scanner;

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
        System.out.println("The tasks are " + project.getArrayTask());
        //System.out.println("");
    }
    public Project drawCreateProject() {

        System.out.println("Welcome to the Project Manager. Project Name: ");
        String name = sc.next();
        Project project1 = new Project(name);
        return project1;
    }
}
