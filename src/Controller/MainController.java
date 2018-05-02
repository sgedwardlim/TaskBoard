package Controller;

import View.MainView;
import View.ProjectView;

import javax.swing.*;

public class MainController {
    private JFrame frame;

    public MainController(JFrame frame) {
        this.frame = frame;

        MainView mainView = new MainView();
        frame.add(mainView);
        frame.pack();

        // setup listeners
        mainView.getCreateButton().addActionListener((e) -> {
            ProjectView projectView = new ProjectView();
            JDialog projectViewDialog = new JDialog();
            projectViewDialog.setLocationRelativeTo(null);
            projectViewDialog.setSize(500, 600);
            projectViewDialog.setTitle("Create New Project");
            projectViewDialog.add(projectView);
            projectViewDialog.setVisible(true);

            String[] columns = { "TODO", "DEV REVIEW", "DONE"};
            projectView.updateColumns(columns);
        });
    }
}
