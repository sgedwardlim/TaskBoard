package Controller;

import Model.Column;
import Model.ProjectModel;
import Model.TaskBoardModel;
import Model.TaskModel;
import View.ColumnCellView;
import View.MainView;
import View.ProjectView;
import View.TaskView;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;

public class MainController {
    private JFrame frame;
    private MainView mainView;

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
            // delete the current existing project,
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

    private void setupColumnCellViewsForCurrentProject(ProjectModel projectModel) {
        mainView.initializeColumns(projectModel.getColumns());  // create the columns

        System.out.printf("Setting up columns: %s\n", projectModel.getColumns());
        for (int i = 0; i < projectModel.getColumns().size(); i++) {
            Column column = projectModel.getColumns().get(i);
            mainView.updateTasksFor(column, projectModel.getTasksFor(column));
            ColumnCellView columnCellView = mainView.getColumnCellViews().get(i);

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
                taskView.getCreateButton().addActionListener((l) -> {
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
                    columnCellView.setTaskModelList(projectModel.getTasksFor(taskView.getSelectedColumn()));

                    serializeTaskBoardModel();

                    taskViewDialog.dispose();
                });

                taskView.getCancelButton().addActionListener((l) -> {
                    taskViewDialog.dispose();
                });
            });
        }
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

        setupMainViewListeners();
    }

    private void setupProjectView(ProjectModel model) {
        boolean editable = model != null;

        // setup the project view
        ProjectView projectView = new ProjectView();
        JDialog projectViewDialog = new JDialog();
        projectViewDialog.setLocationRelativeTo(null);
        projectViewDialog.setSize(500, 600);
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
            projectModel.setName(projectView.getNameField().getText());
            projectModel.getColumns().removeAll(projectModel.getColumns());
            for (String column: projectView.getColumns()) {
                projectModel.addColumn(new Column(column));
            }
            if (!editable) {
                // user is creating a new project
                System.out.printf("User is creating a project");
                taskBoardModel.addProject(projectModel);
                mainView.updateProjectsList(projectModel.getName());
            }
            setupColumnCellViewsForCurrentProject(projectModel);

            serializeTaskBoardModel();

            projectViewDialog.dispose();
        });

        projectView.getCancelButton().addActionListener((event) -> {
            projectViewDialog.dispose();
        });
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