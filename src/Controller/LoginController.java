package Controller;

import View.CreateTaskView;
import View.LoginView;
import View.MainView;
import View.ProjectView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private JFrame frame;
    private LoginView loginView;

    public LoginController(JFrame frame) {
        this.frame = frame;
        loginView = new LoginView();

        frame.add(loginView);
        frame.setTitle("TaskBoard");

        loginView.getLoginButton().addActionListener(new LogInButtonListener());
    }

    private class LogInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginView.getLoginButton()) {
                String username = loginView.getUsernameField().getText();
                String password = loginView.getPasswordField().getText();

                MainView mainView = new MainView();
                CreateTaskView createTaskView = new CreateTaskView();

                // temporary as well to show creation of new project upon login, should only show when user wants to create or edit project
                ProjectView projectView = new ProjectView(frame);
                JDialog projectViewDialog = new JDialog();
                projectViewDialog.setLocationRelativeTo(null);
                projectViewDialog.setSize(500, 600);
                projectViewDialog.setTitle("Create New Project");
                projectViewDialog.add(projectView);
                projectViewDialog.setVisible(true);
//                projectViewDialog.setResizable(false);

                String[] columns = { "TODO", "DEV REVIEW", "DONE"};
                projectView.updateColumns(columns);
                
                //changed log in to present the create task dialog window to test
//                JDialog jd = new JDialog();
//                jd.setLocationRelativeTo(null);
//                jd.setSize(500, 600);
//                jd.setTitle("Create new Task");
//                jd.add(createTaskView);
//                jd.setVisible(true);
//                jd.setResizable(false);

                //frame.remove(loginView);
                //frame.add(createTaskView);
                
                frame.pack();
//                if (username.equals("admin") && password.equals("test")){
//                    MainView mainView = new MainView();
//                    frame.remove(loginView);
//                    frame.add(mainView);
//                    frame.pack();
//                } else {
//                    loginView.getErrorLabel().setVisible(true);
//                }
            }
        }
    }
}
