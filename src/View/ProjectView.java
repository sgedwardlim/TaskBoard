package View;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ProjectView extends JPanel {
    private JLabel nameLabel, columnLabel;
    private JTextField nameField;
    private JButton addColumnButton, createButton, cancelButton;
    private ArrayList<ColumnEditableView> columnEditableViews = new ArrayList();

    private final int columnEditableViewHeight = 40;
    private final int columnEditableViewWidth = 300;

    private SpringLayout layout;

    private JPanel columnEditableViewPanel;

    public ProjectView() {
        configureLayout();
        configureNameLabelLayout();
        configureNameFieldLayout();
        configureColumnLabelLayout();
        configureAddColumnButtonLayout();
        configureColumnEditableViewContainerLayout();
        configureCreateButtonLayout();
        configureCancelButtonLayout();

        // setup listeners
        addColumnButton.addActionListener((e) -> {
            addAndConfigureColumnEditableView(null);
            layout.getConstraints(columnEditableViewPanel).setHeight(Spring.constant(columnEditableViewHeight * columnEditableViews.size()));
            revalidate();
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

    private void configureCreateButtonLayout() {
        createButton = new JButton("Create");
        add(createButton);
        layout.putConstraint(SpringLayout.NORTH, createButton, 0, SpringLayout.SOUTH, columnEditableViewPanel);
        layout.putConstraint(SpringLayout.WEST, createButton, 0, SpringLayout.EAST, columnEditableViewPanel);
    }

    private void configureCancelButtonLayout() {
        cancelButton = new JButton("Cancel");
        add(cancelButton);
        layout.putConstraint(SpringLayout.NORTH, cancelButton, 0, SpringLayout.SOUTH, columnEditableViewPanel);
        layout.putConstraint(SpringLayout.WEST, cancelButton, 0, SpringLayout.EAST, createButton);
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

        revalidate();
    }

    /// Public Methods
    public void updateColumns(String[] columns) {
        columnEditableViews.removeAll(columnEditableViews);
        for (String column: columns) {
            addAndConfigureColumnEditableView(column);
            layout.getConstraints(columnEditableViewPanel).setHeight(Spring.constant(columnEditableViewHeight * columnEditableViews.size()));
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

    public JButton getCreateButton() {
        return createButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    // Helper class that encapsulates a "ColumnEditableView"
    private class ColumnEditableView extends JPanel {
        private JTextField nameField;
        private JButton deleteButton;

        private SpringLayout layout;

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
            layout.putConstraint(SpringLayout.NORTH, nameField, 0, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, nameField, 0, SpringLayout.WEST, this);
        }

        private void configureDeleteButtonLayout() {
            deleteButton = new JButton("-");
            add(deleteButton);
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, deleteButton, 0, SpringLayout.VERTICAL_CENTER, nameField);
            layout.putConstraint(SpringLayout.WEST, deleteButton, 30, SpringLayout.EAST, nameField);
        }
    }
}
