package dtu.student.pp;

import java.nio.file.Paths;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import dtu.student.pp.activity.AbstractActivity;
import dtu.student.pp.interval.IntervalTree;

public class TestInterval {
	//TestState
	PPState state;
	
	@Before
	public void initialize() {
		state = new PPState();
	}
	
	@Test
	public void testEmptyState() {
		
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
		
		IntervalTree<AbstractActivity> tree = 
				new IntervalTree<AbstractActivity>(state.getActivities());
		for(AbstractActivity act:tree.getValues()) {
			System.out.print(act.getName());
			if(act.getStart()!=null)
				System.out.print(" S "+act.getStart().get(Calendar.HOUR_OF_DAY)+" ");
			if(act.getEnd()!=null)
				System.out.print(" E "+act.getEnd().get(Calendar.HOUR_OF_DAY));
			System.out.println();
		}
		
	}
}
