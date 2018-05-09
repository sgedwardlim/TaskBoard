package View;

import java.awt.*;
import javax.swing.*;

public class LoginView extends JPanel {
 
    private JLabel titleLabel, usernameLabel, passwordLabel, errorLabel, subtitleLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    
    private BackgroundImagePanel leftPanel;
    private SpringLayout leftLayout;
    private JPanel rightPanel;
    private SpringLayout rightLayout;

    private BoxLayout layout;

    public LoginView() {
    	
    	leftPanel = new BackgroundImagePanel();
    	leftLayout = new SpringLayout();
    	leftPanel.setLayout(leftLayout);
    	rightPanel = new JPanel();
    	rightLayout = new SpringLayout();
    	rightPanel.setLayout(rightLayout);
    	rightPanel.setBackground(new Color(240,240,240));
    	
        configureLayout();
        
        add(leftPanel);
        add(rightPanel);
        
        configureTitleLabelLayout();
        configureSubtitleLabelLayout();
        configureUsernameFieldLayout();
        configureUsernameLabelLayout();
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
        layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);
    }

    private void configureTitleLabelLayout() {
        titleLabel = new JLabel("TaskBoard");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 80));
        titleLabel.setForeground(Color.WHITE);
        leftPanel.add(titleLabel);
        leftLayout.putConstraint(SpringLayout.VERTICAL_CENTER, titleLabel, -60, SpringLayout.VERTICAL_CENTER, leftPanel);
        leftLayout.putConstraint(SpringLayout.EAST, titleLabel, -20, SpringLayout.EAST, leftPanel);
        
    }
    
    private void configureSubtitleLabelLayout(){
    	subtitleLabel = new JLabel("a nice subtitle");
    	subtitleLabel.setFont(new Font("Helvetica", Font.PLAIN, 35));
    	subtitleLabel.setForeground(Color.WHITE);
    	leftPanel.add(subtitleLabel);
    	leftLayout.putConstraint(SpringLayout.NORTH, subtitleLabel, -20, SpringLayout.SOUTH, titleLabel);
    	leftLayout.putConstraint(SpringLayout.EAST, subtitleLabel, -20, SpringLayout.EAST, leftPanel);
    }


    private void configureUsernameLabelLayout() {
        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        rightPanel.add(usernameLabel);
        rightLayout.putConstraint(SpringLayout.SOUTH, usernameLabel, -5, SpringLayout.NORTH, usernameField);
        rightLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, usernameLabel, -175, SpringLayout.HORIZONTAL_CENTER, rightPanel);
    }

    private void configureUsernameFieldLayout() {
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(320, 40));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        rightPanel.add(usernameField);
        rightLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, usernameField, -50, SpringLayout.HORIZONTAL_CENTER, rightPanel);
        rightLayout.putConstraint(SpringLayout.VERTICAL_CENTER, usernameField, -65, SpringLayout.VERTICAL_CENTER, rightPanel);
    }

    private void configurePasswordLabelLayout() {
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        rightPanel.add(passwordLabel);
        rightLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 10, SpringLayout.SOUTH, usernameField);
        rightLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordLabel, -175, SpringLayout.HORIZONTAL_CENTER, rightPanel);
    }

    private void configurePasswordFieldLayout() {
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(320, 40));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        rightLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordField, -50, SpringLayout.HORIZONTAL_CENTER, rightPanel);
        rightLayout.putConstraint(SpringLayout.NORTH, passwordField, 5, SpringLayout.SOUTH, passwordLabel);
        rightPanel.add(passwordField);
    }

    private void configureLoginButtonLayout() {
        loginButton = new JButton("Login");
        loginButton.setMaximumSize(new Dimension(100,20));
        loginButton.setBackground(new Color(230, 230, 230));
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        loginButton.setPreferredSize(new Dimension(100, 30));
        rightPanel.add(loginButton);
        rightLayout.putConstraint(SpringLayout.NORTH, loginButton, 20, SpringLayout.SOUTH, passwordField);
        rightLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginButton, -160, SpringLayout.HORIZONTAL_CENTER, rightPanel);
    }

    private void configureErrorLabelLayout() {
        errorLabel = new JLabel("Login incorrect. Please try again");
        errorLabel.setForeground(Color.red);
        errorLabel.setVisible(false);
        rightPanel.add(errorLabel);
        rightLayout.putConstraint(SpringLayout.NORTH, errorLabel, 15, SpringLayout.SOUTH, loginButton); 
        rightLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, errorLabel, 0, SpringLayout.HORIZONTAL_CENTER, rightPanel);
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }
}
