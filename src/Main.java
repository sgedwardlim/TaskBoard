import View.LoginView;
import View.MainView;

import java.awt.BorderLayout;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1800, 1000);
        LoginView loginView = new LoginView();
        //frame.add(loginView);
        MainView mainView = new MainView();
        frame.add(mainView);
 

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TaskBoard");
        
        //frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        
    }
}
