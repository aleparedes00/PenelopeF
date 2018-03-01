package controller;

import models.Project;
import models.Task;
import models.User;
import test.TestData;
import views.TaskView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.*;
import static tools.MenuTools.showMenu;
import static tools.ScannerTools.*;
import static views.ProjectsMenuView.selectPriority;

public class TaskController {

    private final TaskView taskView;
    private final Project project;

    public TaskController(TaskView taskView, Project project) {
        this.project = project;
        this.taskView = taskView;
    }

    public void showListTasks() {
        showMenu(ctx -> {
            List<Task> undoneTasks = project.getTasks()
                    .stream()
                    .filter(Task::isActive)
                    .collect(Collectors.toList());
            Integer userInput = this.taskView.drawListTask(undoneTasks);
            ArrayList<Task> tasks = project.getTasks();

            if (userInput == undoneTasks.size()) {
                newTask(project);
            } else if (userInput >= undoneTasks.size()) {
                ctx.leaveCurrentMenu = TRUE;
            } else {
                int i = 0;
                for (Task task : tasks) {
                    if (task.isActive()) {
                        if (i == userInput) {
                            // do something with task
                            taskManager(task);
                            break;
                        }
                        i++;
                    }
                }
            }
        });
    }

    public void taskManager(Task task) {
        showMenu(ctx -> {
            switch (taskView.drawTask(task)) {
                case DONE:
                    task.setActive(FALSE);
                    break;
                case MODIFY:
                    modifyTask(task);
                    break;
                case BACK:
                    ctx.leaveCurrentMenu = TRUE;
            }
        });
    }

    public void modifyTask(Task task) {
        showMenu(ctx -> {
            switch (taskView.modifyTask()) {
                case TITLE:
                    task.setTitle(scanString());
                    break;
                case CONTENT:
                    task.setContent(scanString());
                    break;
                case PRIORITY:
                    task.setPriority(selectPriority());
                    break;
                case BACK:
                    ctx.leaveCurrentMenu = TRUE;
            }
        });
    }

    public void newTask(Project project) {
        Task newTask = taskView.drawNewTask();
        project.addTask(newTask);
    }

    public static void main(String[] args) {
        TestData testData = new TestData();
        User activeUser = testData.project1_2();
        TaskView taskView = new TaskView();
        TaskController taskController = new TaskController(taskView, activeUser.getProjects().get(0));
        taskController.showListTasks();

    }
}
