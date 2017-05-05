package dtu.student.pp.comparators;

import java.util.Comparator;
import dtu.student.pp.data.activity.AbstractActivity;

public class ActivityByHoursUser implements Comparator<AbstractActivity> {
	private final String user;
	
	public ActivityByHoursUser(String user) {
		this.user = user;
	}
	
	@Override
	public int compare(AbstractActivity o1, AbstractActivity o2) {
		int result = Float.compare(o1.getHours(user), o2.getHours(user));
		if(result == 0)
			result = Integer.compare(o1.getActivityID(), o2.getActivityID());
		return result;
	}
	
}
