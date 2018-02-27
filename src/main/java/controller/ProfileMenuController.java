package controller;

import models.Admin;
import repository.RepositoryManager;
import views.AdminView;
import views.ContactView;
import views.ProfileMenuView;

import static tools.MenuTools.showMenu;
import static views.PenelopeF.activeUser;
import static views.PenelopeF.systemData;

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
