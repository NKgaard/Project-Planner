package dtu.student.pp.data.activity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


import dtu.student.pp.data.comparators.Interval;

public abstract class AbstractActivity extends Interval implements Serializable  {
	/**
	 * @Author Nicolai Kammersgård (s143780)
	 */
	private static final long serialVersionUID = -7060292105001058409L;
	//Map to hold work hours.
	private final Map<String, WorkHours> workHours = new HashMap<String, WorkHours>();
	private final int activityID; //Always increment.
	
	/**
	 * @Author Nicolai Kammersgård (s143780)
	 */
	AbstractActivity(int activityID) {
		this.activityID = activityID;
	}
	
	/**
	 * @Author Nicolai Kammersgård (s143780)
	 */
	public int getActivityID() {
		return activityID;
	}
	
	/**
	 * @Author Nicolai Kammersgård (s143780)
	 */
	public abstract void close();
	
	/**
	 * @Author Nicolai Kammersgård (s143780)
	 */
	public abstract boolean isStaff(String developer);
	
	/**
	 * @Author Nicolai Kammersgård (s143780)
	 */
	public boolean isNoWorkRegistered() {
		return workHours.isEmpty();
	}
	
	/**
	 * @Author Nicolai Kammersgård (s143780)
	 */
	public float hoursRegistered() {
		float result = 0;
		if(workHours.isEmpty())
			return result;
		
		for(WorkHours hours:workHours.values())
			result += hours.getWork();
		
		return result;
	}
	
	/**
	 * @Author Nicolai Kammersgård (s143780)
	 */
	public final void registerHours(String developer, float hours) {
		WorkHours accumulator = workHours.get(developer);
		if(accumulator==null)
			workHours.put(developer, accumulator = new WorkHours());
		
		accumulator.registerHours(hours);
	}
	
	/**
	 * @Author Nicolai Kammersgård (s143780)
	 */
	public float getHours(String developer) {
		WorkHours accumulator = workHours.get(developer);
		if(accumulator==null)
			return 0;
		else
			return accumulator.getWork();
	}
	
	/**
	 * @Author Nicolai Kammersgård (s143780)
	 */
	@Override
	public String toString() {
		//return getName();
		return String.valueOf(this.activityID);
	}
	
	/**
	 * The id is an unique integer. This means perfect hashing.
	 * @Author Nicolai Kammersgård (s143780)
	 */
	@Override public int hashCode() { return activityID; }
	
	/**
	 * @Author Nicolai Kammersgård (s143780)
	 */
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
	
	/**
	 * @Author Nicolai Kammersgård (s143780)
	 */
	public float getHoursSum() {
		float sum = 0;
		for(WorkHours hrs:workHours.values())
			sum += hrs.getWork();
		return sum;
	}
}

