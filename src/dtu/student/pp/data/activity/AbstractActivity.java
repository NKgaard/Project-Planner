package dtu.student.pp.data.activity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import dtu.student.pp.String;
import dtu.student.pp.data.comparators.IntervalAble;
import dtu.student.pp.exception.UserNotStaffException;


public abstract class AbstractActivity extends IntervalAble implements Serializable  {
	//private final static String DEFAULT_NAME = "UNNAMED ACTIVITY";
	//Map to hold work hours.
	private final Map<String, WorkHours> workHours = new HashMap<String, WorkHours>();
	private final int activityID; //Always increment.
	//private String name;
	
	AbstractActivity(String name, int activityID) {
		super(name);
		this.activityID = activityID;
	}
	
	public int getActivityID() {
		return activityID;
	}
	
	public abstract void close();
	
	public abstract boolean isStaff(String developer);
	
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
	
	public final void registerHours(String developer, float hours) {
		//Can maybe notify observers when hours are registered.
		
		//TODO: Check if staff here instead?
		
		WorkHours accumulator = workHours.get(developer);
		if(accumulator==null)
			workHours.put(developer, accumulator = new WorkHours());
		
		accumulator.registerHours(hours);
	}
	
	public float getHours(String developer) {
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

