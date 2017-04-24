package dtu.student.pp;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;
import dtu.student.pp.project.Project;

public class TestFile {
	
	@Test
	public void TestFileLoad(){
		String filepath = "testdb.txt";
		
		//Create PPState with data and save to file
		PPState savestate = new PPState();
		savestate.createProject();
		savestate.createActivity();
		savestate.createDeveloper("TEST0".toCharArray());
		savestate.createSpecialActivity("TEST0");
		Main.save(savestate,filepath);
		
		//Create new PPState based on file
		PPState loadstate = new PPState(); 
		loadstate = Main.load(filepath);
		
		//Test if PPStates are equal
		assertEquals(savestate,loadstate);
	}
}