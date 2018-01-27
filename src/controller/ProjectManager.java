package controller;

import models.*;
import views.ProjectView;

import java.util.Scanner;

/**
 * Created by alejandraparedes on 1/21/18.
 */

public class ProjectManager {

    public final ProjectView projectView;
    User user;
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

}
