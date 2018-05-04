import Controller.LoginController;
import View.LoginView;
import View.MainView;

import java.awt.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new LoginController(frame);
        // set the jframe size and location, and make it visible
        frame.setPreferredSize(new Dimension(1200, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
