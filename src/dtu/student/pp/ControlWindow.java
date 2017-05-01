package dtu.student.pp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ControlWindow extends JFrame implements ActionListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ControlWindow(){
		
			//Exit button
			getContentPane().setLayout(null);
			JButton hej = new JButton("Exit");
			hej.setBounds(1, 1, 60, 30);
			hej.addActionListener(this);
			getContentPane().add(hej);
	}

	public static void main(String[] args){
		ControlWindow ctrlwindow = new ControlWindow();
		ctrlwindow.setTitle("Project Planner V1.0");
		ctrlwindow.setSize(800, 600);
		ctrlwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ctrlwindow.setVisible(true);
		
			
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
