package dtu.student.pp.ui;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.lang.reflect.Array;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dtu.student.pp.ProjectPlanner;

public class ChooseDeveloper extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	/**
	 *  Skal formegentlig erstattes - er erstattet af Choose.java
	 */
	public ChooseDeveloper(ProjectPlanner planner) {
		Object[] test = planner.getState().getDevelopers().toArray();
		Object[] possibleValues = test;
    	Object selectedValue = JOptionPane.showInputDialog(null,
    	"Choose a Developer", "Developer",
    	JOptionPane.INFORMATION_MESSAGE, null,
    	possibleValues, possibleValues[0]);
    	
    	
	}
	public static String choiceDev(ProjectPlanner planner){
		Object[] test = planner.getState().getDevelopers().toArray();
		Object[] possibleValues = test;
    	Object selectedValue = JOptionPane.showInputDialog(null,
    	"Choose a Developer", "Developer",
    	JOptionPane.INFORMATION_MESSAGE, null,
    	possibleValues, possibleValues[0]);
    	
    	System.out.println(planner.getState().getDevelopers().contains(selectedValue));
    	return selectedValue.toString();
	}


}
