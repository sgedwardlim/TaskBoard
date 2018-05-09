package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.*;
import javax.swing.border.Border;

import Model.Column;
import Model.TaskModel;

public class ColumnCellView extends JPanel{
	private JLabel titleLabel;
	private Column column;
	private JButton createTaskButton;
	private JPanel taskCellPanel;

    private BoxLayout layout;

	private ArrayList<TaskModel> taskModelList = new ArrayList();
	
	public ColumnCellView(Column column, ArrayList<Column> columns){
		//provide a set width for column
		this.column = column;
		this.setBackground(new Color(249, 249, 249));
		configureLayout();
		configureTitleLabelLayout();
		configureTaskCellPanelLayout();
		configureAddButtonLayout();
	}
	
	private void configureLayout(){
		layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
	}
	
	private void configureTitleLabelLayout() {
		add(Box.createRigidArea(new Dimension(0,8)));
        titleLabel = new JLabel(column.getName());
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 22));
        titleLabel.setAlignmentX(this.CENTER_ALIGNMENT);
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0,8)));
    }
	
	private void configureAddButtonLayout() {
		createTaskButton = new JButton("+");
		createTaskButton.setAlignmentX(this.CENTER_ALIGNMENT);
		add(createTaskButton);
		add(Box.createRigidArea(new Dimension(0,10)));
	}
	
	private void configureTaskCellPanelLayout(){
		taskCellPanel = new JPanel();
		taskCellPanel.setLayout(new BoxLayout(taskCellPanel, BoxLayout.Y_AXIS));
		taskCellPanel.setAlignmentX(this.CENTER_ALIGNMENT);
		taskCellPanel.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7)); 
		taskCellPanel.setBackground(new Color(249, 249, 249));
		add(taskCellPanel);
	}
	
	public JLabel getTitleLabel(){
		return titleLabel;
	}

    public Column getColumn() {
        return column;
    }

    public JButton getCreateTaskButton() {
        return createTaskButton;
    }

    private void updateTaskViewCells() {
		taskCellPanel.removeAll();
		Collections.sort(taskModelList);
		for (TaskModel taskModel : taskModelList){
			TaskCellView taskCellView = new TaskCellView();
			taskCellView.setTitleLabel(taskModel.getName());
			taskCellView.setDescriptionLabel(taskModel.getDescription());
			taskCellView.setDueDateLabel(taskModel.getDueDate().toGMTString().substring(0, 11));
			taskCellView.setBackground(taskModel.getBackgroundColor());
			taskCellPanel.add(taskCellView);
			taskCellPanel.add(Box.createRigidArea(new Dimension(0,15)));
			
			/*
			taskCellView.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					System.out.println("Task Clicked");
				}
			});
			*/
			
		}
		revalidate();
		repaint();
	}

    public void setTaskModelList(ArrayList<TaskModel> taskModelList) {
        this.taskModelList = taskModelList;
        updateTaskViewCells();
    }
}
