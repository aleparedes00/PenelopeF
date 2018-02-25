package controller;

import models.UserSystem;
import repository.RepositoryManager;
import views.AdminMenuView;
import views.UserSystemView;

import static tools.MenuTools.showMenu;

public class AdminMenuController {
    private final UserSystemController os;
    private final RepositoryManager repositories;

    private AdminMenuView adminMenuView;

    public AdminMenuController(RepositoryManager repositories) {
        UserSystem userSystem = repositories.getSystemData().getUserSystem();
        this.os = new UserSystemController(userSystem, new UserSystemView(userSystem));
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
                case BACK:
                    ctx.leaveCurrentMenu = true;
            }

        });
    }
}
