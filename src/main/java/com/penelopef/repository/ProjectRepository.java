package com.penelopef.repository;

import com.penelopef.models.Project;
import com.penelopef.models.User;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.penelopef.PenelopeF.getSystemData;

public class ProjectRepository extends Repository<Project> {

    /* Constructor */
    ProjectRepository(String pathToFolder) {
        super(pathToFolder, Project.class);
        initiateFolder();
    }

    private void initiateFolder() { // Create folder if it doesn't exist
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

//    public List<String> readDocFolder(String nameOfProject) {
//        File[] documents = new File(path + "/" + nameOfProject).listFiles(file -> !file.isHidden());
//        List<String> docList = new ArrayList<>();
//        if (documents != null) {
//            for (File doc : documents) {
//                docList.add(doc.getName());
//            }
//        }
//        return docList;
//    }

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

    void loadProjectsToUser(User activeUser) {
        HashMap<UUID, Project> projects = getSystemData().getProjects();
        activeUser.setProjects(new ArrayList<>(projects.entrySet().stream()
                .filter(e -> activeUser.getProjectsIds().contains(e.getKey()))
                .map(e -> e.getValue())
                .collect(Collectors.toList())));
//        activeUser.getProjects().forEach(project -> {
//            project.setDocuments(readDocFolder(project.getName()));
//        });
    }

    /* Saving */
    public void saveData() {
        HashMap<UUID, Project> projectsToSave = getSystemData().getDataFromType(dataType);
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
    void addNewProjectFile(Project project) {
        String pathToFile = this.path + "/" + project.getId().toString() + ".json";
        try {
            serializer.serialize(project, pathToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!(getSystemData().existsProject(project.getId()))) {
            getSystemData().loadProjectInMap(project);
        }
    }

    void addNewProjectFolder(Project project) {
        String name = path + "/" + project.getName();
        File file = new File(name);
        file.mkdirs();
    }
}

