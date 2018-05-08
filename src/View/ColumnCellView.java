package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import Model.Column;
import Model.TaskModel;

public class ColumnCellView extends JPanel{
	private JLabel titleLabel;
	private String title;
	private JButton addTaskButton;
	private JPanel taskCellPanel;
	
	private ArrayList<TaskModel> taskModelList = new ArrayList();
	
	private BoxLayout layout;
	
	public ColumnCellView(String title, ArrayList<Column> columns){
		
		//provide a set width for column
		
		this.title = title;
		
		configureLayout();
		configureTitleLabelLayout();
		configureAddButtonLayout();
		configureTaskCellPanelLayout();
		
		addTaskButton.addActionListener((e) -> {
			ManageTaskView manageTaskView = new ManageTaskView(columns);
			manageTaskView.setSelectedItem(title);
			
			JDialog taskViewDialog = new JDialog();
			taskViewDialog.setLocationRelativeTo(null);
			taskViewDialog.setSize(450,600);
			taskViewDialog.add(manageTaskView);
			taskViewDialog.setVisible(true);
			
			manageTaskView.getCreateButton().addActionListener((l) -> {
				TaskModel taskModel = new TaskModel();
				taskModel.setName(manageTaskView.getNameField().getText());
				taskModel.setDescription(manageTaskView.getDescArea().getText());
				
				try {
					taskModel.setDueDate(manageTaskView.getDueDate());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				taskModel.setCurrentStatus(manageTaskView.getSelectedItem().toString());
				
				taskModelList.add(taskModel);
				
				updateTaskViewCells();
				
				taskViewDialog.dispose();
			});
			
			manageTaskView.getCancelButton().addActionListener((l) -> {
				taskViewDialog.dispose();
			});
		
		});
	}
	
	private void configureLayout(){
		layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
	}
	
	private void configureTitleLabelLayout() {
        titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        titleLabel.setBorder(border);
        titleLabel.setAlignmentX(this.CENTER_ALIGNMENT);
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0,10)));
        
    }
	
	private void configureAddButtonLayout() {
		addTaskButton = new JButton("+");
		addTaskButton.setAlignmentX(this.CENTER_ALIGNMENT);
		add(addTaskButton);
		add(Box.createRigidArea(new Dimension(0,10)));
	}
	
	private void configureTaskCellPanelLayout(){
		taskCellPanel = new JPanel();
		taskCellPanel.setLayout(new BoxLayout(taskCellPanel, BoxLayout.Y_AXIS));
		taskCellPanel.setAlignmentX(this.CENTER_ALIGNMENT);
		
		add(taskCellPanel);
	}
	
	public JLabel getTitleLabel(){
		return titleLabel;
	}
	
	
	
	private void updateTaskViewCells(){
		
		//figure out how to remove prev taskcells
		taskCellPanel.removeAll();
		
		for (TaskModel taskModel : taskModelList){
			TaskCellView taskCellView = new TaskCellView();
			taskCellView.setTitleLabel(taskModel.getName());
			taskCellView.setDescriptionLabel(taskModel.getDescription());
			taskCellView.setDueDateLabel(taskModel.getDueDate().toGMTString().substring(0, 11));
			taskCellPanel.add(taskCellView);
			taskCellPanel.add(Box.createRigidArea(new Dimension(0,15)));
		}
		
		revalidate();
		
	}
}
