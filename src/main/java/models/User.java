package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.*;

import static java.lang.Math.min;
import static java.lang.Math.random;
import static models.Group.ADMIN_GROUP;

public class User {

    private String username;

    private String firstName;
    private String lastName;

    private String password;

    private Contact contactInfo;

    @JsonIgnore
    private ArrayList<Project> projects;
    private ArrayList<UUID> projectsIds;

    @JsonIgnore
    private Group selfGroup;
    private UUID selfGroupId;

    @JsonIgnore
    private ArrayList<Group> groups;
    private ArrayList<UUID> groupsIds;

    private UUID id;

    /* Password alphabets */
    public static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    public static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUM = "0123456789";
    public static final String SPECIAL = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    /* Constructor by default */
    public User() {
    }

    /* Constructor */
    public User(String firstName, String lastName, String... additional) {
        this.id = UUID.randomUUID();

        this.firstName = firstName;
        this.lastName = lastName;
        this.groups = new ArrayList<>();
        this.groupsIds = new ArrayList<>();

        this.username = (additional.length >= 1) ? additional[0] :
                lastName.toLowerCase().substring(0, min(6, lastName.length())) + "_" + firstName.toLowerCase().charAt(0);

        this.password = (additional.length >= 2) ? additional[1] : generatePassword();

        this.contactInfo = new Contact();

        Group selfGroup = new Group(this);
        this.selfGroup = selfGroup;
        this.selfGroupId = selfGroup.getId();

        this.projects = new ArrayList<>();
        this.projectsIds = new ArrayList<>();
    }

    /* Getters */
    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonIgnore
    public String getName() {
        return firstName + " " + lastName;
    }

    public String getPassword() {
        return password;
    }

    public Contact getContactInfo() {
        return contactInfo;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public ArrayList<UUID> getProjectsIds() {
        return projectsIds;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public ArrayList<UUID> getGroupsIds() {
        return groupsIds;
    }

    public Group getSelfGroup() {
        return selfGroup;
    }

    public UUID getSelfGroupId() {
        return selfGroupId;
    }

    public UUID getId() {
        return id;
    }

    /* Setters */
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public void setSelfGroup(Group selfGroup) {
        this.selfGroup = selfGroup;
    }

    /* Other Methods */
    public void addProject(Project project) {
        this.projects.add(project);
        if (!projectsIds.contains(project.getId())) {
            this.projectsIds.add(project.getId());
        }
    }

    public void removeProject(Project project) {
        projects.remove(project);
        projectsIds.removeIf(pId -> pId == project.getId());
    }

    public void addGroup(Group group) {
        this.groups.add(group);
        this.groupsIds.add(group.getId());
    }
    //public void addDeactiveProject(Project project) {this.deactiveProjects.add(project);}


    private String generatePassword() {
        Character[] pwdBuilder = new Character[8];

        // At least one capital letter
        int random_char = (int) (random() * ALPHA_CAPS.length());
        pwdBuilder[0] = ALPHA_CAPS.charAt(random_char);

        // At least one number
        random_char = (int) (random() * NUM.length());
        pwdBuilder[1] = NUM.charAt(random_char);

        // At least one SPECIAL character
        random_char = (int) (random() * SPECIAL.length());
        pwdBuilder[2] = SPECIAL.charAt(random_char);

        // Fill the rest
        String alphabet = ALPHA + ALPHA_CAPS + NUM + SPECIAL;
        for (int i = 3; i < 8; i++) {
            random_char = (int) (random() * alphabet.length());
            pwdBuilder[i] = alphabet.charAt(random_char);
        }

        // Shuffle
        List<Character> toShuffle = Arrays.asList(pwdBuilder);
        Collections.shuffle(toShuffle);
        StringBuilder pwd = new StringBuilder();
        for (char c : toShuffle) {
            pwd.append(c);
        }

        return pwd.toString();
    }

    public void printGroupsOfUser() {
        System.out.println("Groups of user " + this.getUsername());
        for (Group group : groups) {
            System.out.println("\t" + group.getName());
        }
    }

    @JsonIgnore
    public Boolean isAdmin() {
        for (Group group : groups) {
            if (group.getName().equals(ADMIN_GROUP)) {
                return true;
            }
        }
        return false;
    }

    public boolean isRightPassword(String pwd) {
        return this.getPassword().equals(pwd);
    }

    /* Message Methods */
    public void writeNewMessage(Dashboard dash) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Add a message. Title [Enter] Content [Enter]");
        String title = sc.nextLine();
        String msg = sc.nextLine();
        Message newMessage = new Message(title, msg, LocalDateTime.now().toString(), this);
        dash.addMessage(newMessage);
    }
}
