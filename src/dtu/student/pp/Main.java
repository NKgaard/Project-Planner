package dtu.student.pp;



import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



import javax.swing.JOptionPane;

import dtu.student.pp.PPState;
import dtu.student.pp.ui.*;
import dtu.student.pp.data.activity.NormalActivity;
import dtu.student.pp.data.project.Project;

import dtu.student.pp.ui.Choose;








public class Main {
	
	private static String FILEPATH = "database.txt";
	private final PPState state;
	private ProjectPlanner planner;
	public ProjectPlanner pp;

	Main(PPState state) {
		this.state = state;
		String initials = getInitials();
		
		if(initials==null)
			return;
		state.createDeveloper(initials);
		
		planner = new ProjectPlanner(
				initials,
				state);
		
		
		exit(state);
		
		//Åbner aktivitetsvinduet - test
		planner.createProject();
		planner.createProject();
		planner.createProject();
		planner.createProject();
		//ActivityControl.main(null);
		//Choose.developer(planner); 			
		//DeleteProject dlt = new DeleteProject(planner);
		//String hej = ChooseDeveloper.choiceDev(planner);
		
		//String hej = Choose.developer(planner);
		Project hej1 = Choose.project(planner);
		Project test = planner.createProject();
		test.setLeader("dr");
		
			NormalActivity test1 = planner.getState().createActivity(test);
			NormalActivity test2 = planner.getState().createActivity(test);
			NormalActivity test3 = planner.getState().createActivity(test);
			NormalActivity test13 = planner.getState().createActivity(test);
			
		
		
		Project test4 = planner.createProject();
		NormalActivity test5 = planner.getState().createActivity(test4);
		DeleteProject dlt = new DeleteProject(planner);
		
		
		//NormalActivity test5 = Choose.activityFromProject(test);
		//NormalActivity test0 = Choose.activity(planner);
		//System.out.println(planner.getState().getActivities());
		//System.out.println(planner.getState().getProjects());
		//hej.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
		
		
	}

	private String getInitials() {
		String initials = null;
		
		boolean success = false;
		String defaultText = "Input your initials";
		String text = defaultText;
		
		while(!success) {
			initials = JOptionPane.showInputDialog(null,
					text,
					"Project Planner",
					JOptionPane.PLAIN_MESSAGE);
			
			if(initials == null) {
				exit(state);
				break;
			} else if (initials.length()==0)
				text = "The login must contain at least one character.";
			else if (initials.length() > PPState.MAX_INITIAL_LETTERS) {
				text = "Maximum 4 characters allowed";
			} else if (!state.hasDeveloper(initials)){
				int option = JOptionPane.showConfirmDialog(null,
						"<html><body>These initials are not registered in the system."
						+ "<br>Want to create a new developer account?</body></html>",
						"Create new account?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				success = option == JOptionPane.YES_OPTION;
				text = defaultText;
			} else {
				success = true;
			}
		}
		
		return initials;
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		if(args.length != 0) {FILEPATH = args[0];}
		
		new Main(load(FILEPATH));
		
		
		
	}

	public static void exit(PPState state) {
		save(state, FILEPATH);
	}
	
	public static void save(PPState state, String filepath) {
		
		try {
			FileOutputStream fos = new FileOutputStream(filepath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(state);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static PPState load(String filepath) {
		PPState result = null;
		if(new File(filepath).exists())
			try {
				FileInputStream fis = new FileInputStream(filepath);
				ObjectInputStream ois = new ObjectInputStream(fis);
				result = (PPState) ois.readObject();
				ois.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		if(result == null)
			result = new PPState();
		
		return result;
	}
	
	public PPState getPPState(){
		return state;
	}
	public ProjectPlanner getProjectPlanner(){
		return planner;
	}

}
