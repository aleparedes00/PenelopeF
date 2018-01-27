package models;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public class TestData {

    public static User project1_2() {
        User user = new User("Alejandra", "Paredes");
        Project project1 = new Project("TestProject");
        Task task1 = new Task("Task1", "Creating content", "High", user);
        Document file1 = new Document("Workflow", user);
        Group dev = new Group("Dev");
        dev.getUsers().add(user);
        project1.setGroup(dev);
        project1.addToArrayDoc(file1);
        project1.addToArrayTask(task1);
        user.addProject(project1);
        Project project2 = new Project("TestProject2");
        Task task2 = new Task("Task2", "Creating content", "High", user);
        Document file2 = new Document("Workflow2", user);
        project1.setGroup(dev);
        project1.addToArrayDoc(file2);
        project1.addToArrayTask(task2);
        user.addProject(project2);
        return user;
    }

}
