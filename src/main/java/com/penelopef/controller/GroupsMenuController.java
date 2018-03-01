package com.penelopef.controller;

import com.penelopef.models.Group;
import com.penelopef.views.GroupsMenuView;

import static com.penelopef.tools.MenuTools.showMenu;
import static com.penelopef.PenelopeF.systemData;

public class GroupsMenuController {
    private GroupsMenuView groupsMenuView;

    /* Constructor */
    public GroupsMenuController() {
        this.groupsMenuView = new GroupsMenuView(systemData);
    }

    public void showGroups() {
        showMenu(ctx -> {
            Group selectedGroup = groupsMenuView.listGroups();
            if (selectedGroup != null) {
                UsersMenuController usersMenuController = new UsersMenuController(selectedGroup, systemData);
                usersMenuController.showUsers();
            } else ctx.leaveCurrentMenu = true;
        });
    }
}