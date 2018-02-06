package controller;

import models.User;
import views.GroupView;

import static tools.MenuTools.showMenu;

public class GroupController {
    public final GroupView groupView;
    private User user;

    /*Constructor*/
    public GroupController(GroupView groupView, User user) {
        this.groupView = groupView;
        this.user = user;
    }

    /*Getter*/

    public User getUser() {
        return user;
    }

    /*Other methods*/
    //TODO Controller for the first menu print. To be defined how to connect the GroupController to the projectMananger (?)
    /*public void showGroup() {
        showMenu(ctx -> {
            switch (this.groupView.printGroupMenu(getUser())) {
                case ADD_USERS:

            }
        })
    }*/
}
