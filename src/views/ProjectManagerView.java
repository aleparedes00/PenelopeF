package views;

import models.Project;
import views.menus.ModifyProjectMenu;
import views.menus.ProjectElements;
import models.User;
import views.menus.ProjectHomeSelection;

import java.util.ArrayList;

import static tools.ScannerTools.scanInt;
import static tools.ScannerTools.scanString;
import static views.PrintTools.printStringAndReadIntegre;

/**
 * Created by alejandraparedes on 1/21/18.
 */
public class ProjectManagerView {

    /*Print Project Information*/
    public ProjectElements drawPrintProject(Project project) {
        System.out.println("Project Name: " + project.getNameOfProject());
//TODO Si le user vient de créer un nouveau project, ce nouveau ne va pas avoir forcement des Tasks et des Documents
        System.out.println("1.-" + ProjectElements.TASK + ":");
        project.getArrayTask().forEach(task -> System.out.println(task.getTitle()));
        System.out.println("2.-" + ProjectElements.DOCUMENT + ":");
        project.getFiles().forEach(document -> System.out.println(document.getNameOfDoc()));
        System.out.println("3.-" + ProjectElements.GROUP + ":" + project.getGroup().getName());
        System.out.println("4.-" + ProjectElements.DASHBOARD + ":");
        System.out.println("5.-" + ProjectElements.MODIFY);
        System.out.println("6.-" + ProjectElements.DEACTIVATE);
        System.out.println("7.-" + ProjectElements.BACK);
        System.out.println("What would you like to do? Please choose a valid number (1-7)");
        return ProjectElements.valueOf(scanInt(1, 7));
    }


    public ModifyProjectMenu modifyProjectMenu(Project project) {
        System.out.println("What would you like to modify?");
        System.out.println("1.-" + ModifyProjectMenu.PROJECT_NAME + ":");
        System.out.println("2.-" + ModifyProjectMenu.GROUP + ":");
        System.out.println("3.-" + ModifyProjectMenu.BACK + ".");
        System.out.println("What would you like to do?");
        return ModifyProjectMenu.valueOf(scanInt(1, 3));
    }

}
