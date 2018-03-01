package com.penelopef.controller;

import com.penelopef.views.AdminMenuView;
import com.penelopef.views.AdminView;

import static com.penelopef.PenelopeF.getRepositories;
import static com.penelopef.tools.MenuTools.showMenu;

class AdminMenuController {
    private final AdminController os;

    private AdminMenuView adminMenuView;

    AdminMenuController() {
        this.os = new AdminController(new AdminView());
        this.adminMenuView = new AdminMenuView();
    }

    void showAdminMenu() {
        showMenu(ctx -> {
            switch (adminMenuView.showCommands()) {
                case CREATEUSER:
                    os.createUser();
                    getRepositories().saveData();
                    break;
                case CREATEGROUP:
                    os.createGroup();
                    getRepositories().saveData();
                    break;
                case ADDUSERTOGROUP:
                    os.prepareAddUserToGroup();
                    getRepositories().saveData();
                    break;
                case RESETUSERPASSWORD:
                    os.changeUserPassword();
                    getRepositories().saveData();
                    break;
                case DELETEUSER:
                    os.deleteUser();
                    getRepositories().saveData();
                    break;
                case DELETEGROUP:
                    os.deleteGroup();
                    getRepositories().saveData();
                    break;
                case BACK:
                    ctx.leaveCurrentMenu = true;
            }

        });
    }
}
