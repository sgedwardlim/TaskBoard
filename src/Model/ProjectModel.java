package Model;

import java.util.*;

public class ProjectModel {
    private String name = "Project1";
    private static List<Column> columns = new ArrayList<>();
    private List<TaskModel> tasks = new LinkedList<>();

    /// Adds a new column to the existing list of columns while maintaining the list order
   public void addColumn(String name) {
       Column newColumn = new Column(name);
       boolean containsDuplicate = false;
       for (Column column: columns) {
           if (column.equals(newColumn)) {
               containsDuplicate = true;
               break;
           }
       }

       if (!containsDuplicate) {
           columns.add(newColumn);
       }
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Column> getColumns() {
        return columns;
    }

    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }
}
