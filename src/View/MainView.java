package View;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import Model.Column;
import Model.TaskModel;

public class MainView extends JPanel{

	private JLabel selectLabel, serFileLocationLabel;
	private JComboBox projectComboBox;
	private JButton editButton, saveButton, deleteButton, loadButton, createButton, logoutButton;
	
    private SpringLayout mainLayout;
    
    private JPanel columnsPanel;

    private ArrayList<ColumnCellView> columnCellViews = new ArrayList<>();
    
	public MainView(){
		this.setBackground(new Color(249, 249, 249));
		
		configureLayout();
		configureSelectLabelLayout();
		configureProjectListLayout();
		configureEditButtonLayout();
		configureSaveButtonLayout();
		configureDeleteButtonLayout();
		configureLogoutButtonLayout();
		configureCreateButtonLayout();
		configureLoadButtonLayout();
		configureSerFileLocationLabelLayout();
		configureColumnsPanelLayout();
	}
	
	private void configureLayout() {
		mainLayout = new SpringLayout();
	    setLayout(mainLayout);
	    
	}
	
	 private void configureSelectLabelLayout() {
		 selectLabel = new JLabel("Select Project:");
	     selectLabel.setFont(new Font("Calibri", Font.BOLD, 20));
	     mainLayout.putConstraint(SpringLayout.WEST, selectLabel, 20, SpringLayout.WEST,  this);
	     mainLayout.putConstraint(SpringLayout.NORTH, selectLabel, 20, SpringLayout.NORTH,  this);
	     add(selectLabel);
	 }
	 
	 private void configureProjectListLayout(){
		 //test combo box list
		 projectComboBox = new JComboBox();
		 projectComboBox.setFont(new Font("Calibri", Font.BOLD, 18));
		 mainLayout.putConstraint(SpringLayout.WEST, projectComboBox, 15, SpringLayout.EAST, selectLabel);
		 mainLayout.putConstraint(SpringLayout.NORTH, projectComboBox, 18, SpringLayout.NORTH, this);
		 add(projectComboBox);
	 }
	 
	 
	 private void configureEditButtonLayout(){
		 editButton = new JButton("Edit");
		 configureButtonStyle(editButton);
		 mainLayout.putConstraint(SpringLayout.WEST, editButton, 20, SpringLayout.EAST,  projectComboBox);
	     mainLayout.putConstraint(SpringLayout.NORTH, editButton, 18, SpringLayout.NORTH,  this);
		 add(editButton);
	 }
	 
	 private void configureSaveButtonLayout(){
		 saveButton = new JButton("Save");
		 configureButtonStyle(saveButton);
		 mainLayout.putConstraint(SpringLayout.WEST, saveButton, 20, SpringLayout.EAST,  editButton);
	     mainLayout.putConstraint(SpringLayout.NORTH, saveButton, 18, SpringLayout.NORTH,  this);
		 add(saveButton);
	 }
	 
	 private void configureDeleteButtonLayout(){
		 deleteButton = new JButton("Delete");
		 configureButtonStyle(deleteButton);
		 mainLayout.putConstraint(SpringLayout.WEST, deleteButton, 20, SpringLayout.EAST,  saveButton);
	     mainLayout.putConstraint(SpringLayout.NORTH, deleteButton, 18, SpringLayout.NORTH,  this);
		 add(deleteButton);
	 }
	 
	 private void configureLoadButtonLayout(){
		 loadButton = new JButton("Load...");
		 configureButtonStyle(loadButton);
		 mainLayout.putConstraint(SpringLayout.EAST, loadButton, -20, SpringLayout.WEST,  createButton);
	     mainLayout.putConstraint(SpringLayout.NORTH, loadButton, 18, SpringLayout.NORTH,  this);
		 add(loadButton);
	 }
	 
	 private void configureCreateButtonLayout(){
		 createButton = new JButton("Create new");
		 configureButtonStyle(createButton);
		 mainLayout.putConstraint(SpringLayout.EAST, createButton, -20, SpringLayout.WEST,  logoutButton);
	     mainLayout.putConstraint(SpringLayout.NORTH, createButton, 18, SpringLayout.NORTH,  this);
		 add(createButton);
	 }
	 
	 private void configureLogoutButtonLayout(){
		 logoutButton = new JButton("Logout");
		 configureButtonStyle(logoutButton);
		 mainLayout.putConstraint(SpringLayout.EAST, logoutButton, -20, SpringLayout.EAST,  this);
	     mainLayout.putConstraint(SpringLayout.NORTH, logoutButton, 18, SpringLayout.NORTH,  this);
		 add(logoutButton);
	 }

	private void configureSerFileLocationLabelLayout() {
		serFileLocationLabel = new JLabel();
		serFileLocationLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		mainLayout.putConstraint(SpringLayout.EAST, serFileLocationLabel, -20, SpringLayout.EAST, this);
		mainLayout.putConstraint(SpringLayout.SOUTH, serFileLocationLabel, -10, SpringLayout.SOUTH, this);
		add(serFileLocationLabel);
	}

	 private void configureColumnsPanelLayout(){
		 
		 columnsPanel = new JPanel();
		 columnsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		 columnsPanel.setOpaque(true);
		 columnsPanel.setBackground(new Color(226, 226, 226));
		  
	     mainLayout.putConstraint(SpringLayout.NORTH, columnsPanel, 10, SpringLayout.SOUTH, editButton);
	     mainLayout.putConstraint(SpringLayout.WEST, columnsPanel, 20, SpringLayout.WEST, this);
	     mainLayout.putConstraint(SpringLayout.EAST, columnsPanel, -20, SpringLayout.EAST, this);
	     mainLayout.putConstraint(SpringLayout.SOUTH, columnsPanel, -10, SpringLayout.NORTH, serFileLocationLabel);

	     add(columnsPanel);
	 }
	 
	 private void configureButtonStyle(JButton button){
		 
		 button.setFont(new Font("Calibri", Font.BOLD, 18));
		 button.setContentAreaFilled(false);
	 }

	public JButton getCreateButton() {
		return createButton;
	}

    public JButton getEditButton() {
        return editButton;
    }

	public JButton getLogoutButton() {
		return logoutButton;
	}

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void updateProjectsList(String project){
		projectComboBox.addItem(project);
	}

	public JComboBox getProjectComboBox() {
		return projectComboBox;
	}

	public JLabel getSerFileLocationLabel() {
		return serFileLocationLabel;
	}

	public ArrayList<ColumnCellView> getColumnCellViews() {
        return columnCellViews;
    }

    public void initializeColumns(ArrayList<Column> columns) {
		columnsPanel.removeAll();
		columnCellViews.removeAll(columnCellViews);	// this will remove any existing listeners on buttons
        for(int i = 0; i < columns.size(); i++){
            ColumnCellView cell = new ColumnCellView(columns.get(i), columns);
            columnCellViews.add(cell);
            columnsPanel.add(cell);
        }
        columnsPanel.revalidate();
        columnsPanel.repaint();
    }

	public void updateTasksFor(Column column, ArrayList<TaskModel> tasks) {
        for (ColumnCellView view: columnCellViews) {
            if (view.getColumn().equals(column)) {
                view.setTaskModelList(tasks);
                break;
            }
        }
		columnsPanel.revalidate();
		columnsPanel.repaint();
    }
}
