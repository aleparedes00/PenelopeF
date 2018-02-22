package test;

import models.*;

import static tools.DateTools.now;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public class TestData {

    public static User project1_2() {
        User user = new User("Alejandra", "Paredes");
        user.setPassword("test");
        Group dev = new Group("Dev");
        user.addGroup(dev);
        Project project1 = new Project("TestProject", dev, now(), Priority.NORMAL);
        user.addProject(project1);
        Task task1 = new Task("Task1", "Creating content", now(), Priority.HIGH, user);
//        Document file1 = new Document("Workflow", user, project1);
//        project1.addDocument(file1);
        project1.addTask(task1);
        Task task2 = new Task("Task2", "Creating content", now(), Priority.HIGH, user);
//        Document file2 = new Document("Workflow2", user, project1);
//        project1.addDocument(file2);
        project1.addTask(task2);
        Project project2 = new Project("TestProject2", dev, now(), Priority.HIGH);
        user.addProject(project2);
        Task task3 = new Task("Task3", "Creating content", now(), Priority.HIGH, user);
//        Document file3 = new Document("Workflow3", user, project2);
//        project2.addDocument(file3);
//        project2.addDocument(file2);
        project2.addTask(task2);
        project2.addTask(task3);
        return user;
    }

}
