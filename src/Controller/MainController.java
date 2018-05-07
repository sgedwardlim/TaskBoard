package Controller;

import Model.ProjectModel;
import Model.TaskBoardModel;
import View.MainView;
import View.ProjectView;

import javax.swing.*;
import java.io.*;

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

        // Load from taskboardmodel
        for (ProjectModel projectModel: taskBoardModel.getProjects()) {
            mainView.updateProjectsList(projectModel.getName());
        }
        // always show the first
        if (taskBoardModel.getProjects().size() != 0) {
            mainView.addColumns(taskBoardModel.getProjects().get(0).getColumns());
        }

        // setup listeners
        mainView.getEditButton().addActionListener((e) -> {
            // figure out the selected project and edit
            int index = mainView.getProjectComboBox().getSelectedIndex();
            ProjectModel model = taskBoardModel.getProjects().get(index);
            setupProjectView(model);
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
        projectViewDialog.setSize(500, 600);
        projectViewDialog.setTitle("Create New Project");
        projectViewDialog.add(projectView);
        projectViewDialog.setVisible(true);

        // read from our model to populate the view with the right values, if model is null, then create new model
        ProjectModel projectModel =  editable ? model : new ProjectModel();
        projectView.getNameField().setText(projectModel.getName());
        projectModel.addColumn("TODO");
        String[] columns = new String[projectModel.getColumns().size()];
        for (int i = 0; i < projectModel.getColumns().size(); i++) {
            columns[i] = projectModel.getColumns().get(i).getName();
        }
        projectView.updateColumns(columns);

        // setup button listeners
        projectView.getSaveButton().addActionListener((event) -> {
            projectModel.setName(projectView.getNameField().getText());
            for (String column: projectView.getColumns()) {
                projectModel.addColumn(column);
            }
            if (!editable) {
                taskBoardModel.addProject(projectModel);
            }

            // save new project model into task board
            try {
                FileOutputStream fos = new FileOutputStream(TaskBoardModel.filename);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(taskBoardModel);
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mainView.updateProjectsList(projectView.getNameField().getText());
            mainView.addColumns(projectModel.getColumns());
            projectViewDialog.dispose();
        });

        projectView.getCancelButton().addActionListener((event) -> {
            projectViewDialog.dispose();
        });
    }
}