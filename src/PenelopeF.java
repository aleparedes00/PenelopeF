package controller;

import models.*;
import views.*;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public class PenelopeF { // executable main class
    public static void main(String[] args) {
        // Initialize Application
        UserSystem os = new UserSystem(); // as of now: new system, eventually: load saved users, groups etc.
        User activeUser = null;

        // Login Screen
        LoginModel loginModel = new LoginModel(os);
        LoginView loginView = new LoginView(loginModel);
        LoginController loginController = new LoginController(loginModel, loginView);
        while (activeUser == null) {
            activeUser = loginController.userLogin();
        }

        // Project Screen
        ProjectManager projectManager = new ProjectManager(new ProjectView(), TestData.project1_2());

        switch (projectManager.projectView.showAndSelectHome()) {
            case CREATE_PROJECT:
                projectManager.user.addProject(projectManager.projectView.createProject());
                break;
            case LIST_PROJECTS:
                int projectIndex = projectManager.projectView.listProjects(projectManager.user);
                projectManager.projectView.drawPrintProject(projectManager.user.getProjects().get(projectIndex - 1));
                break;
        }




        //proj.projectView.drawPrintProject(project1);
    }
}
