package models;

import java.util.*;

import static java.lang.Math.min;
import static java.lang.Math.random;

public class User {

    private String login;

    private String firstName;
    private String lastName;

    private String password;
    private ArrayList<Project> projects;
    private ArrayList<Group> groups; // List of groups the user belongs to

    /* Password alphabets */
    public static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    public static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUM = "0123456789";
    public static final String SPECIAL = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    /* Constructor */
    public User(String firstName, String lastName, String... additional) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groups = new ArrayList<>();


        this.login = (additional.length >= 1) ? additional[0] :
                lastName.toLowerCase().substring(0, min(6, lastName.length())) + "_" + firstName.toLowerCase().charAt(0);

        this.password = (additional.length >= 2) ? additional[1] : generatePassword();

        Group selfGroup = new Group(this.login);
        this.groups.add(selfGroup);

        this.projects = new ArrayList<>();
    }

    /* Getters */
    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    /* Setters */
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* Other Methods */
    public void addProject(Project project){
        this.projects.add(project);
    }

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
        System.out.println("Groups of user " + this.getLogin());
        for (Group group : this.getGroups()) {
            System.out.println("\t"+group.getName());
        }
    }

    public boolean isAdmin() {
        for (Group group : this.getGroups()) {
            if (group.getName().equals("root"))
                return true;
        }
        return false;
    }

    public boolean isRightPassword(String pwd) {
        return this.getPassword().equals(pwd);
    }
}
