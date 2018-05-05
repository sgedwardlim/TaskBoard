package View;

import java.awt.Font;

import javax.swing.*;

public class ColumnCellView extends JPanel{
	private JLabel titleLabel;
	private JButton addTaskButton;
	
	private BoxLayout layout;
	
	public ColumnCellView(String title){
		
		configureLayout();
		configureTitleLabelLayout(title);
		configureAddButtonLayout();
		
		addTaskButton.addActionListener((e) -> {
			ManageTaskView manageTaskView = new ManageTaskView();
			JDialog taskViewDialog = new JDialog();
			taskViewDialog.setLocationRelativeTo(null);
			taskViewDialog.setSize(450,600);
			taskViewDialog.add(manageTaskView);
			taskViewDialog.setVisible(true);
		});
	}
	
	private void configureLayout(){
		layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
	}
	
	private void configureTitleLabelLayout(String title) {
        titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        add(titleLabel);
    }
	
	private void configureAddButtonLayout() {
		addTaskButton = new JButton("+");
		add(addTaskButton);
	}
	
	//include method to add task cells?
}
