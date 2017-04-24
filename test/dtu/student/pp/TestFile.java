package dtu.student.pp;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import dtu.student.pp.PPState;
import dtu.student.pp.project.Project;

public class TestFile {
	PPState state;
	
	@Before
	public void Initialize(){
		//Create PPstate
		state = new PPState(); 
		
		//Create test information
 		state.createDeveloper("NKA".toCharArray());
		state.createProject();
		state.createActivity();
		state.createSpecialActivity("TEST");
	}
	
	@Test
	public void TestFileSave(){
		

		
		Set<Project> halloj = state.getProjects();
		for ( Project p : halloj ) {
			p.setLead);
		}
		
		
		
		assertTrue(state.getProjects().contains(project));
		assertEquals(project,state.getProjects());
	}
}