package controller;

import models.Group;
import models.SystemData;
import models.User;
import views.UsersMenuView;

import static tools.MenuTools.showMenu;

public class UsersMenuController {
    private UsersMenuView usersMenuView;
    private final Group selectedGroup;
    private final SystemData systemData;

    public UsersMenuController(Group selectedGroup, SystemData systemData) {
        this.usersMenuView = new UsersMenuView(systemData);

        this.selectedGroup = selectedGroup;
        this.systemData = systemData;
    }

    public void showUsers() {
        showMenu(ctx -> {
            User selectedUser = usersMenuView.listUsers(selectedGroup);
            if (selectedUser != null) {
                usersMenuView.showSelectedUser(selectedUser);
            } else ctx.leaveCurrentMenu = true;
        });
    }
}
