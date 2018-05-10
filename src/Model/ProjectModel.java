package Model;

import java.io.Serializable;
import java.util.*;

public class ProjectModel implements Serializable {
    private String name = "Project1";
    private ArrayList<Column> columns = new ArrayList<>();
    private HashMap<String, ArrayList<TaskModel>> tasks = new HashMap<>();

    /// Adds a new column to the existing list of columns while maintaining the list order
   public void addColumn(Column column) {
       if (!isDuplicate(column)) {
           columns.add(column);
           tasks.put(column.getName(), new ArrayList<>());
       }
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public ArrayList<TaskModel> getTasksFor(Column column) {
    	if (tasks.get(column.getName()) != null){
    		Collections.sort(tasks.get(column.getName()));
    	}
    	return tasks.get(column.getName());
    }

    public void addTaskFor(Column column, TaskModel task) {
       // only add task if column already exists in columns
        if (isDuplicate(column)) {
            ArrayList<TaskModel> taskModels = tasks.get(column.getName());
            taskModels.add(task);
            tasks.put(column.getName(), taskModels);
            System.out.printf("Added new task for %s\n", column.getName());
        }
    }

    public void updateTasksFor(Column column, ArrayList<TaskModel> tasks) {
        // only add task if column already exists in columns
        if (isDuplicate(column)) {
            this.tasks.put(column.getName(), tasks);
        }
    }

    private boolean isDuplicate(Column column) {
        boolean containsDuplicate = false;
        for (Column elem: columns) {
            if (column.equals(elem)) {
                containsDuplicate = true;
                break;
            }
        }
        return containsDuplicate;
    }
}
