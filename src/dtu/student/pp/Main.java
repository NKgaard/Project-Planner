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
import dtu.student.pp.data.activity.AbstractActivity;


public class Main {
	
	private static String FILEPATH = "database.txt";
	private static PPState state;
	private static ProjectPlanner pp;

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		if(args.length != 0) {FILEPATH = args[0];}
		state = load(FILEPATH);
		
		
		
		//save(state);
		
		
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
