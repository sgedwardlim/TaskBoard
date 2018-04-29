package View;

import javax.swing.*;
import java.awt.*;

interface TaskCellViewInterface {
    JLabel getTitleLabel();
    JLabel getDescriptionLabel();
    JLabel getDueDateLabel();
}

public class TaskCellView extends JPanel implements TaskCellViewInterface {
    private JLabel titleLabel, descriptionLabel, dueLiteralLabel, dueDateLabel;

    private SpringLayout layout;

    public TaskCellView() {
        configureLayout();
        configureTitleLabelLayout();
        configureDescriptionLabelLayout();
        configureUsernameFieldLayout();
        configureDueLiteralLabelLayout();
        configureDueDateLabelLayout();
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
        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        add(titleLabel);
        layout.putConstraint(SpringLayout.WEST, titleLabel, 0, SpringLayout.WEST, this);
    }

    private void configureDescriptionLabelLayout() {
        descriptionLabel = new JLabel();
        layout.putConstraint(SpringLayout.NORTH, descriptionLabel, 0, SpringLayout.SOUTH, titleLabel);
        layout.putConstraint(SpringLayout.WEST, descriptionLabel, 0, SpringLayout.WEST, this);
    }

    private void configureUsernameFieldLayout() {
        descriptionLabel = new JLabel();
        layout.putConstraint(SpringLayout.NORTH, descriptionLabel, 0, SpringLayout.SOUTH, titleLabel);
        layout.putConstraint(SpringLayout.WEST, descriptionLabel, 0, SpringLayout.WEST, this);
    }

    private void configureDueLiteralLabelLayout() {
        dueLiteralLabel = new JLabel("Due:");
        layout.putConstraint(SpringLayout.NORTH, dueLiteralLabel, 0, SpringLayout.SOUTH, descriptionLabel);
        layout.putConstraint(SpringLayout.WEST, dueLiteralLabel, 0, SpringLayout.WEST, this);
    }

    private void configureDueDateLabelLayout() {
        dueDateLabel = new JLabel();
        layout.putConstraint(SpringLayout.NORTH, dueDateLabel, 0, SpringLayout.SOUTH, dueLiteralLabel);
        layout.putConstraint(SpringLayout.WEST, dueDateLabel, 0, SpringLayout.WEST, this);
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
}
