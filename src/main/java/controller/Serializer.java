package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Group;
import models.Priority;
import models.Project;
import models.User;

import java.io.File;
import java.io.IOException;

public class Serializer {
    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> void serialize(T object, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), object);
    }

    public <T> T deserialize(String filePath, Class<T> type) throws IOException {
        return objectMapper.readValue(new File(filePath), type);
    }

    public static void main(String[] args) throws IOException {
        Project testProject = new Project("Test Project", new Group("Dev"), Priority.HIGH);
        Serializer jsonSerializer = new Serializer();
        jsonSerializer.serialize(testProject, "Project/project1.json");

        User testUser = new User("Test", "User");
        testUser.addProject(testProject);
        jsonSerializer.serialize(testUser, "target/users.json");

//        Deserialization: currently not working (constructor issue, investigating later)
        //User deserializedTestUser = (User) jsonSerializer.deserialize("target/users.json", User.class);
        /*System.out.println("First Name: " + deserializedTestUser.getFirstName());
        System.out.println("Last Name: " + deserializedTestUser.getLastName());
        System.out.println("Username: " + deserializedTestUser.getUsername());
        System.out.println("Password: " + deserializedTestUser.getPassword());
        System.out.println("Groups: " + deserializedTestUser.getGroups());
        System.out.println("Projects: " + deserializedTestUser.getProjects());
    */}
}
