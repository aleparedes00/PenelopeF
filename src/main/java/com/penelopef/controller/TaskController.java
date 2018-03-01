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
                    getRepositories().saveData();
                    project.getDashboard().createNewSystemMessage("The task " + task.getTitle() + "has been marked as done. Good job!");
                    ctx.leaveCurrentMenu = true;
                    break;
                case EDIT:
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
                    task.setTitle(taskView.editTitle());
                    getRepositories().saveData();
                    taskView.modificationSaved();
                    project.getDashboard().createNewSystemMessage("Task name has been modified.\nNew name: " + task.getTitle());
                    break;
                case CONTENT:
                    task.setContent(taskView.editContent());
                    getRepositories().saveData();
                    taskView.modificationSaved();
                    project.getDashboard().createNewSystemMessage("Content has been modified for task: " + task.getTitle() + ".\nNew content: " + task.getContent());
                    break;
                case PRIORITY:
                    task.setPriority(selectPriority());
                    getRepositories().saveData();
                    taskView.modificationSaved();
                    project.getDashboard().createNewSystemMessage("New priority -> " + task.getPriority().toString() + " to task " + task.getTitle());
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
        project.getDashboard().createNewSystemMessage("New task has been added.\n Task name: " + newTask.getTitle() + "\nPriority: " + newTask.getPriority().toString());
    }
}
