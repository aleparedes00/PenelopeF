package repository;

import models.FSListenable;
import models.Project;
import models.SystemData;
import models.User;
import test.TestData;
import views.PenelopeF;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ProjectRepository extends Repository<Project> {

    /* Constructor */
    public ProjectRepository(String pathToFolder, SystemData systemData) {
        super(pathToFolder, systemData, Project.class);
        initiateFolder();
    }

    private void initiateFolder() { // Create folder if it doesn't exist
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public List<String> readDocFolder(String nameOfProject) {
        File[] documents = new File(path + "/" + nameOfProject).listFiles(file -> !file.isHidden());
        List<String> docList = new ArrayList<>();
        if (documents != null) {
            for (File doc : documents) {
                docList.add(doc.getName());
            }
        }
        return docList;
    }

    /* Loading */
    public HashMap<UUID, Project> readData(String pathToFile) {
        File[] projectFiles = new File(path).listFiles(file -> !file.isHidden());
        if (projectFiles != null)
            try {
                HashMap<UUID, Project> deserializedProjects = new HashMap<>();
                for (File projectFile : projectFiles) {
                    if (!projectFile.isDirectory()) {
                        Project p = serializer.deserialize(projectFile.getPath(), Project.class);
                        deserializedProjects.put(p.getId(), p);
                    }
                }
                return deserializedProjects;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Fuck! " + e);
            }
        return EMPTY;
    }

    public void loadProjectsToUser(User activeUser) {
        HashMap<UUID, Project> projects = systemData.getProjects();
        activeUser.setProjects(new ArrayList<>(projects.entrySet().stream()
                .filter(e -> activeUser.getProjectsIds().contains(e.getKey()))
                .map(e -> e.getValue())
                .collect(Collectors.toList())));
    }

    /* Saving */
    public void saveData() {
        HashMap<UUID, Project> projectsToSave = systemData.getDataFromType(dataType);
        for (Map.Entry<UUID, Project> projectEntry : projectsToSave.entrySet()) {
            Project dataToSave = projectEntry.getValue();
            String pathToFile = path + "/" + dataToSave.getId().toString() + ".json";
            try {
                serializer.serialize(dataToSave, pathToFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* Updating */
    public void addNewProjectFile(Project project) {
        String pathToFile = this.path + "/" + project.getId().toString() + ".json";
        try {
            serializer.serialize(project, pathToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!(systemData.existsProject(project.getId()))) {
            systemData.loadProjectInMap(project);
        }
    }

    public void addNewProjectFolder(Project project) {
        String name = path + "/" + project.getName();
        File file = new File(name);
        file.mkdirs();
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

    public static void main(String[] args) {
        SystemData systemData = new SystemData();
        ProjectRepository repository = new ProjectRepository("Project", systemData);

        //TestData test = new TestData();
        //User user = test.project1_2();
        //System.out.println("Project amount is " + user.getProjects().size());

        //Save project
        /*for (int i = 0; i < user.getProjects().size(); i++) {
            repository.addNewProjectFile(user.getProjects().get(i));
        }*/

        //Read project
        //repository.loadProjectsToUser();
        //System.out.println("Id of project: " + .getId().toString());
        //Project project = new Project("somethingNew", new Group("default"), Priority.NORMAL);
        //repository.createFolder(project);
        //repository.addNewProjectFile(user.getProjects().get(0));
        //Project p = repository.readProject(new File(repository.getPath() + "/TestProject/fc37b35e-8ddd-4cc5-b563-00f162e35d0a.json"));
        //System.out.println("project name : " + p.getName());
        /*
        for (Project project : user.getProjects()) {
            repository.addNewProjectFile(project);
        }*/
        /*
        for (int i = 0; i < user.getProjects().size(); i++) {
        repository.addNewProjectFile(user.getProjects().get(i));
        }*/
        //System.out.println("New project created " + project.getName());
        /*ArrayList<Project> projects = repository.readAndLoadProjectArray();

        for (int i = 0; i < projects.size(); i++) {
            System.out.println(projects.get(i).getName().replace(".json", ""));
        }*/
    }
}

