package controller;

import models.TestData;
import views.ProjectView;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public class PenelopeF {
    public static void main(String[] args) {
        ProjectManager projectManager = new ProjectManager(new ProjectView(), TestData.project1_2());

        switch (projectManager.projectView.showAndSelectHome()) {
            case CREATE_PROJECT:
                projectManager.user.addProject(projectManager.projectView.createProject());
                break;
            case LIST_PROJECTS:
                projectManager.projectView.listProjects(projectManager.user);
                break;
        }




        //proj.projectView.drawPrintProject(project1);
    }
}