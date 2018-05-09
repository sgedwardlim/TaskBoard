package View;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class TaskCellView extends JPanel {
    private JLabel titleLabel, descriptionLabel, dueLiteralLabel, dueDateLabel;
    private BoxLayout layout;
 

    public TaskCellView() {
    	
    	this.setPreferredSize(new Dimension(175,100));
    	this.setBackground(Color.WHITE);
    	this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        configureLayout();
        configureTitleLabelLayout();
        configureDescriptionLabelLayout();  
        //configureUsernameFieldLayout();
        configureDueLiteralLabelLayout();
        configureDueDateLabelLayout();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void configureLayout() {
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
    }

    private void configureTitleLabelLayout() {
    	add(Box.createRigidArea(new Dimension(0,3)));
        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        //layout.putConstraint(SpringLayout.WEST, titleLabel, 0, SpringLayout.WEST, this);
        add(titleLabel);
        add(Box.createHorizontalGlue());
    }

    private void configureDescriptionLabelLayout() {
        descriptionLabel = new JLabel();
        descriptionLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
        //layout.putConstraint(SpringLayout.NORTH, descriptionLabel, 0, SpringLayout.SOUTH, titleLabel);
        //layout.putConstraint(SpringLayout.WEST, descriptionLabel, 0, SpringLayout.WEST, this);
        add(descriptionLabel);
        add(Box.createRigidArea(new Dimension(0,8)));
    }

    private void configureUsernameFieldLayout() {
        descriptionLabel = new JLabel();
        //layout.putConstraint(SpringLayout.NORTH, descriptionLabel, 0, SpringLayout.SOUTH, titleLabel);
        //layout.putConstraint(SpringLayout.WEST, descriptionLabel, 0, SpringLayout.WEST, this);
    }

    private void configureDueLiteralLabelLayout() {
        dueLiteralLabel = new JLabel("Due:");
        dueLiteralLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        //layout.putConstraint(SpringLayout.NORTH, dueLiteralLabel, 0, SpringLayout.SOUTH, descriptionLabel);
        //layout.putConstraint(SpringLayout.WEST, dueLiteralLabel, 0, SpringLayout.WEST, this);
        add(dueLiteralLabel);
    }

    private void configureDueDateLabelLayout() {
        dueDateLabel = new JLabel();
        //layout.putConstraint(SpringLayout.NORTH, dueDateLabel, 0, SpringLayout.SOUTH, dueLiteralLabel);
        //layout.putConstraint(SpringLayout.WEST, dueDateLabel, 0, SpringLayout.WEST, this);
        add(dueDateLabel);
        add(Box.createRigidArea(new Dimension(0,3)));
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JLabel getDescriptionLabel() {
        return descriptionLabel;
    }

    public JLabel getDueDateLabel() {
        return dueDateLabel;
    }
    
    public void setTitleLabel(String title){
    	titleLabel.setText(title);
    }
    
    public void setDescriptionLabel(String desc){
    	descriptionLabel.setText(desc);
    }
    
    public void setDueDateLabel(String date){
    	dueDateLabel.setText(date);
    }
}
