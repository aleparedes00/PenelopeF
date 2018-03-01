package com.penelopef.repository;

import com.penelopef.models.*;
import com.penelopef.PenelopeF;

import java.io.File;

import static com.penelopef.PenelopeF.*;

public class RepositoryManager {
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private MessageRepository messageRepository;
    private ProjectRepository projectRepository;

    public RepositoryManager() {
        this.userRepository = new UserRepository(usersJson);
        this.groupRepository = new GroupRepository(groupsJson);
        this.messageRepository = new MessageRepository(messagesJson);
        this.projectRepository = new ProjectRepository(defaultProjectsPath);
    }

    /* Loading */
    public void loadData() {
        userRepository.loadData();
        groupRepository.loadData();
        messageRepository.loadData();
        projectRepository.loadData();
    }

    public void loadActiveUserProjects() {
        groupRepository.loadGroupsToUser(activeUser);
        projectRepository.loadProjectsToUser(activeUser);
    }

    /* Updating Projects */
    public void createNewProject(Project project) {
        projectRepository.addNewProjectFile(project);
        projectRepository.addNewProjectFolder(project);
        FSListenable.addListener(project, new File(project.getPathToProject()).toPath());
    }

    /* Saving */
    public void saveData() {
        userRepository.saveData();
        groupRepository.saveData();
        messageRepository.saveData();
        projectRepository.saveData();
    }
}
