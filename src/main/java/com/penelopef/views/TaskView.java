package com.penelopef.views;

import com.penelopef.models.Priority;
import com.penelopef.models.Task;
import com.penelopef.views.menus.TaskElements;

import java.util.List;

import static com.penelopef.PenelopeF.*;
import static com.penelopef.models.Priority.selectPriority;
import static com.penelopef.tools.DateTools.now;
import static com.penelopef.tools.ScannerTools.scanInt;
import static com.penelopef.views.PrintTools.*;
import static com.penelopef.views.menus.TaskElements.*;

public class TaskView {
    public Integer showAndSelectTask(List<Task> tasks){
        int i = 1;
        for (Task task : tasks) {
            System.out.println(i++ + ".- " + task.getTitle() + " [" + task.getPriority() + "]");
        }
        System.out.println(i++ + ".- Create new task");
        System.out.println(i + ".- Back");
        return (scanInt(1, i) - 1);
    }

    public TaskElements drawTask(Task task) {
        // Task info
        System.out.println(TITLE + ": " + task.getTitle() +
                "\n" + CONTENT + ": " + task.getContent() +
                "\n" + DATE + ": " + task.getDate() +
                "\n" + PRIORITY + ": " + task.getPriority().toString() +
                "\n" + USER + ": " + task.getAuthorName());

        // Task menu
        System.out.println("\n1.- " + DONE +
                "\n2.- " + MODIFY +
                "\n3.- " + BACK);
        return TaskElements.valueOf(scanInt(1, 3));
    }

    public TaskElements modifyTask() {
        System.out.println("What would you like to change? ");
        System.out.println("1.- " + TITLE + "\n2.- " + TaskElements.CONTENT + "\n3.- " + TaskElements.PRIORITY + "\n4.- " + TaskElements.BACK);
        return TaskElements.valueOfModify(printStringAndReadInteger("Please, select a valid option", 1, 4));
    }

    public Task drawNewTask() {
        String title = PrintTools.printStringAndReadChoice(TITLE + ":");
        String content = PrintTools.printStringAndReadChoice(CONTENT + ":");
        Priority priority = selectPriority();
        Task task = new Task(title, content, now(), priority, activeUser);
        System.out.println("Created new task \"" + title + "\"");
        return task;
    }

    public void markTaskAsDone(Task task) {
        System.out.println("Task \"" + task.getTitle() + "\" is marked as done");
    }
}
