package com.penelopef.controller;

import com.penelopef.models.Group;
import com.penelopef.models.User;
import com.penelopef.views.UsersMenuView;

import static com.penelopef.tools.MenuTools.showMenu;

class UsersMenuController {
    private UsersMenuView usersMenuView;
    private final Group selectedGroup;

    UsersMenuController(Group selectedGroup) {
        this.usersMenuView = new UsersMenuView();

        this.selectedGroup = selectedGroup;
    }

    void showUsers() {
        showMenu(ctx -> {
            User selectedUser = usersMenuView.listUsers(selectedGroup);
            if (selectedUser != null) {
                usersMenuView.showSelectedUser(selectedUser);
            } else ctx.leaveCurrentMenu = true;
        });
    }
}
