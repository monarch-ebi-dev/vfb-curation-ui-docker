package vfb.ebi.dataingest.model;

import vfb.ebi.dataingest.api.APIAccessException;
import vfb.ebi.dataingest.api.DataIngestAPI;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements DataIngestResource, Serializable {
    private String apitoken = "";
    private String username = "Anonymous";
    private String orcid = "";
    private static User user = null;
    private List<Project> projects = new ArrayList<>();

    // use singleton here
    public static User getInstance() {
        if(user==null) {
            user = new User();
        }
        return user;
    }

    private User() {}

    public String getApiToken() {
        return apitoken;
    }

    public void setApiToken(String apitoken) {
        this.apitoken = apitoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrcid() {
        return orcid;
    }

    public void setOrcid(String orcid) {
        this.orcid = orcid;
    }

    public boolean authenticate() {
        return DataIngestAPI.getInstance().authenticate(getApiToken());
    }

    public void login(String s) {
        try {
            DataIngestAPI.getInstance().login(s, User.getInstance());
        } catch (APIAccessException e) {
            e.printStackTrace();
        }
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
}
