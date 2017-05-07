package dtu.student.pp.ui;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.lang.reflect.Array;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dtu.student.pp.ProjectPlanner;
import dtu.student.pp.data.project.Project;

public class DeleteProject extends JDialog {

	//private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	//	try {
			
	//		ChooseDeveloper dialog = new ChooseDeveloper();
	//		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	//		dialog.setVisible(true);
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//	}
	//}

	/**
	 * Create the dialog.
	 */
	public DeleteProject(ProjectPlanner planner) {
		Object[] test = planner.getState().getProjects().toArray();
		Object[] possibleValues = test;
    	Object selectedValue = JOptionPane.showInputDialog(null,
    	"Choose a Project to delete", "Delete Project",
    	JOptionPane.INFORMATION_MESSAGE, null,
    	possibleValues, possibleValues[0]);
    	  	
    	for(Project p: planner.getState().getProjects()){
    		if(p.getProjectNumber().toString().equals(selectedValue.toString())){
    			planner.getState().removeProject(p);
    			System.out.println("Project " + selectedValue.toString() + " Deleted!");
    			break;
    		 
    		}
    	}
    	
    	
	}
	
	

}