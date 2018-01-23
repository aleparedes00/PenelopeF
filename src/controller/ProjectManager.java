package controller;

import javafx.scene.Group;
import javafx.scene.Parent;
import models.Document;
import models.Project;
import models.Task;
import models.User;
import views.ProjectView;

import java.util.Scanner;

/**
 * Created by alejandraparedes on 1/21/18.
 */

public class ProjectManager {

    private final ProjectView projectView;
    Scanner sc = new Scanner(System.in);

    public ProjectManager(ProjectView projectView) {
        this.projectView = projectView;
    }

   /* public Boolean waitForAction() {

        int input = sc.nextInt();

        switch (input) {
            case 1:
                projectView.drawCreateProject();
        }
        // while + scanner

        // update la view
    }*/

    public static void main(String[] args) {
        ProjectManager homeProject = new ProjectManager(new ProjectView());
        User ale = new User("Ale", "Paredes", " ");
        Project project1 = new Project("project1");
        Task task1 = new Task("Make Workflow", "I'm creating the workflow", "High", ale);
        Document file = new Document("Workflow", ale);
        models.Group dev = new models.Group("Dev");
        dev.getUsers().add(ale);
        project1.setGroup(dev);
        project1.addToArrayDoc(file);
        project1.addToArrayTask(task1);
        homeProject.projectView.drawPrintProject(project1);
//        Boolean isRunning = true;
       /* while (isRunning) {
            isRunning = project.waitForAction();
        }*/
    }
}
