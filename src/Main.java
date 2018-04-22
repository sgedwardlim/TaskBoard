import View.LoginView;

import java.awt.BorderLayout;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1000, 700);
        LoginView loginView = new LoginView();
        frame.add(loginView);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TaskBoard");
        
        //pack() isn't working
        //frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
        
    }
}
