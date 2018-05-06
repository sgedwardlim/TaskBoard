package View;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;

import Model.Column;
import Model.ProjectModel;

public class MainView extends JPanel{

	private JLabel selectLabel;
	private JComboBox projectComboBox;
	private JButton editButton, saveButton, deleteButton, loadButton,
		createButton, logoutButton;
	
    private SpringLayout mainLayout;
    
    private JPanel columnsPanel;
    
	public MainView(){
		
		configureLayout();
		configureSelectLabelLayout();
		configureProjectListLayout();
		configureEditButtonLayout();
		configureSaveButtonLayout();
		configureDeleteButtonLayout();
		configureLogoutButtonLayout();
		configureCreateButtonLayout();
		configureLoadButtonLayout();
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
	 
	 private void configureColumnsPanelLayout(){
		 columnsPanel = new JPanel();
		 columnsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		 columnsPanel.setOpaque(true);
		 columnsPanel.setBackground(Color.lightGray);
		  
	     mainLayout.putConstraint(SpringLayout.NORTH, columnsPanel, 10, SpringLayout.SOUTH, editButton);
	     mainLayout.putConstraint(SpringLayout.WEST, columnsPanel, 20, SpringLayout.WEST, this);
	     mainLayout.putConstraint(SpringLayout.EAST, columnsPanel, -20, SpringLayout.EAST, this);
	     mainLayout.putConstraint(SpringLayout.SOUTH, columnsPanel, -10, SpringLayout.SOUTH, this);

	     add(columnsPanel);
	 }
	 
	 private void configureButtonStyle(JButton button){
		 
		 button.setFont(new Font("Calibri", Font.BOLD, 18));
		 button.setContentAreaFilled(false);
	 }

	public JButton getCreateButton() {
		return createButton;
	}
	
	public void updateProjectsList(String project){
		projectComboBox.addItem(project);
	}

	public JComboBox getProjectComboBox() {
		return projectComboBox;
	}

	public void addColumns(){
	
		List<Column> columnList = ProjectModel.getColumns();
		
		for(int i = 0; i < columnList.size(); i++){
			ColumnCellView cell = new ColumnCellView(columnList.get(i).getName());
			columnsPanel.add(cell);
		}
		
	}
}
