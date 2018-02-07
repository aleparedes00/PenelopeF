package repository;

//import com.oracle.javafx.jmx.json.JSONReader;
//import jdk.nashorn.internal.parser.JSONParser;
import controller.Serializer;
import models.Group;
import models.Project;
import models.Priority;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class ProjectRepository {

    private final Serializer serializer = new Serializer();

    public static void initiateProgram() {
        File file = new File("Project/"); //This line is probably to make the user.
        if (!file.exists()) {
            file.mkdir();
        }
/*        try (FileWriter writer = new FileWriter(file))
        {
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public String createFolder(Project project) {
        String name = "Project/" + project.getId().toString();
        File file = new File(name);
        file.mkdirs();
        
        return file.getPath();
    }
    public void createNew(Project project) {
     //   String path = createFolder(project);
        File file = new File("Project/" + project.getId().toString() + ".properties");
       // String pathFile = file.getPath();
      /*  try {
            serializer.serialize(project, path + "/" + project.getNameOfProject() + ".json");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try (FileWriter writer = new FileWriter(file)) {
            Properties projectProperties = new Properties();
            projectProperties.setProperty("nameOfProject", project.getNameOfProject());
            projectProperties.setProperty("id", project.getId().toString());
            projectProperties.setProperty("dateCreation", project.getDate().toString());
            projectProperties.store(writer, "Creation of project");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Project> readAndLoadProjectArray() {
        File folder = new File("Project/");
        File[] projectsFiles = folder.listFiles(new FileFilter() {
                                                    @Override
                                                    public boolean accept(File file) {
                                                        return !file.isHidden();
                                                    }
                                                });
                ArrayList < Project > projects = new ArrayList<>();
        //String nameOfProject = new String();
        if (projectsFiles != null) {
            for (File projectsFile : projectsFiles) {
                // read each project file, load the project and add it to projects
                Project project = readProject(projectsFile);
                projects.add(project);
            }
        }
        return projects;
    }

    public Project readProject(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            Properties properties = new Properties();
            properties.load(fileReader);
            String nameOfProject = properties.get("nameOfProject").toString();
            Project project = new Project(nameOfProject, new Group("default"), Priority.NORMAL); //TODO: implement reading group ownership and priority from properties file
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
        initiateProgram();
        Project project = new Project("somethingNew", new Group("default"), Priority.NORMAL);
        //repository.createFolder(project);
        repository.createNew(project);
        //System.out.println("New project created " + project.getNameOfProject());
        /*ArrayList<Project> projects = repository.readAndLoadProjectArray();

        for (int i = 0; i < projects.size(); i++) {
            System.out.println(projects.get(i).getNameOfProject().replace(".json", ""));
        }*/
    }
}
