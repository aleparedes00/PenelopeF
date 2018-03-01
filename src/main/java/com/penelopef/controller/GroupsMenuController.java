package com.penelopef.controller;

import com.penelopef.models.Group;
import com.penelopef.views.GroupsMenuView;

import static com.penelopef.PenelopeF.getSystemData;
import static com.penelopef.tools.MenuTools.showMenu;

class GroupsMenuController {
    private GroupsMenuView groupsMenuView;

    /* Constructor */
    GroupsMenuController() {
        this.groupsMenuView = new GroupsMenuView(getSystemData());
    }

    void showGroups() {
        showMenu(ctx -> {
            Group selectedGroup = groupsMenuView.listGroups();
            if (selectedGroup != null) {
                UsersMenuController usersMenuController = new UsersMenuController(selectedGroup);
                usersMenuController.showUsers();
            } else ctx.leaveCurrentMenu = true;
        });
    }
}