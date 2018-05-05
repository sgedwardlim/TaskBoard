package Controller;

import Model.ProjectModel;
import View.MainView;
import View.ProjectView;

import javax.swing.*;

public class MainController {
    private JFrame frame;
    private MainView mainView;

    public MainController(JFrame frame) {
        this.frame = frame;

        frame.setLocationRelativeTo(null);
        mainView = new MainView();
        frame.add(mainView);
        frame.pack();

        // setup listeners
        mainView.getCreateButton().addActionListener((e) -> {
            setupProjectView();
        });
    }

    private void setupProjectView() {
        // setup the project view
        ProjectView projectView = new ProjectView();
        JDialog projectViewDialog = new JDialog();
        projectViewDialog.setLocationRelativeTo(null);
        projectViewDialog.setSize(500, 600);
        projectViewDialog.setTitle("Create New Project");
        projectViewDialog.add(projectView);
        projectViewDialog.setVisible(true);

        // read from our model to populate the view with the right values
        ProjectModel projectModel = new ProjectModel();
        // populate the name field
        projectView.getNameField().setText(projectModel.getName());
        // populate the columns field
        String[] columns = new String[projectModel.getColumns().size()];
        for (int i = 0; i < projectModel.getColumns().size(); i++) {
            columns[i] = projectModel.getColumns().get(i).getName();
        }
        projectView.updateColumns(columns);

        projectView.getCreateButton().addActionListener((event) -> {
            // save the name
            projectModel.setName(projectView.getNameField().getText());
            // save the columns
            mainView.updateProjectsList(projectView.getNameField().getText());
            for (String column: projectView.getColumns()) {
                projectModel.addColumn(column);
            }
            projectViewDialog.dispose();
        });

        projectView.getCancelButton().addActionListener((event) -> {
            projectViewDialog.dispose();
        });
    }
}
