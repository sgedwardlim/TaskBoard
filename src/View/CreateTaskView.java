package View;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class CreateTaskView extends JPanel{
	
	private JLabel nameLabel, descLabel, statusLabel, duedateLabel;
	private JComboBox statusList;
	private JButton createButton, cancelButton;
	private JTextField nameField, duedateField;
	private JTextArea descArea;
	
	private SpringLayout createTaskLayout;
	
	public CreateTaskView(){
		configureLayout();
		configureNameLabelLayout();
		configureNameField();
		configureDescLabelLayout();
		configureDescAreaLayout();
		configureStatusLabelLayout();
		configureStatusListLayout();
		configureDueDateLabelLayout();
		configureDueDateField();
		configureCreateButtonLayout();
		configureCancelButtonLayout();
		
	}
	
	private void configureLayout() {
		createTaskLayout = new SpringLayout();
	    setLayout(createTaskLayout);
	    
	}
	
	private void configureNameLabelLayout() {
		 nameLabel = new JLabel("Name");
	     nameLabel.setFont(new Font("Calibri", Font.BOLD, 20));
	     createTaskLayout.putConstraint(SpringLayout.WEST, nameLabel, 20, SpringLayout.WEST,  this);
	     createTaskLayout.putConstraint(SpringLayout.NORTH, nameLabel, 20, SpringLayout.NORTH,  this);
	     add(nameLabel);
	 }
	
	private void configureNameField(){
		nameField = new JTextField();
		nameField.setFont(new Font("Calibri", Font.PLAIN, 18));
		createTaskLayout.putConstraint(SpringLayout.WEST, nameField, 20, SpringLayout.WEST, this);
		createTaskLayout.putConstraint(SpringLayout.NORTH, nameField, 3, SpringLayout.SOUTH,  nameLabel);
		createTaskLayout.putConstraint(SpringLayout.EAST, nameField, -20, SpringLayout.EAST, this);
		add(nameField);
		
	}
	
	private void configureDescLabelLayout() {
		 descLabel = new JLabel("Description");
	     descLabel.setFont(new Font("Calibri", Font.BOLD, 20));
	     createTaskLayout.putConstraint(SpringLayout.WEST, descLabel, 20, SpringLayout.WEST,  this);
	     createTaskLayout.putConstraint(SpringLayout.NORTH, descLabel, 8, SpringLayout.SOUTH,  nameField);
	     add(descLabel);
	}
	
	private void configureDescAreaLayout(){
		descArea = new JTextArea(10, 10);
		descArea.setFont(new Font("Calibri", Font.PLAIN, 18));
		descArea.setLineWrap(true);
		descArea.setWrapStyleWord(true);
		createTaskLayout.putConstraint(SpringLayout.WEST, descArea, 20, SpringLayout.WEST, this);
		createTaskLayout.putConstraint(SpringLayout.NORTH, descArea, 3, SpringLayout.SOUTH,  descLabel);
		createTaskLayout.putConstraint(SpringLayout.EAST, descArea, -20, SpringLayout.EAST, this);
		add(descArea);
		
	}
	
	private void configureStatusLabelLayout() {
		 statusLabel = new JLabel("Status");
	     statusLabel.setFont(new Font("Calibri", Font.BOLD, 20));
	     createTaskLayout.putConstraint(SpringLayout.WEST, statusLabel, 20, SpringLayout.WEST,  this);
	     createTaskLayout.putConstraint(SpringLayout.NORTH, statusLabel, 8, SpringLayout.SOUTH,  descArea);
	     add(statusLabel);
	}
	
	private void configureStatusListLayout(){
		//tests combo box list
		String[] statuses = {"To Do", "In Progress", "Completed"};
		statusList = new JComboBox(statuses);
		statusList.setFont(new Font("Calibri", Font.BOLD, 18));
		createTaskLayout.putConstraint(SpringLayout.WEST, statusList, 20, SpringLayout.WEST, this);
		createTaskLayout.putConstraint(SpringLayout.NORTH, statusList, 3, SpringLayout.SOUTH,  statusLabel);
		createTaskLayout.putConstraint(SpringLayout.EAST, statusList, -20, SpringLayout.EAST, this);
		add(statusList);
	}
	
	private void configureDueDateLabelLayout() {
		 duedateLabel = new JLabel("Due Date");
	     duedateLabel.setFont(new Font("Calibri", Font.BOLD, 20));
	     createTaskLayout.putConstraint(SpringLayout.WEST, duedateLabel, 20, SpringLayout.WEST,  this);
	     createTaskLayout.putConstraint(SpringLayout.NORTH, duedateLabel, 8, SpringLayout.SOUTH,  statusList);
	     add(duedateLabel);
	 }
	
	private void configureDueDateField(){
		duedateField = new JTextField();
		duedateField.setFont(new Font("Calibri", Font.PLAIN, 18));
		createTaskLayout.putConstraint(SpringLayout.WEST, duedateField, 20, SpringLayout.WEST, this);
		createTaskLayout.putConstraint(SpringLayout.NORTH, duedateField, 3, SpringLayout.SOUTH,  duedateLabel);
		createTaskLayout.putConstraint(SpringLayout.EAST, duedateField, -20, SpringLayout.EAST, this);
		add(duedateField);
		
	}
	
	private void configureCreateButtonLayout(){
		createButton = new JButton("Create");
		createButton.setFont(new Font("Calibri", Font.BOLD, 18));
		createTaskLayout.putConstraint(SpringLayout.WEST, createButton, 20, SpringLayout.WEST, this);
		createTaskLayout.putConstraint(SpringLayout.NORTH, createButton, 8, SpringLayout.SOUTH,  duedateField);
		add(createButton);
	}
	
	private void configureCancelButtonLayout(){
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Calibri", Font.BOLD, 18));
		createTaskLayout.putConstraint(SpringLayout.WEST, cancelButton, 5, SpringLayout.EAST, createButton);
		createTaskLayout.putConstraint(SpringLayout.NORTH, cancelButton, 8, SpringLayout.SOUTH,  duedateField);
		add(cancelButton);
	}
	
}
