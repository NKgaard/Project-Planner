package dtu.student.pp.comparators;

import java.util.Comparator;

import dtu.student.pp.Developer;
import dtu.student.pp.data.activity.AbstractActivity;

public class OrderByHoursUser implements Comparator<AbstractActivity> {
	private final Developer user;
	
	public OrderByHoursUser(Developer user) {
		this.user = user;
	}
	
	@Override
	public int compare(AbstractActivity o1, AbstractActivity o2) {
		int result = Float.compare(o1.getHours(user), o2.getHours(user));
		if(result == 0)
			result = o1.getName().compareTo(o2.getName());
		return result;
	}

}
