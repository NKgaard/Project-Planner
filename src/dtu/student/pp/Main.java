package dtu.student.pp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import dtu.student.pp.PPState;
import dtu.student.pp.activity.AbstractActivity;


public class Main {
	
	private static String FILEPATH = "database.txt";
	private static PPState state;
	private static ProjectPlanner pp;

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		if(args.length != 0) {FILEPATH = args[0];}
		state = load(FILEPATH);

		state.createDeveloper("NKA".toCharArray());
		state.createProject();
		state.createActivity().setStart(Calendar.getInstance());
		
		System.out.println(state.getActivities().size());		
		System.out.println(state.getProjects().size());
		
		//////MERE TING//////
		AbstractActivity test0 = state.createSpecialActivity("Test0");
		AbstractActivity test1 = state.createSpecialActivity("Test1");
		AbstractActivity test2 = state.createSpecialActivity("Test2");
		AbstractActivity test3 = state.createSpecialActivity("Test3");
		AbstractActivity test4 = state.createSpecialActivity("Test4");
		AbstractActivity test5 = state.createSpecialActivity("Test5");
		
		Calendar early = Calendar.getInstance();
		Calendar late = (Calendar) early.clone();
		late.add(Calendar.HOUR_OF_DAY, 1);
		Calendar later = (Calendar) late.clone();
		later.add(Calendar.HOUR_OF_DAY, 5);
		
		test1.setStart(early);
		test2.setEnd(early);
		
		test3.setStart(early);
		test3.setEnd(late);
		
		test4.setStart(later);
		test5.setEnd(later);
		
		save(state);
		
		
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
		PPState result;
		
//		try (	FileInputStream fis = new FileInputStream(filepath);
//				ObjectInputStream ois = new ObjectInputStream(fis)) {
//			
//			result = (PPState) ois.readObject();
//			
//		} catch (IOException | ClassNotFoundException e) {
//			e.printStackTrace();
//			
//			result = new PPState();
//			
//		}
		
		try {
			FileInputStream fis = new FileInputStream(filepath);
			ObjectInputStream ois = new ObjectInputStream(fis);
			result = (PPState) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			//TODO: Please do print stack trace. (It caused an error, hard to debug)
			e.printStackTrace();
			result = new PPState();
		}
		
		return result;
	}

}
