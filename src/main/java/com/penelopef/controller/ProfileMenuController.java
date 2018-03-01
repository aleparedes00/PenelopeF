package com.penelopef.controller;

import com.penelopef.views.AdminView;
import com.penelopef.views.ContactView;
import com.penelopef.views.ProfileMenuView;

import static com.penelopef.PenelopeF.getRepositories;
import static com.penelopef.tools.MenuTools.showMenu;
import static com.penelopef.PenelopeF.activeUser;

class ProfileMenuController {
    private final ContactController contactController;
    private final AdminController adminController;

    private ProfileMenuView profileMenuView;

    ProfileMenuController() {
        this.contactController = new ContactController(activeUser.getContactInfo(), new ContactView());
        this.adminController = new AdminController(new AdminView());
        this.profileMenuView = new ProfileMenuView();
    }

    void showProfileMenu() {
        showMenu(ctx -> {
            switch (profileMenuView.showCommands()) {
                case CHANGEMAIL:
                    contactController.changeMail();
                    getRepositories().saveData();
                    break;
                case CHANGEPHONE:
                    contactController.changePhone();
                    getRepositories().saveData();
                    break;
                case RESETPASSWORD:
                    adminController.changePassword(activeUser);
                    getRepositories().saveData();
                    break;
                case BACK:
                    ctx.leaveCurrentMenu = true;
            }
        });
    }
}
