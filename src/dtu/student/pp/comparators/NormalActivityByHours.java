package dtu.student.pp.comparators;

import java.util.Comparator;

import dtu.student.pp.data.activity.AbstractActivity;
import dtu.student.pp.data.activity.NormalActivity;

public class NormalActivityByHours implements Comparator<NormalActivity> {
	
	@Override
	public int compare(NormalActivity o1, NormalActivity o2) {
		int result = Float.compare(o1.hoursRegistered(), o2.hoursRegistered());
		if(result==0)
			result = Integer.compare(o1.getActivityID(), o2.getActivityID());
		return result;
	}

}
