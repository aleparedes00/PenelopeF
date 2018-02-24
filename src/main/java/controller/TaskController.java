package controller;

import models.Project;
import models.User;
import test.TestData;
import views.TaskView;

import static tools.MenuTools.showMenu;

public class TaskController {

    private final TaskView taskView;
    private final Project project;

    public TaskController(TaskView taskView, Project project){
        this.project = project;
        this.taskView = taskView;
    }

    public void showListTasks() {
        showMenu(ctx -> {
            Integer userInput = this.taskView.drawListTask(project);
            if (userInput == project.getTasks().size())
            { System.out.println("Menu for new task"); }
            else if (userInput >= project.getTasks().size())
            { ctx.leaveCurrentMenu = Boolean.TRUE; }
            else
            {
            }
        });
    }
    public static void main(String[] args) {
        TestData testData = new TestData();
        User activeUser = testData.project1_2();
        TaskView taskView = new TaskView();
        TaskController taskController = new TaskController(taskView, activeUser.getProjects().get(0));
        taskController.showListTasks();

    }
}
