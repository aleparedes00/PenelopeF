package repository;

import com.oracle.javafx.jmx.json.JSONReader;
import jdk.nashorn.internal.parser.JSONParser;
import models.Project;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class ProjectRepository {
    public static void initiateProgram() {
        File file = new File("Project"); //This line is probably to make the user.
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public void createNew(Project project) {
        String name = "Project/" + project.getNameOfProject() + ".json";
        File file = new File(name);
        try (FileWriter writer = new FileWriter(file)) {
            Properties projectProperties = new Properties();
            projectProperties.setProperty("id", project.getId().toString());
            projectProperties.store(writer, "Creation of project");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Project> readAndLoadProjectArray() {
        File folder = new File("Project/");
        File[] projectsFiles = folder.listFiles();
        ArrayList<Project> projects = new ArrayList<>();
        String nameOfProject = new String();
        for (int i = 0; i < projectsFiles.length; i++) {
            // read each project file, load the project and add it to projects
            Project project = readProject(projectsFiles[i]);
            projects.add(project);
        }
        return projects;
    }

    public Project readProject(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            Properties properties = new Properties();
            properties.load(fileReader);
            Project project = new Project(file.getName());
            project.setId((String) properties.get("id"));
            // read all tasks file and fill in the Project
            // read all documents file and fill in the Project
            // read group
            return project;
        } catch (IOException e) {
            throw new RuntimeException("GOD DAMMIT! Everything went wrong here... ", e);
        }
    }

    public static void main(String[] args) {
        ProjectRepository repository = new ProjectRepository();
        repository.initiateProgram();
        ArrayList<Project> projects = repository.readAndLoadProjectArray();

        for (int i = 0; i < projects.size(); i++) {
            System.out.println(projects.get(i).getNameOfProject().replace(".json", ""));
        }
    }
}
