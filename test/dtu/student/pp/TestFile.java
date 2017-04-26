package dtu.student.pp;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Map;
import java.util.Set;

import dtu.student.pp.data.activity.NormalActivity;
import dtu.student.pp.data.project.Project;

public class TestFile {
	public final static String TESTFILE_PATH = "testdb.txt";
	
	//This test may be very short!
	//Try to get a high code coverage in the Main static methods. (The .equals seems alright)	
	
	@Test
	public void testEmptyState() {
		PPState emptyState = new PPState();
		Main.save(emptyState, TESTFILE_PATH);
		
		PPState loadState = Main.load(TESTFILE_PATH);
		
		assertEquals(emptyState,loadState);
	}
	
	@Test
	public void testState() {
		PPState saveState = new PPState();
		saveState.createDeveloper("TEST".toCharArray());
		
		Project project = saveState.createProject();
		project.setLeader(saveState.createDeveloper("LEDR".toCharArray()));
		
		NormalActivity activity = saveState.createActivity(project);
		activity.setName("TestActivity");
		activity.setStart(Calendar.getInstance());
		activity.setTimeEstimate(20);
		
		Developer developer = saveState.createDeveloper("DEV1".toCharArray());
		activity.registerStaff(developer);
		activity.registerHours(developer, 5);
		
		saveState.createSpecialActivity("TestSpecialActivity");
		
		Main.save(saveState, TESTFILE_PATH);
		
		//Create new PPState based on file
		PPState loadState = Main.load(TESTFILE_PATH);
		
		//Test if PPStates are equal
		assertEquals(saveState, loadState);
				
	}
	
	@Test
	public void TestFileLoad(){
		String filepath = "testdb.txt";
		
		//Create PPState with data and save to file
		PPState savestate = new PPState();
		savestate.createActivity(savestate.createProject());
		savestate.createDeveloper("TEST0".toCharArray());
		savestate.createSpecialActivity("TEST0");
		Main.save(savestate,filepath);
		
		//Create new PPState based on file
		PPState loadstate = Main.load(filepath);
		
		//Test if PPStates are equal
		assertEquals(savestate,loadstate);
	}
}