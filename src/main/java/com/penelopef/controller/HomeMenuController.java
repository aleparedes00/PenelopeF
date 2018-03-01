package com.penelopef.controller;

import com.penelopef.repository.RepositoryManager;
import com.penelopef.views.HomeMenuView;

import static com.penelopef.tools.MenuTools.showMenu;

public class HomeMenuController {
    private final RepositoryManager repositories;

    private HomeMenuView homeMenuView;

    public HomeMenuController(RepositoryManager repositories) {
        this.repositories = repositories;
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
                    ProjectsMenuController projectsMenuController = new ProjectsMenuController(repositories);
                    projectsMenuController.showProjects();
                    break;
                case PROFILE:
                    ProfileMenuController profileMenuController = new ProfileMenuController(repositories);
                    profileMenuController.showProfileMenu();
                    break;
                case ADMIN:
                    AdminMenuController adminMenuController = new AdminMenuController(repositories);
                    adminMenuController.showAdminMenu();
                    break;
                case LOGOUT:
                    ctx.leaveCurrentMenu = true;
            }
        });
    }
}
