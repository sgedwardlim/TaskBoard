package View;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    private JPanel containerPanel;
    private JLabel titleLabel, usernameLabel, passwordLabel, errorLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private SpringLayout layout;

    public LoginView() {
        configureLayout();
        configureTitleLabelLayout();
        configureContainerPanelLayout();
        configureUsernameLabelLayout();
        configureUsernameFieldLayout();
        configurePasswordLabelLayout();
        configurePasswordFieldLayout();
        configureLoginButtonLayout();
        configureErrorLabelLayout();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void configureLayout() {
        layout = new SpringLayout();
        setLayout(layout);
    }

    private void configureTitleLabelLayout() {
        titleLabel = new JLabel("Task Board Login");
        add(titleLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER, this);
    }

    private void configureContainerPanelLayout() {
        containerPanel = new JPanel();
        GridLayout containerGridLayout = new GridLayout(2, 2);
        containerPanel.setLayout(containerGridLayout);
        add(containerPanel);

        layout.putConstraint(SpringLayout.NORTH, containerPanel, 0, SpringLayout.SOUTH, titleLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, containerPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
    }

    private void configureUsernameLabelLayout() {
        usernameLabel = new JLabel("Username");
        containerPanel.add(usernameLabel);
    }

    private void configureUsernameFieldLayout() {
        usernameField = new JTextField();
        containerPanel.add(usernameField);
    }

    private void configurePasswordLabelLayout() {
        passwordLabel = new JLabel("Password");
        containerPanel.add(passwordLabel);
    }

    private void configurePasswordFieldLayout() {
        passwordField = new JPasswordField();
        containerPanel.add(passwordField);
    }

    private void configureLoginButtonLayout() {
        loginButton = new JButton("Login");
        loginButton.setMaximumSize(new Dimension(100,20));
        add(loginButton);
        layout.putConstraint(SpringLayout.NORTH, loginButton, 0, SpringLayout.SOUTH, containerPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginButton, 0, SpringLayout.HORIZONTAL_CENTER, containerPanel);
    }

    private void configureErrorLabelLayout() {
        errorLabel = new JLabel("Login incorrect. Please try again");
        errorLabel.setForeground(Color.red);
        add(errorLabel);
        layout.putConstraint(SpringLayout.NORTH, errorLabel, 0, SpringLayout.SOUTH, loginButton);
        layout.putConstraint(SpringLayout.SOUTH, errorLabel, 0, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, errorLabel, 0, SpringLayout.HORIZONTAL_CENTER, loginButton);
    }
}
