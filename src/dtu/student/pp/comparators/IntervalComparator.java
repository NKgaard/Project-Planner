package dtu.student.pp.comparators;

import java.util.Comparator;

import dtu.student.pp.data.activity.AbstractActivity;
import dtu.student.pp.data.comparators.Interval;

public class IntervalComparator implements Comparator<Interval> {

	@Override
	public int compare(Interval o1, Interval o2) {

		if(o1.getStart()==null && o2.getStart() != null)
			return 1;
		if(o1.getStart()!=null && o2.getStart() == null)
			return -1;

		if(o1.getEnd()==null && o2.getEnd() != null)
			return 1;
		if(o1.getEnd()!=null && o2.getEnd() == null)
			return -1;

		int result = 0;
		if(o1.getStart() != null && o2.getStart() != null)
			result = o1.getStart().compareTo(o2.getStart());
		if(result!=0) return result;

		if(o1.getEnd() != null && o2.getEnd() != null)
			result = o1.getEnd().compareTo(o2.getEnd());
		if(result!=0) return result;

		//This case both start or end fields were null.
		result = o1.getName().compareTo(o2.getName());
		assert result != 0 && !o1.equals(o2) : //TODO: Should never happen.
			      "The intervals are nonunique. (Same start and end-date, and name)";
		
		return result;
	}

}
