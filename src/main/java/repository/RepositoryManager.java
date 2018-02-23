package repository;

import models.*;
import static views.PenelopeF.*;

public class RepositoryManager {
    private Repository<User> userRepository;
    private Repository<Group> groupRepository;
    private Repository<Message> messageRepository;
    private ProjectRepository projectRepository;
    private SystemData systemData;

    public RepositoryManager(SystemData systemData) {
        this.userRepository = new UserRepository(usersJson, systemData);
        this.groupRepository = new GroupRepository(groupsJson, systemData);
        this.messageRepository = new MessageRepository(messagesJson, systemData);
        this.projectRepository = new ProjectRepository(defaultProjectsPath, systemData);
        this.systemData = systemData;
    }

    /* Loading */
    public void loadData() {
        userRepository.loadData();
        groupRepository.loadData();
        messageRepository.loadData();
        projectRepository.loadData();
    }

    public void loadActiveUserProjects(User activeUser) {
        projectRepository.loadProjectsToUser(activeUser);
    }

    /* Updating Projects */
    public void createNewProject(Project project) {
        projectRepository.addNewProjectFile(project);
        projectRepository.addNewProjectFolder(project);
    }

    /* Saving */
    public void saveData() {
        userRepository.saveData();
        groupRepository.saveData();
        messageRepository.saveData();
        projectRepository.saveData();
    }
}
