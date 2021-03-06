package Controller;

import View.LoginView;

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

                if (username.equals("admin") && password.equals("admin")){
                    frame.remove(loginView);
                    new MainController(frame);
                } else {
                    loginView.getErrorLabel().setVisible(true);
                }
            }
        }
    }
}
