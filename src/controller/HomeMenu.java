package controller;

import models.User;
import views.HomeMenuView;
import views.PrintTools;
import views.ProjectManagerView;
import views.menus.ProjectHomeSelection;
import static java.lang.Boolean.TRUE;
import static tools.MenuTools.showMenu;


public class HomeMenu {

    public final User user;
    HomeMenuView homeMenuView = new HomeMenuView();

    /*Constructor*/
    public HomeMenu(User user) {
        this.user = user;
    }

    /*Getters*/
    public User getUser() {
        return user;
    }

    //AFTER LOGIN or even the login could be here
    public void firstnMenuControl() {
        showMenu((ctx) -> {
            switch (homeMenuView.showAndSelectHome()) {
                case CREATE_PROJECT:
                    user.addProject(homeMenuView.createProject());
                    break;
                case LIST_PROJECTS:
                    if (!(user.getProjects().isEmpty())) {
                        int projectIndex = homeMenuView.listProjects(user);
                        System.out.println("My index is " + projectIndex);
                        ProjectManagerView projectManagerView = new ProjectManagerView();
                        ProjectManager projectManager = new ProjectManager(projectManagerView, user, user.getProjects().get(projectIndex));
                        projectManager.showProject();
                    } else {
                        PrintTools.printString("No projects to show");
                    }
                    break;
                case LOGOUT:
                    ctx.leaveCurrentMenu = TRUE;
                    break;
            }
        });
    }
}
