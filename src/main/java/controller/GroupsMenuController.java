package controller;

import models.SystemData;
import models.User;
import models.Group;
import views.GroupsMenuView;

import static tools.MenuTools.showMenu;
import static views.PenelopeF.systemData;

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