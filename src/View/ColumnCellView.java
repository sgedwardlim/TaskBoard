package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

public class ColumnCellView extends JPanel{
	private JLabel titleLabel;
	private JButton addTaskButton;
	
	private BoxLayout layout;
	
	public ColumnCellView(String title){
		
		this.setPreferredSize(new Dimension(150, 100));
		
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
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        titleLabel.setBorder(border);
        titleLabel.setAlignmentX(this.CENTER_ALIGNMENT);
        add(titleLabel);
    }
	
	private void configureAddButtonLayout() {
		addTaskButton = new JButton("+");
		addTaskButton.setAlignmentX(this.CENTER_ALIGNMENT);
		add(addTaskButton);
	}
	
	//include method to add task cells?
}
