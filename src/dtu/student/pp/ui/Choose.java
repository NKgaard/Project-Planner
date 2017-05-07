package dtu.student.pp.ui;


import javax.swing.JOptionPane;

import dtu.student.pp.ProjectPlanner;
import dtu.student.pp.data.activity.NormalActivity;
import dtu.student.pp.data.project.Project;

/**
 * @author Jonas
 *
 */
public class Choose {
	
	
	
	public static Project project(ProjectPlanner planner){
		Object[] possibleValues = planner.getState().getProjects().toArray();
		Object selectedValue = JOptionPane.showInputDialog(null,
    	"Choose a Project", "Projects",
    	JOptionPane.INFORMATION_MESSAGE, null,
    	possibleValues, possibleValues[0]);
    	
    	System.out.println(planner.getState().getProjects().contains(selectedValue));
    	return (Project) selectedValue;
	}
	
	
	public static String developer(ProjectPlanner planner){
		Object[] possibleValues = planner.getState().getDevelopers().toArray();
		Object selectedValue = JOptionPane.showInputDialog(null,
    	"Choose a Developer", "Developer",
    	JOptionPane.INFORMATION_MESSAGE, null,
    	possibleValues, possibleValues[0]);
    	return selectedValue.toString();
	}
	
	public static NormalActivity activityFromProject(Project p){
		Object[] possibleValues = p.getActivities().toArray();
		Object selectedValue = JOptionPane.showInputDialog(null,
    	"Choose an activity", "Activities in " + p.toString(),
    	JOptionPane.INFORMATION_MESSAGE, null,
    	possibleValues, possibleValues[0]);
    	
    	return (NormalActivity) selectedValue;
	}
	
	public static NormalActivity activity(ProjectPlanner planner){
		Object[] possibleValues = planner.getState().getActivities().toArray();
		Object selectedValue = JOptionPane.showInputDialog(null,
    	"Choose an activity", "Activities",
    	JOptionPane.INFORMATION_MESSAGE, null,
    	possibleValues, possibleValues[0]);
    	
    	return (NormalActivity) selectedValue;
	}
	
	
	

}
