package dtu.student.pp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dtu.student.pp.ui.Menu;
import dtu.student.pp.ui.Table1;

public class ControlWindow2 extends JFrame implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ControlWindow2(ProjectPlanner planner){
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle("Project Planner V1.0");
		setMinimumSize(new Dimension(500, 370));
		
		JPanel mainPane = new JPanel(new BorderLayout(0, 0));
		
		Table1 table = new Table1(planner.getAllActivities());
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		mainPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel menuHolder = new JPanel(new BorderLayout());
		mainPane.add(menuHolder, BorderLayout.WEST);
		
		//menuHolder.add(menuPane, BorderLayout.NORTH);
		menuHolder.add(new Menu(), BorderLayout.NORTH);
		
		
		setContentPane(mainPane);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		//Exit button
		//JButton hej = new JButton("Exit");
		//hej.setBounds(1, 1, 60, 30);
		//hej.addActionListener(this);
		//getContentPane().add(hej);
	}

	public static void main(ProjectPlanner planner){
		ControlWindow2 ctrlwindow = new ControlWindow2(planner);
		ctrlwindow.addWindowListener(new WindowListener(){
			@Override public void windowActivated(WindowEvent arg0) {}
			@Override
			public void windowClosed(WindowEvent arg0) {
				//Main.exit(state);
			}
			@Override public void windowClosing(WindowEvent arg0) {}
			@Override public void windowDeactivated(WindowEvent arg0) {}
			@Override public void windowDeiconified(WindowEvent arg0) {}
			@Override public void windowIconified(WindowEvent arg0) {}
			@Override public void windowOpened(WindowEvent arg0) {}
		});
			
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
