package View;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Column;

public class TaskView extends JPanel {
	
	private JLabel nameLabel, descLabel, statusLabel, duedateLabel;
	private JComboBox statusList;
	private JButton createButton, cancelButton, colorButton;
	private JTextField nameField, duedateField;
	private JTextArea descArea;
	private JPanel colorPanel;
	private JDatePickerImpl datePicker;
	
	private SpringLayout createTaskLayout;

	private ArrayList<Column> columns;
	private Color backgroundColor;

	public TaskView(ArrayList<Column> columns){
	    this.columns = columns;
	    backgroundColor = Color.WHITE;
	    
		configureLayout();
		configureNameLabelLayout();
		configureNameField();
		configureDescLabelLayout();
		configureDescAreaLayout();
		configureStatusLabelLayout();
		configureStatusListLayout();
		updateStatusList(columns);
		configureDueDateLabelLayout();
		configureDatePickerLayout();
		configureColorButton();
		configureColorPanel();
		configureCreateButtonLayout();
		configureCancelButtonLayout();
		
		colorButton.addActionListener((l)->{
			 backgroundColor=JColorChooser.showDialog(this,"Choose",Color.WHITE); 
			 setColorPanel(backgroundColor); 
		});
			
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
		
		statusList = new JComboBox();
//		updateStatusList();
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
	
	
	private void configureColorButton(){
		colorButton = new JButton("Choose a Color");
		colorButton.setFont(new Font("Calibri", Font.BOLD, 15));
		createTaskLayout.putConstraint(SpringLayout.WEST, colorButton, 30, SpringLayout.EAST, datePicker);
		createTaskLayout.putConstraint(SpringLayout.NORTH, colorButton, 35, SpringLayout.SOUTH, statusList);
		add(colorButton);
	}
	
	private void configureColorPanel(){
		colorPanel = new JPanel();
		colorPanel.setPreferredSize(new Dimension(30,30));
		colorPanel.setBackground(Color.WHITE);
		createTaskLayout.putConstraint(SpringLayout.WEST, colorPanel, 5, SpringLayout.EAST, colorButton);
		createTaskLayout.putConstraint(SpringLayout.NORTH, colorPanel, 35 , SpringLayout.SOUTH, statusList);
		add(colorPanel);
		
	}
	
	private void setColorPanel(Color color){
		colorPanel.setBackground(color);
	}
	
	private void configureCreateButtonLayout(){
		createButton = new JButton("Create");
		createButton.setFont(new Font("Calibri", Font.BOLD, 18));
		createTaskLayout.putConstraint(SpringLayout.WEST, createButton, 20, SpringLayout.WEST, this);
		createTaskLayout.putConstraint(SpringLayout.NORTH, createButton, 10, SpringLayout.SOUTH,  datePicker);
		add(createButton);
	}
	
	private void configureCancelButtonLayout(){
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Calibri", Font.BOLD, 18));
		createTaskLayout.putConstraint(SpringLayout.WEST, cancelButton, 5, SpringLayout.EAST, createButton);
		createTaskLayout.putConstraint(SpringLayout.NORTH, cancelButton, 10, SpringLayout.SOUTH,  datePicker);
		add(cancelButton);
	}
	
	private void configureDatePickerLayout(){
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setFont(new Font("Calibri", Font.BOLD, 18));
		createTaskLayout.putConstraint(SpringLayout.WEST, datePicker, 20, SpringLayout.WEST, this);
		createTaskLayout.putConstraint(SpringLayout.NORTH, datePicker, 3, SpringLayout.SOUTH,  duedateLabel);
		add(datePicker);
		
		
	}
	
	public JButton getCreateButton(){
		return createButton;
	}
	
	public JButton getCancelButton(){
		return cancelButton;
	}
	
	public JTextField getNameField(){
		return nameField;
	}
	
	public JTextArea getDescArea(){
		return descArea;
	}
	
	public JComboBox getStatusList(){
		return statusList;
	}
	
	public Color getBackgroundColor(){
		return backgroundColor;
	}
	
	public Date getDueDate() throws ParseException {
		String sDate =  datePicker.getJFormattedTextField().getText();
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		return date;
	}
	
	public void updateStatusList(List<Column> columns){
		statusList.removeAllItems();
		for (Column column : columns){
			statusList.addItem(column.getName());
		}
	}
	
	public void setSelectedItem(Column column) {
		statusList.setSelectedItem(column.getName());
	}

    public Column getSelectedColumn(){
        for (Column column: columns) {
            if (column.getName().equals(statusList.getSelectedItem().toString())) {
                return column;
            }
        }
        return new Column(statusList.getSelectedItem().toString());
    }
}
