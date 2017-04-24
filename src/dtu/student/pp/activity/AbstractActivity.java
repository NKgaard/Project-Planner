package dtu.student.pp.activity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import dtu.student.pp.Developer;
import dtu.student.pp.exception.UserNotStaffException;
import dtu.student.pp.interval.IntervalAble;


public abstract class AbstractActivity extends IntervalAble implements Serializable  {
	//private final static String DEFAULT_NAME = "UNNAMED ACTIVITY";
	//Map to hold work hours.
	private final Map<Developer, WorkHours> workHours = new HashMap<Developer, WorkHours>();
	private final int activityID; //Always increment.
	//private String name;
	
	AbstractActivity(String name, int activityID) {
		super(name);
		this.activityID = activityID;
	}
	
	public int getActivityID() {
		return activityID;
	}
	
	//Return true if this person is a staff/assistant, and can register hours.
	public abstract boolean isStaff(Developer developer);
	
	public boolean isNoWorkRegistered() {
		return workHours.isEmpty();
	}
	
	public float hoursRegistered() {
		float result = 0;
		if(workHours.isEmpty())
			return result;
		
		for(WorkHours hours:workHours.values())
			result += hours.getWork();
		
		return result;
	}
	
	public final void registerHours(Developer developer, float hours) throws UserNotStaffException {
		//Can maybe notify observers when hours are registered.
		if(!isStaff(developer))
			throw new UserNotStaffException();
		
		WorkHours accumulator = workHours.get(developer);
		if(accumulator==null)
			workHours.put(developer, accumulator = new WorkHours());
		
		accumulator.registerHours(hours);
	}
	
	public float getHours(Developer developer) {
		WorkHours accumulator = workHours.get(developer);
		if(accumulator==null)
			return 0;
		else
			return accumulator.getWork();
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	//The id is an unique integer. This means perfect hashing.
	@Override public int hashCode() { return activityID; }
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractActivity))
			return false;
		if (getActivityID() != ((AbstractActivity) obj).getActivityID())
			return false;
		return true;
	}
}

