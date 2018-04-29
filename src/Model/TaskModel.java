package Model;

//Name: String, “Task1” by default
//Description: String, empty by default
//Due Date: Date/String, empty by default
//Status: String, the first column of that project by default (For ex TODO)*
//        NOTE: Because of this requirement, the tasks in one project cannot be moved to another project (because the columns maybe different).
//        Think about what this implies about the relationship between Project and Task.
//
//        You will need a TaskModelComparator or implement a comparable to compare tasks by due date.
//        Additionally you may want to implement a TaskModelListener and make the ProjectModel, implement TaskModelListener.

import java.util.Date;

public class TaskModel {
    private String name = "Task1";
    private String description;
    private Date dueDate;
    // need a datatype that encapsulates dynamic types of "columns"

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
