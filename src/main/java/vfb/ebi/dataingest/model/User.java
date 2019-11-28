package vfb.ebi.dataingest.model;

import vfb.ebi.dataingest.api.DataIngestAPI;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements DataIngestResource, Serializable {
    private String apitoken = "";
    private String username = "Anonymous";
    private String id = "";
    private static User user = null;
    private List<Project> projects = new ArrayList<>();

    // use singleton here
    public static User getInstance() {
        if(user==null) {
            user = new User();
        }
        return user;
    }

    public static void login(User u) {
        // Here the already existing user object is changed.. Not sure whether this can be replaced by 'user = u';
        getInstance().setApiToken(u.apitoken);
        getInstance().setUsername(u.username);
        getInstance().setId(u.id);
        getInstance().setApiToken(u.apitoken);
        getInstance().setProjects(u.projects);
    }

    private User() {}

    public String getApiToken() {
        return apitoken;
    }

    private void setApiToken(String apitoken) {
        this.apitoken = apitoken;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    private void setId(String orcid) {
        this.id = id;
    }

    private void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toJSON() {
        return null;
    }

    public void addProject(Project p) {
        this.projects.add(p);
    }

    public Iterable<? extends Project> getProjects() {
        return this.projects;
    }

    public void addProjects(List<Project> projects) {
        this.projects.addAll(projects);
    }

    public boolean isLoggedIn() {
        return !apitoken.isEmpty();
    }
}
