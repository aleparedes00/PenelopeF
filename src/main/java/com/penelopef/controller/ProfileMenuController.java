package com.penelopef.controller;

import com.penelopef.models.Admin;
import com.penelopef.repository.RepositoryManager;
import com.penelopef.views.AdminView;
import com.penelopef.views.ContactView;
import com.penelopef.views.ProfileMenuView;

import static com.penelopef.tools.MenuTools.showMenu;
import static com.penelopef.PenelopeF.activeUser;
import static com.penelopef.PenelopeF.systemData;

public class ProfileMenuController {
    private final ContactController contactController;
    private final AdminController adminController;
    private final RepositoryManager repositories;

    private ProfileMenuView profileMenuView;

    public ProfileMenuController(RepositoryManager repositories) {
        this.contactController = new ContactController(activeUser.getContactInfo(), new ContactView());
        this.adminController = new AdminController(new Admin(systemData), new AdminView());
        this.repositories = repositories;
        this.profileMenuView = new ProfileMenuView();
    }

    public void showProfileMenu() {
        showMenu(ctx -> {
            switch (profileMenuView.showCommands()) {
                case CHANGEMAIL:
                    contactController.changeMail();
                    repositories.saveData();
                    break;
                case CHANGEPHONE:
                    contactController.changePhone();
                    repositories.saveData();
                    break;
                case RESETPASSWORD:
                    adminController.changePassword(activeUser);
                    repositories.saveData();
                    break;
                case BACK:
                    ctx.leaveCurrentMenu = true;
            }
        });
    }
}
