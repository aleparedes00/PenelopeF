package com.penelopef.controller;

import com.penelopef.views.HomeMenuView;

import static com.penelopef.tools.MenuTools.showMenu;

public class HomeMenuController {
    private HomeMenuView homeMenuView;

    public HomeMenuController() {
        this.homeMenuView = new HomeMenuView();
    }

    public void showHomeMenu() {
        showMenu(ctx -> {
            switch (homeMenuView.showHome()) {
                case GROUPS:
                    GroupsMenuController groupsMenuController = new GroupsMenuController();
                    groupsMenuController.showGroups();
                    break;
                case PROJECTS:
                    ProjectsMenuController projectsMenuController = new ProjectsMenuController();
                    projectsMenuController.showProjects();
                    break;
                case PROFILE:
                    ProfileMenuController profileMenuController = new ProfileMenuController();
                    profileMenuController.showProfileMenu();
                    break;
                case ADMIN:
                    AdminMenuController adminMenuController = new AdminMenuController();
                    adminMenuController.showAdminMenu();
                    break;
                case LOGOUT:
                    ctx.leaveCurrentMenu = true;
            }
        });
    }
}
