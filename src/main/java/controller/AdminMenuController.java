package controller;

import models.Admin;
import repository.RepositoryManager;
import views.AdminMenuView;
import views.AdminView;

import static tools.MenuTools.showMenu;

public class AdminMenuController {
    private final AdminController os;
    private final RepositoryManager repositories;

    private AdminMenuView adminMenuView;

    public AdminMenuController(RepositoryManager repositories) {
        Admin admin = new Admin(repositories.getSystemData());
        this.os = new AdminController(admin, new AdminView());
        this.repositories = repositories;
        this.adminMenuView = new AdminMenuView();
    }

    public void showAdminMenu() {
        showMenu(ctx -> {
            switch (adminMenuView.showCommands()) {
                case CREATEUSER:
                    os.createUser();
                    repositories.saveData();
                    break;
                case CREATEGROUP:
                    os.createGroup();
                    repositories.saveData();
                    break;
                case ADDUSERTOGROUP:
                    os.prepareAddUserToGroup();
                    repositories.saveData();
                    break;
                case RESETUSERPASSWORD:
                    os.changeUserPassword();
                    repositories.saveData();
                    break;
                case DELETEUSER:
                    os.deleteUser();
                    repositories.saveData();
                    break;
                case DELETEGROUP:
                    os.deleteGroup();
                    repositories.saveData();
                    break;
                case BACK:
                    ctx.leaveCurrentMenu = true;
            }

        });
    }
}
