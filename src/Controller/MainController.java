package Controller;

import Model.Column;
import Model.ProjectModel;
import Model.TaskBoardModel;
import Model.TaskModel;
import View.ColumnCellView;
import View.MainView;
import View.ProjectView;
import View.TaskCellView;
import View.TaskView;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

public class MainController {
    private JFrame frame;
    private MainView mainView;
    private String path;

    private TaskBoardModel taskBoardModel;

    public MainController(JFrame frame) {
        this.frame = frame;
        frame.setLocationRelativeTo(null);
        mainView = new MainView();
        frame.add(mainView);
        frame.pack();

        deserializeTaskBoardModel();

        setupMainView();
    }

    private void setupMainView() {
        // Fetch all projects for task board
        for (ProjectModel projectModel: taskBoardModel.getProjects()) {
            mainView.updateProjectsList(projectModel.getName());
        }
        // always show the first project for the board if exists
        if (!taskBoardModel.getProjects().isEmpty()) {
            ProjectModel projectModel = taskBoardModel.getProjects().get(0);
            setupColumnCellViewsForCurrentProject(projectModel);
        }

        path = new File(TaskBoardModel.filename).getAbsolutePath();
        mainView.getSerFileLocationLabel().setText(String.format("Saved file for data is located at: %s", path));

        setupMainViewListeners();
    }

    private void setupMainViewListeners() {
        // setup listeners
        mainView.getProjectComboBox().addActionListener((e) -> {
            int index = mainView.getProjectComboBox().getSelectedIndex();
            ProjectModel model = taskBoardModel.getProjects().get(index);
            setupColumnCellViewsForCurrentProject(model);
        });

        mainView.getEditButton().addActionListener((e) -> {
            // figure out the selected project and edit
            int index = mainView.getProjectComboBox().getSelectedIndex();
            ProjectModel model = taskBoardModel.getProjects().get(index);
            setupProjectView(model);
        });

        mainView.getDeleteButton().addActionListener((e) -> {
            // delete the current existing project
            int index = mainView.getProjectComboBox().getSelectedIndex();
            if (index != -1) {
                ProjectModel model = taskBoardModel.getProjects().get(index);
                taskBoardModel.deleteProject(model);
                mainView.getProjectComboBox().removeItemAt(index);
            }
            serializeTaskBoardModel();
        });

        mainView.getCreateButton().addActionListener((e) -> {
            setupProjectView(null);
        });

        mainView.getLogoutButton().addActionListener((e) -> {
            frame.remove(mainView);
            new LoginController(frame);
            frame.pack();
        });
    }

    private void setupProjectView(ProjectModel model) {
        boolean editable = model != null;

        // setup the project view
        ProjectView projectView = new ProjectView();
        JDialog projectViewDialog = new JDialog();
        projectViewDialog.setLocationRelativeTo(null);
        projectViewDialog.setSize(410, 600);
        projectViewDialog.setTitle("Create New Project");
        projectViewDialog.add(projectView);
        projectViewDialog.setVisible(true);

        // read from our model to populate the view with the right values, if model is null, then create new model
        ProjectModel projectModel =  editable ? model : new ProjectModel();
        projectView.getNameField().setText(projectModel.getName());
        projectModel.addColumn(new Column("TODO"));
        String[] columns = new String[projectModel.getColumns().size()];
        for (int i = 0; i < projectModel.getColumns().size(); i++) {
            columns[i] = projectModel.getColumns().get(i).getName();
        }
        projectView.updateColumns(columns);

        // setup button listeners
        projectView.getSaveButton().addActionListener((event) -> {
            // Fetch everything from the view since it will provide us with the latest values
            projectModel.setName(projectView.getNameField().getText());
            projectModel.getColumns().removeAll(projectModel.getColumns());

            // save existing copies of tasksModels for project model along with new columns created with index preserved
            ArrayList<ArrayList<TaskModel>> taskModels = new ArrayList<>();
            for (String columnName: projectView.getColumns()) {
                Column column = new Column(columnName);
                if (projectModel.getTasksFor(column) == null) {
                    taskModels.add(new ArrayList<>());
                } else {
                    taskModels.add(projectModel.getTasksFor(column));
                }
            }

            // clear out the all columns in existing project since we now have copy
            projectModel.getColumns().removeAll(projectModel.getColumns());
            for (int i = 0; i < taskModels.size(); i++) {
                Column column = new Column(projectView.getColumns()[i]);
                projectModel.addColumn(column);
                projectModel.updateTasksFor(column, taskModels.get(i));
            }

            if (!editable) {
                // user is creating a new project
                System.out.printf("User is creating a project\n");
                taskBoardModel.addProject(projectModel);
                mainView.updateProjectsList(projectModel.getName());
                mainView.getProjectComboBox().setSelectedIndex(taskBoardModel.getProjects().size() - 1);
            } else {
                int selectedIndex = mainView.getProjectComboBox().getSelectedIndex();
                mainView.getProjectComboBox().removeItemAt(selectedIndex);
                mainView.getProjectComboBox().insertItemAt(projectModel.getName(), selectedIndex);
                mainView.getProjectComboBox().setSelectedIndex(selectedIndex);
            }

            setupColumnCellViewsForCurrentProject(projectModel);

            serializeTaskBoardModel();

            projectViewDialog.dispose();
        });

        projectView.getCancelButton().addActionListener((event) -> {
            projectViewDialog.dispose();
        });
    }

