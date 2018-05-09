package Model;

import java.awt.Color;

//Name: String, â€œTask1â€� by default
//Description: String, empty by default
//Due Date: Date/String, empty by default
//Status: String, the first column of that project by default (For ex TODO)*
//        NOTE: Because of this requirement, the tasks in one project cannot be moved to another project (because the columns maybe different).
//        Think about what this implies about the relationship between Project and Task.
//
//        You will need a TaskModelComparator or implement a comparable to compare tasks by due date.
//        Additionally you may want to implement a TaskModelListener and make the ProjectModel, implement TaskModelListener.

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class TaskModel implements Serializable, Comparable<TaskModel> {
    private String name = "Task1";
    private String description;
    private Date dueDate;
    private Column status;
    private Color backgroundColor;

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

    public Column getStatus() {
        return status;
    }

    public void setStatus(Column status) {
        this.status = status;
    }
    
    public void setBackgroundColor(Color color){
    	this.backgroundColor = color;
    }
    
    public Color getBackgroundColor(){
    	return this.backgroundColor;
    }
   
    @Override
	public int compareTo(TaskModel task) {
		if(dueDate.compareTo(task.getDueDate()) > 0){
			return 1; 
		}else if (dueDate.compareTo(task.getDueDate()) == 0){
			return 0;
		}else{
			return -1;
		}
	}
}
