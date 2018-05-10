package View;

import Model.Column;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ProjectView extends JPanel {
    private JLabel nameLabel, columnLabel;
    private JTextField nameField;
    private JButton addColumnButton, saveButton, cancelButton, upButton, downButton;
    private ArrayList<ColumnEditableView> columnEditableViews = new ArrayList();

    private final int columnEditableViewHeight = 40;
    private final int columnEditableViewWidth = 200;

    private SpringLayout layout;

    private JPanel columnEditableViewPanel;

    private int lastSelectedIndex = -1;

    public ProjectView() {
        configureLayout();
        configureNameLabelLayout();
        configureNameFieldLayout();
        configureColumnLabelLayout();
        configureAddColumnButtonLayout();
        configureColumnEditableViewContainerLayout();
        configureUpButtonLayout();
        configureDownButtonLayout();
        configureCreateButtonLayout();
        configureCancelButtonLayout();

        // setup listeners
        addColumnButton.addActionListener((e) -> {
            addAndConfigureColumnEditableView(null);
            layout.getConstraints(columnEditableViewPanel).setHeight(Spring.constant(columnEditableViewHeight * columnEditableViews.size()));
            revalidate();
        });

        upButton.addActionListener((e) -> {
            for (int i = 0; i < columnEditableViews.size(); i++) {
                if (columnEditableViews.get(i).selected) {
                    lastSelectedIndex = i;
                    break;
                }
            }

            if (lastSelectedIndex >= 1 && lastSelectedIndex != -1) {
                String[] columns = getColumns();
                String temp = columns[lastSelectedIndex];
                columns[lastSelectedIndex] = columns[lastSelectedIndex - 1];
                columns[lastSelectedIndex - 1] = temp;
                --lastSelectedIndex;
                updateColumns(columns);
            }
        });

        downButton.addActionListener((e) -> {
            for (int i = 0; i < columnEditableViews.size(); i++) {
                if (columnEditableViews.get(i).selected) {
                    lastSelectedIndex = i;
                    break;
                }
            }

            if (lastSelectedIndex < columnEditableViews.size() - 1 && lastSelectedIndex != -1) {
                String[] columns = getColumns();
                String temp = columns[lastSelectedIndex];
                columns[lastSelectedIndex] = columns[lastSelectedIndex + 1];
                columns[lastSelectedIndex + 1] = temp;
                ++lastSelectedIndex;
                updateColumns(columns); 
            }

        });
    }

    private void configureLayout() {
        layout = new SpringLayout();
        setLayout(layout);
    }

    private void configureNameLabelLayout() {
        nameLabel = new JLabel("Name");
        add(nameLabel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 20, SpringLayout.WEST, this);
    }

    private void configureNameFieldLayout() {
        nameField = new JTextField();
        add(nameField);
        layout.getConstraints(nameField).setWidth(Spring.constant(160));
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, nameField, 0, SpringLayout.VERTICAL_CENTER, nameLabel);
        layout.putConstraint(SpringLayout.WEST, nameField, 20, SpringLayout.EAST, nameLabel);
    }

    private void configureColumnLabelLayout() {
        columnLabel = new JLabel("Columns");
        add(columnLabel);
        layout.putConstraint(SpringLayout.NORTH, columnLabel, 10, SpringLayout.SOUTH, nameField);
        layout.putConstraint(SpringLayout.WEST, columnLabel, 20, SpringLayout.WEST, this);
    }

    private void configureAddColumnButtonLayout() {
        addColumnButton = new JButton("+");
        add(addColumnButton);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, addColumnButton, 0, SpringLayout.VERTICAL_CENTER, columnLabel);
        layout.putConstraint(SpringLayout.WEST, addColumnButton, 30, SpringLayout.EAST, columnLabel);
    }

    private void configureColumnEditableViewContainerLayout() {
        columnEditableViewPanel = new JPanel();
        columnEditableViewPanel.setLayout(new BoxLayout(columnEditableViewPanel, BoxLayout.Y_AXIS));

        add(columnEditableViewPanel);
        layout.getConstraints(columnEditableViewPanel).setWidth(Spring.constant(columnEditableViewWidth));
        layout.getConstraints(columnEditableViewPanel).setHeight(Spring.constant(columnEditableViewHeight));
        layout.putConstraint(SpringLayout.NORTH, columnEditableViewPanel, 10, SpringLayout.SOUTH, addColumnButton);
        layout.putConstraint(SpringLayout.WEST, columnEditableViewPanel, 20, SpringLayout.WEST, this);
    }

    private void configureUpButtonLayout() {
        BufferedImage buttonIcon = null;
        try {
            buttonIcon = ImageIO.read(new File("Assets/up.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        upButton = new JButton(new ImageIcon(buttonIcon.getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
        upButton.setText("Move up");
        add(upButton);
        layout.getConstraints(upButton).setWidth(Spring.constant(100));
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, upButton, 0, SpringLayout.VERTICAL_CENTER, columnEditableViewPanel);
        layout.putConstraint(SpringLayout.WEST, upButton, 30, SpringLayout.EAST, columnEditableViewPanel);
    }

    private void configureDownButtonLayout() {
        BufferedImage buttonIcon = null;
        try {
            buttonIcon = ImageIO.read(new File("Assets/down.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        downButton = new JButton(new ImageIcon(buttonIcon.getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
        downButton.setText("Move down");
        add(downButton);
        layout.getConstraints(downButton).setWidth(Spring.constant(100));
        layout.putConstraint(SpringLayout.NORTH, downButton, 4, SpringLayout.SOUTH, upButton);
        layout.putConstraint(SpringLayout.WEST, downButton, 0, SpringLayout.WEST, upButton);
    }

    private void configureCreateButtonLayout() {
        saveButton = new JButton("Save");
        add(saveButton);
        layout.putConstraint(SpringLayout.NORTH, saveButton, 30, SpringLayout.SOUTH, downButton);
        layout.putConstraint(SpringLayout.WEST, saveButton, 0, SpringLayout.WEST, downButton);
    }

    private void configureCancelButtonLayout() {
        cancelButton = new JButton("Cancel");
        add(cancelButton);
        layout.putConstraint(SpringLayout.NORTH, cancelButton, 0, SpringLayout.NORTH, saveButton);
        layout.putConstraint(SpringLayout.WEST, cancelButton, 0, SpringLayout.EAST, saveButton);
        layout.putConstraint(SpringLayout.EAST, cancelButton, 0, SpringLayout.EAST, this);
    }

    private void addAndConfigureColumnEditableView(String title) {
        ColumnEditableView view = new ColumnEditableView();
        if (title != null) { view.nameField.setText(title); }
        columnEditableViewPanel.add(view);
        columnEditableViews.add(view);
        layout.getConstraints(columnEditableViewPanel).setHeight(Spring.constant(columnEditableViewHeight * columnEditableViews.size()));

        // listen to delete calls from cell
        view.deleteButton.addActionListener((e) -> {
            // determine which was selected and remove from list
            JButton button = (JButton) e.getSource();
            for (int i = 0; i < columnEditableViews.size(); i++) {
                if (columnEditableViews.get(i).deleteButton == button) {
                    columnEditableViewPanel.remove(columnEditableViews.get(i));
                    columnEditableViews.remove(i);
                    columnEditableViews.trimToSize();
                    break;
                }
            }
            layout.getConstraints(columnEditableViewPanel).setHeight(Spring.constant(columnEditableViewHeight * columnEditableViews.size()));
            revalidate();
        });

        view.nameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                for (ColumnEditableView columnView: columnEditableViews) {
                    columnView.selected = false;
                    columnView.setBackground(null);
                }
                view.setBackground(Color.blue);
                view.selected = true;
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });

        revalidate();
    }

    /// Public Methods
    public void updateColumns(String[] columns) {
        columnEditableViewPanel.removeAll();
        columnEditableViews.removeAll(columnEditableViews);
        for (String column: columns) {
            addAndConfigureColumnEditableView(column);
            layout.getConstraints(columnEditableViewPanel).setHeight(Spring.constant(columnEditableViewHeight * columnEditableViews.size()));
        }

        if (lastSelectedIndex != -1) {
            columnEditableViews.get(lastSelectedIndex).setBackground(Color.blue);
        }
        revalidate();
    }

    public JTextField getNameField() {
        return nameField;
    }

    public String[] getColumns() {
        String[] columns = new String[columnEditableViews.size()];
        for (int i = 0; i < columnEditableViews.size(); i++) {
            columns[i] = columnEditableViews.get(i).nameField.getText();
        }
        return columns;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    // Helper class that encapsulates a "ColumnEditableView"
    private class ColumnEditableView extends JPanel {
        private JTextField nameField;
        private JButton deleteButton;

        private SpringLayout layout;

        public boolean selected = false;

        ColumnEditableView() {
            configureLayout();
            configureNameFieldLayout();
            configureDeleteButtonLayout();
        }

        private void configureLayout() {
            layout = new SpringLayout();
            setLayout(layout);
        }

        private void configureNameFieldLayout() {
            nameField = new JTextField();
            add(nameField);
            layout.getConstraints(nameField).setWidth(Spring.constant(160));
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, nameField, 0, SpringLayout.VERTICAL_CENTER, this);
            layout.putConstraint(SpringLayout.WEST, nameField, 0, SpringLayout.WEST, this);
        }

        private void configureDeleteButtonLayout() {
            deleteButton = new JButton("-");
            add(deleteButton);
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, deleteButton, 0, SpringLayout.VERTICAL_CENTER, nameField);
            layout.putConstraint(SpringLayout.WEST, deleteButton, 5, SpringLayout.EAST, nameField);
            layout.putConstraint(SpringLayout.EAST, deleteButton, -5, SpringLayout.EAST, this);
        }
    }
}
