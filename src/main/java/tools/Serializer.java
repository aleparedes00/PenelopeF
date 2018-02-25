package tools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.Group;
import models.Priority;
import models.Project;
import models.User;
import test.TestData;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import static tools.DateTools.now;

public class Serializer {
    private ObjectMapper objectMapper = new ObjectMapper();

    public Serializer() {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
    public <T> void serialize(T object, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), object);
    }

    public <T> T deserialize(String filePath, Class<T> type) throws IOException {
        return objectMapper.readValue(new File(filePath), type);
    }

    public <T> T deserialize(String filePath, TypeReference<T> typeRef) throws IOException {
        return objectMapper.readValue(new File(filePath), typeRef);
    }

    public static void main(String[] args) throws IOException {
        Project testProject = new Project("Test Project", new Group("Dev"), now(), Priority.HIGH);
        Serializer jsonSerializer = new Serializer();
        jsonSerializer.serialize(testProject, "Project/project1.json");

        User testUser = new User("Test", "User");
        testUser.addProject(testProject);
        jsonSerializer.serialize(testUser, "target/user1.json");

        Project deserializedProject = jsonSerializer.deserialize("Project/project1.json", Project.class);
        System.out.println("Project Name: " + deserializedProject.getName());
        System.out.println("Project Date: " + deserializedProject.getDate());

        User testUser2 = TestData.project1_2();
        jsonSerializer.serialize(testUser2, "target/user2.json");

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
