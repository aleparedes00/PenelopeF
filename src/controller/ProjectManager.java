package controller;

import models.User;
import views.ProjectView;

import java.util.Scanner;

import static tools.MenuTools.showMenu;

/**
 * Created by alejandraparedes on 1/21/18.
 */

public class ProjectManager {

    public final ProjectView projectView;
    public User user;
    Scanner sc = new Scanner(System.in);

    /*Constructor*/
    public ProjectManager(ProjectView projectView, User user) {
        this.projectView = projectView;
        this.user = user;

        /*
        switch (input) {
            case 1:
                projectView.createProject();
        }
        // while + scanner

        // update la view
    }*/


        //    homeProject.projectView.drawPrintProject(project1);
//        Boolean isRunning = true;
       /* while (isRunning) {
            isRunning = project.waitForAction();
        }*/
    }

    public void showProject(int projectIndex) {
        showMenu(ctx -> {
            switch (this.projectView.drawPrintProject(this.user.getProjects().get(projectIndex - 1))) {
                case TASK:
                    System.out.println("More information about Task");
                    break;
                case DOCUMENT:
                    System.out.println("[Documents]: more information");
                    break;
                case GROUP:
                    System.out.println("[Group] : changes? Modify? deactivate?");
                    break;
                case DASHBOARD:
                    System.out.println("[Dashboard] : Calling freaking Dashboard");
                    break;
                case MODIFY:
                    System.out.println("Sending to MODIFY View Function");
                    break;
                case DEACTIVATE:
                    System.out.println("Deactivating the project");
                    break;
            }
        });
    }

}
