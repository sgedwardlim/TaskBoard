package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskBoardModel implements Serializable {
    public static final String filename = "TaskBoardModelData.ser";

    private String name = "TaskBoard1";
    private ArrayList<ProjectModel> projects = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ProjectModel> getProjects() {
        return projects;
    }

    public void addProject(ProjectModel project) {
        projects.add(project);
    }
}
