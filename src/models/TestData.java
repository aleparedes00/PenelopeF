package models;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public class TestData {

    public static User project1_2() {
        User user = new User("Alejandra", "Paredes");
        user.setPassword("test");
        Project project1 = new Project("TestProject");
        user.addProject(project1);
        Task task1 = new Task("Task1", "Creating content", "High", user);
        Document file1 = new Document("Workflow", user);
        Group dev = new Group("Dev");
        dev.getUsers().add(user);
        project1.setGroup(dev);
        project1.addToArrayDoc(file1);
        project1.addToArrayTask(task1);
        project1.setGroup(dev);
        Task task2 = new Task("Task2", "Creating content", "High", user);
        Document file2 = new Document("Workflow2", user);
        project1.addToArrayDoc(file2);
        project1.addToArrayTask(task2);
        Project project2 = new Project("TestProject2");
        user.addProject(project2);
        Task task3 = new Task("Task3", "Creating content", "High", user);
        Document file3 = new Document("Workflow3", user);
        project2.addToArrayDoc(file3);
        project2.addToArrayDoc(file2);
        project2.addToArrayTask(task2);
        project2.addToArrayTask(task3);
        project2.setGroup(dev);
        return user;
    }

}
