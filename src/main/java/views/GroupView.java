package views;

import models.User;
import views.menus.GroupMenu;

import static views.PrintTools.printStringAndReadIntegre;

public class GroupView {
    public GroupMenu printGroupMenu(User user) {
        System.out.println("Welcome " + user.getName());
        System.out.println("1.-" + GroupMenu.SEE_USERS); //TODO just print the freaking users
        System.out.println("2.-" + GroupMenu.ADD_USERS);
        System.out.println("3.-" + GroupMenu.CHANGE_NAME);
        System.out.println("4.-" + GroupMenu.BACK);
        return GroupMenu.valueOf(printStringAndReadIntegre("What would you like to do? Enter between 1-4", 1, 4));
    }
    //TODO I need a project here? or how am I going to connect the group on the project to GroupController?
    public void printUsers(){
        //System.out.println("The members of the group: " + );
    }
}
