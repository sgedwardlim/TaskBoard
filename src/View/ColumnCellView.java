package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

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
		
		configureLayout();
		configureTitleLabelLayout();
		configureAddButtonLayout();
		configureTaskCellPanelLayout();
	}
	
	private void configureLayout(){
		layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
	}
	
	private void configureTitleLabelLayout() {
        titleLabel = new JLabel(column.getName());
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        titleLabel.setPreferredSize(new Dimension(200, 20));
        titleLabel.setBorder(border);
        titleLabel.setAlignmentX(this.CENTER_ALIGNMENT);
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0,10)));
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
		for (TaskModel taskModel : taskModelList){
			TaskCellView taskCellView = new TaskCellView();
			taskCellView.setTitleLabel(taskModel.getName());
			taskCellView.setDescriptionLabel(taskModel.getDescription());
			taskCellView.setDueDateLabel(taskModel.getDueDate().toGMTString().substring(0, 11));
			taskCellPanel.add(taskCellView);
			taskCellPanel.add(Box.createRigidArea(new Dimension(0,15)));
		}
		revalidate();
		repaint();
	}

    public void setTaskModelList(ArrayList<TaskModel> taskModelList) {
        this.taskModelList = taskModelList;
        updateTaskViewCells();
    }
}
