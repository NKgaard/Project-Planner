package dtu.student.pp;

import org.junit.Before;
import org.junit.Test;

public class TestInterval {
	//TestState
	PPState state;
	
	@Before
	public void initialize() {
		state = new PPState();
	}
	
	@Test
	public void testEmptyState() {
		//Create some random activities.
		for(int i=0; i < 5; i++)
			state.createActivity();
		
		
		
	}
}
