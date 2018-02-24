package views;

import models.Project;
import models.Task;
import views.menus.TaskElements;

public class TaskView {
    public Integer drawListTask(Project project){
        int i = 1;
        for (Task task: project.getTasks()) {
            System.out.println(i++ + ".-" + task.getTitle());
        }
        System.out.println(i++ + ".- Create new");
        System.out.println(i + ".- Back");
        return PrintTools.printStringAndReadInteger("Please, select the talk you would like to see", 1, i);
    }

}
