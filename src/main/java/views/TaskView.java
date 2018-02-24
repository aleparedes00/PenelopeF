package views;

import models.Priority;
import models.Project;
import models.Task;
import views.menus.TaskElements;

import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static views.PenelopeF.*;
import static views.PrintTools.*;

public class TaskView {
    public Integer drawListTask(Project project){
        int i = 1;
        for (Task task: project.getTasks().stream().filter(Task::isActive).collect(Collectors.toList())) {
            System.out.println(i++ + ".-" + task.getTitle());
        }
        System.out.println(i++ + ".- Create new");
        System.out.println(i + ".- Back");
        Integer userInput = printStringAndReadInteger("Please, select the talk you would like to see", 1, i);
        return (userInput - 1);
    }
    //TODO envoyer directment object task soit faire le meme filer dans le controller

    public TaskElements drawTask(Task task) {
        System.out.println(TaskElements.TITLE + ": " + task.getTitle() +
                "\n" + TaskElements.CONTENT + ": " + task.getContent() +
                "\n" + TaskElements.DATE + ": " + task.getDate() +
                "\n" + TaskElements.PRIORITY + ": " + task.getPriority().toString() +
                "\n" + TaskElements.USER + ": " + task.getAuthorName() +
                "\n1.-" + TaskElements.DONE +
                "\n2.-" + TaskElements.MODIFY +
                "\n3.-" + TaskElements.BACK);
        return TaskElements.valueOf(printStringAndReadInteger("Please, select a valid option", 1, 3));
    }

    public TaskElements modifyTask() {
        System.out.println("What would you like to change? ");
        System.out.println("1.- " + TaskElements.TITLE + "\n2.- " + TaskElements.CONTENT + "\n3.- " + TaskElements.PRIORITY + "\n4.- " + TaskElements.BACK);
        return TaskElements.valueOfModify(printStringAndReadInteger("Please, select a valid option", 1, 4));
    }

    public Task drawNewTask() {
        String message = "Please insert ";
        String title = PrintTools.printStringAndReadChoice(message + TaskElements.TITLE);
        String content = PrintTools.printStringAndReadChoice(message + TaskElements.CONTENT);
        Priority priority = HomeMenuView.selectPriority();
        Task task = new Task(title, content, tools.DateTools.now(), priority, activeUser);
        System.out.println("\tA new task has been created");
        return task;
    }
}
