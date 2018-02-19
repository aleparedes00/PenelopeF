package repository;

//import com.oracle.javafx.jmx.json.JSONReader;
//import jdk.nashorn.internal.parser.JSONParser;

import models.Project;
import models.SystemData;
import models.User;
import test.TestData;
import tools.Serializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;

/*A chaque fois que repository deserialise un ficher, il utilise SystemData pour remplir celui là
 *
 * */
public class ProjectRepository {

    private final Serializer serializer = new Serializer();
    private final SystemData systemData;
    private final Path path;

    /*Constructor*/

    public ProjectRepository(String folderName, SystemData systemData) {

        this.path = initiateProgram(folderName);
        this.systemData = systemData;
    }

    public Path initiateProgram(String folderName) {
        File file = new File(folderName + "/"); //This line is probably to make the user.
        if (!file.exists()) {
            file.mkdir();
        }
/*        try (FileWriter writer = new FileWriter(file))
        {
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }*/
        String p = file.getPath();
        Path path = Paths.get(p);
        return Paths.get(file.getPath());
    }

    public String createFolder(Project project) {
        String name = path + "/" + project.getName();
        File file = new File(name);
        file.mkdirs();

        return file.getPath();
    }

    public void createNewUser(User user) {
        String pathToFile = path.toString() + user.getId().toString() + ".json";
        try {
            serializer.serialize(user, pathToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNew(Project project) {
        String pathFile = path.toString() + "/" + project.getId().toString() + ".json";
        System.out.println("this is path file: " + pathFile);
        try {
            serializer.serialize(project, pathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!(systemData.existsProject(project.getId()))) {
            systemData.loadProjectInMap(project);
        }
        /*String path = createFolder(project);
        File file = new File("Project/" + project.getId().toString() + ".properties");
        try (FileWriter writer = new FileWriter(file)) {
            Properties projectProperties = new Properties();
            projectProperties.setProperty("nameOfProject", project.getName());
            projectProperties.setProperty("id", project.getId().toString());
            projectProperties.setProperty("dateCreation", project.getDate().toString());
            projectProperties.store(writer, "Creation of project");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /*public HashMap<UUID,Project> readAndLoadProjectArray() {
        File folder = new File("Project/");
        File[] projectsFiles = folder.listFiles(file -> !file.isHidden());
        HashMap<UUID, Project> projects = new HashMap<>();
        //String nameOfProject = new String();
        if (projectsFiles != null) {
            for (File projectsFile : projectsFiles) {
                // read each project file, load the project and add it to projects
                Project project = readProject(projectsFile.toString());
                projects.put(project.getId(), project);
            }
        }
        return projects;
    }*/

    public void loadProjects() {
        File folder = new File(path.toString() + "/");
        File[] projectsFiles = folder.listFiles(file -> !file.isHidden());
        if (projectsFiles != null) {
            for (int i = 0; i < projectsFiles.length; i++) {
                Project p = readProject(projectsFiles[i].toString());
                if (!systemData.existsProject(p.getId())) {
                    systemData.loadProjectInMap(p);
                }
            }
        }
    }

    public Project readProject(String pathToFile) {
        try {
            return serializer.deserialize(pathToFile, Project.class);
        } catch (IOException e) {
            System.out.println("Fuck! " + e);
        }

       /* try (FileReader fileReader = new FileReader(file)) {
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
        }*/
        return null;
    }

    public Path getPath() {
        return path;
    }

    public static void main(String[] args) {
        SystemData systemData = new SystemData();
        ProjectRepository repository = new ProjectRepository("Project", systemData);

        TestData test = new TestData();
        User user = test.project1_2();
        System.out.println("Project amount is " + user.getProjects().size());

        //Save project
        for (int i = 0; i < user.getProjects().size(); i++) {
            repository.createNew(user.getProjects().get(i));
        }

        //Read project
        //repository.loadProjects();
        //System.out.println("Id of project: " + .getId().toString());
        //Project project = new Project("somethingNew", new Group("default"), Priority.NORMAL);
        //repository.createFolder(project);
        //repository.createNew(user.getProjects().get(0));
        //Project p = repository.readProject(new File(repository.getPath() + "/TestProject/fc37b35e-8ddd-4cc5-b563-00f162e35d0a.json"));
        //System.out.println("project name : " + p.getName());
        /*
        for (Project project : user.getProjects()) {
            repository.createNew(project);
        }*/
        /*
        for (int i = 0; i < user.getProjects().size(); i++) {
        repository.createNew(user.getProjects().get(i));
        }*/
        //System.out.println("New project created " + project.getName());
        /*ArrayList<Project> projects = repository.readAndLoadProjectArray();

        for (int i = 0; i < projects.size(); i++) {
            System.out.println(projects.get(i).getName().replace(".json", ""));
        }*/
    }
}
