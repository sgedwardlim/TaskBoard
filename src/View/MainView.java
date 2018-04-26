package View;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

public class MainView extends JPanel{

	private JLabel selectLabel;
	private JComboBox projectList;
	private JButton editButton, saveButton, deleteButton, loadButton,
		createButton, logoutButton;
	
    private SpringLayout mainLayout;
   

	
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
		 String[] projects = {"Project 1", "Project 2", "Project 3"};
		 projectList = new JComboBox(projects);
		 projectList.setFont(new Font("Calibri", Font.BOLD, 18));
		 mainLayout.putConstraint(SpringLayout.WEST, projectList, 15, SpringLayout.EAST, selectLabel);
		 mainLayout.putConstraint(SpringLayout.NORTH, projectList, 18, SpringLayout.NORTH, this);
		 add(projectList);
	 }
	 
	 
	 private void configureEditButtonLayout(){
		 editButton = new JButton("Edit");
		 configureButtonStyle(editButton);
		 mainLayout.putConstraint(SpringLayout.WEST, editButton, 20, SpringLayout.EAST,  projectList);
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
	 
	 private void configureButtonStyle(JButton button){
		 
		 button.setFont(new Font("Calibri", Font.BOLD, 18));
		 //button.setBackground(java.awt.Color.WHITE);
		 button.setContentAreaFilled(false);
	 }
	
	
}