    private void setupColumnCellViewsForCurrentProject(ProjectModel projectModel) {
        mainView.initializeColumns(projectModel.getColumns());  // create the columns

        System.out.printf("Setting up columns: %s\n", projectModel.getColumns());
        for (int i = 0; i < projectModel.getColumns().size(); i++) {
            Column column = projectModel.getColumns().get(i);
            mainView.updateTasksFor(column, projectModel.getTasksFor(column));
            ColumnCellView columnCellView = mainView.getColumnCellViews().get(i);
            
            for(int j = 0; j < columnCellView.getTaskCellViews().size(); j++){
            	TaskCellView taskCell = columnCellView.getTaskCellViews().get(j);
            	TaskModel taskModel = projectModel.getTasksFor(column).get(j);
            	taskCell.addMouseListener(new MouseAdapter(){
            		public void mousePressed(MouseEvent e) {
            		    TaskView taskView = new TaskView(projectModel.getColumns());
            		    taskView.setSelectedItem(columnCellView.getColumn());

                         JDialog taskViewDialog = new JDialog();
                         taskViewDialog.setLocationRelativeTo(null);
                         taskViewDialog.setSize(450, 600);
                         taskViewDialog.add(taskView);
                         taskViewDialog.setVisible(true);
                         
                         taskView.setNameField(taskCell.getTitleLabel().getText());
                         taskView.setDescArea(taskCell.getDescriptionLabel().getText());
                         taskView.setBackgroundColor(taskCell.getBackground());
                         taskView.setColorPanel(taskCell.getBackground());
                         taskView.setSelectedItem(column);
                         taskView.updateDateFieldWith(taskModel.getDueDate());
                         
                         taskView.getSaveButton().addActionListener((l) -> {
                        	 taskModel.setName(taskView.getNameField().getText());
                        	 taskModel.setDescription(taskView.getDescArea().getText());
                        	 taskModel.setStatus(taskView.getSelectedColumn());
                        	 try {
                                 taskModel.setDueDate(taskView.getDueDate());
                             } catch (ParseException e1) {
                                 e1.printStackTrace();
                             }
                             taskModel.setBackgroundColor(taskView.getBackgroundColor());
                        	 if (!column.equals(taskModel.getStatus())) {
                        	     projectModel.getTasksFor(column).remove(taskModel);
                        	     projectModel.getTasksFor(taskModel.getStatus()).add(taskModel);
                             }

                             setupColumnCellViewsForCurrentProject(projectModel);

                             serializeTaskBoardModel();
                             taskViewDialog.dispose();
                        	 
                         });
                         
                         taskView.getCancelButton().addActionListener((l) -> {
                             taskViewDialog.dispose();
                         });
            		}
            	});
            }

            System.out.printf("Fetching tasks for %s: %s\n", column.getName(), projectModel.getTasksFor(column));

            // setup listeners
            columnCellView.getCreateTaskButton().addActionListener((e) -> {
                System.out.printf("Create task button selected from column: %s\n", column.getName());
                TaskView taskView = new TaskView(projectModel.getColumns());
                taskView.setSelectedItem(columnCellView.getColumn());

                JDialog taskViewDialog = new JDialog();
                taskViewDialog.setLocationRelativeTo(null);
                taskViewDialog.setSize(450, 600);
                taskViewDialog.add(taskView);
                taskViewDialog.setVisible(true);

                // setup listeners for creating a new task
                taskView.getSaveButton().addActionListener((l) -> {
                    TaskModel taskModel = new TaskModel();
                    taskModel.setName(taskView.getNameField().getText());
                    taskModel.setDescription(taskView.getDescArea().getText());
                    taskModel.setStatus(taskView.getSelectedColumn());
                    try {
                        taskModel.setDueDate(taskView.getDueDate());
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    taskModel.setBackgroundColor(taskView.getBackgroundColor());
                    projectModel.addTaskFor(taskView.getSelectedColumn(), taskModel);
                    setupColumnCellViewsForCurrentProject(projectModel);

                    serializeTaskBoardModel();

                    taskViewDialog.dispose();
                });

                taskView.getCancelButton().addActionListener((l) -> {
                    taskViewDialog.dispose();
                });
            });
        }
    }

    private void deserializeTaskBoardModel() {
        // Deserialization of TaskBoardModel
        try {
            FileInputStream file = new FileInputStream(TaskBoardModel.filename);
            ObjectInputStream in = new ObjectInputStream(file);
            taskBoardModel = (TaskBoardModel) in.readObject();
            in.close();
            file.close();
        } catch(IOException ex) {
            System.out.println("IOException is caught");
            // create a new task model
            taskBoardModel = new TaskBoardModel();
        } catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
            // create a new task model
            taskBoardModel = new TaskBoardModel();
        }
    }

    private void serializeTaskBoardModel() {
        // save new project model into task board
        try {
            FileOutputStream fos = new FileOutputStream(TaskBoardModel.filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskBoardModel);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}