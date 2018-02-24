package controller;

import models.Project;
import models.Task;
import models.User;
import test.TestData;
import tools.MenuTools;
import views.TaskView;

import static tools.MenuTools.showMenu;

public class TaskController {

    private final TaskView taskView;
    private final Project project;

    public TaskController(TaskView taskView, Project project){
        this.project = project;
        this.taskView = taskView;
    }

    public void showListTaks() {
        showMenu(ctx -> {
            Integer userInput = this.taskView.drawListTask(project);
            System.out.println("The choice is " + userInput);
        });
    }
    public static void main(String[] args) {
        TestData testData = new TestData();
        User activeUser = testData.project1_2();
        TaskView taskView = new TaskView();
        TaskController taskController = new TaskController(taskView, activeUser.getProjects().get(0));
        taskController.showListTaks();

    }
}
