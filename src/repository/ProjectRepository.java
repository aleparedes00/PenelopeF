package repository;

import models.Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
            writer.write(project.getNameOfProject());
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
            nameOfProject = projectsFiles[i].getName();
            Project project = new Project(nameOfProject);
            projects.add(project);
            // read each project file, load the project and add it to projects
        }
        return projects;
    }

    public static void main(String[] args) {
        ProjectRepository repository = new ProjectRepository();
        repository.initiateProgram();
        ArrayList<Project> projects = repository.readAndLoadProjectArray();

        for (int i = 0; i < projects.size(); i++) {
            System.out.println(projects.get(i).getNameOfProject());
        }
    }
}
