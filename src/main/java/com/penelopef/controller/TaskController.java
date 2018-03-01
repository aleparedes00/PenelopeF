package com.penelopef.controller;

import com.penelopef.models.Project;
import com.penelopef.models.Task;
import com.penelopef.views.TaskView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.penelopef.PenelopeF.getRepositories;
import static com.penelopef.models.Priority.selectPriority;
import static com.penelopef.tools.MenuTools.showMenu;
import static com.penelopef.tools.ScannerTools.*;

class TaskController {

    private final TaskView taskView;
    private final Project project;

    TaskController(TaskView taskView, Project project) {
        this.project = project;
        this.taskView = taskView;
    }

    void showTasks() {
        showMenu(ctx -> {
            List<Task> activeTasks = project.getTasks()
                    .stream()
                    .filter(Task::isActive)
                    .collect(Collectors.toList());
            activeTasks.sort(Comparator.comparing(Task::getPriority));
            Integer selectedTaskIndex = taskView.showAndSelectTask(activeTasks);
            if (selectedTaskIndex < activeTasks.size()) {
                taskManager(activeTasks.get(selectedTaskIndex));
            } else if (selectedTaskIndex == activeTasks.size())
                createTask();
            else ctx.leaveCurrentMenu = true;
        });
    }

    private void taskManager(Task task) {
        showMenu(ctx -> {
            switch (taskView.drawTask(task)) {
                case DONE:
                    taskView.markTaskAsDone(task);
                    task.setActive(false);
                    ctx.leaveCurrentMenu = true;
                    break;
                case MODIFY:
                    modifyTask(task);
                    break;
                case BACK:
                    ctx.leaveCurrentMenu = true;
            }
        });
    }

    private void modifyTask(Task task) {
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
                    ctx.leaveCurrentMenu = true;
            }
        });
    }

    private void createTask() {
        Task newTask = taskView.drawNewTask();
        project.addTask(newTask);
        getRepositories().saveData();
    }
}
