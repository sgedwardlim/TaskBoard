package View;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

public class MainView extends JPanel{

	private JLabel selectLabel;
	private JComboBox projectList;
	private JButton editButton, saveButton, deleteButton, loadButton,
		createButton, logoutButton;
	
    private FlowLayout mainLayout;
   

	
	public MainView(){
		configureLayout();
		configureSelectLabelLayout();
		configureProjectListLayout();
		configureEditButtonLayout();
		configureSaveButtonLayout();
		configureDeleteButtonLayout();
		configureLoadButtonLayout();
		configureCreateButtonLayout();
		configureLogoutButtonLayout();
		
	}
	
	private void configureLayout() {
		mainLayout = new FlowLayout(FlowLayout.LEFT, 10, 0);
	    setLayout(mainLayout);
	    
	}
	
	 private void configureSelectLabelLayout() {
		 selectLabel = new JLabel("Select Project");
	     selectLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
	     add(selectLabel);
	 }
	 
	 private void configureProjectListLayout(){
		 //test combo box list
		 String[] projects = {"Project 1", "Project 2", "Project 3"};
		 projectList = new JComboBox(projects);
		 add(projectList);
	 }
	 
	 private void configureEditButtonLayout(){
		 editButton = new JButton("Edit");
		 add(editButton);
	 }
	 
	 private void configureSaveButtonLayout(){
		 saveButton = new JButton("Save");
		 add(saveButton);
	 }
	 
	 private void configureDeleteButtonLayout(){
		 deleteButton = new JButton("Delete");
		 add(deleteButton);
	 }
	 
	 private void configureLoadButtonLayout(){
		 loadButton = new JButton("Load...");
		 add(loadButton);
	 }
	 
	 private void configureCreateButtonLayout(){
		 createButton = new JButton("Create new");
		 add(createButton);
	 }
	 
	 private void configureLogoutButtonLayout(){
		 logoutButton = new JButton("Logout");
		 add(logoutButton);
	 }

	
}
