package com.penelopef.controller;

import com.penelopef.models.Group;
import com.penelopef.models.SystemData;
import com.penelopef.models.User;
import com.penelopef.views.UsersMenuView;

import static com.penelopef.tools.MenuTools.showMenu;

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
