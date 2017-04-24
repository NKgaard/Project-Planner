package dtu.student.pp.interval;

import java.io.Serializable;
import java.time.Duration;
import java.util.Calendar;
import java.util.Observable;


public abstract class IntervalAble extends Observable implements Comparable<IntervalAble>, Serializable {
	private Calendar start, end; //Calendar, Date or Instant!
	private String name; //It is the responsibility of everyone implementing this to set a name.
	
	public IntervalAble(String name) {
		this.name = name;
	}
	
	public boolean isSetInterval() {
		return start!=null && end!=null;
	}
	
	public Duration getDuration() {
		if(!isSetInterval()) return Duration.ZERO;
		return Duration.between(start.toInstant(), end.toInstant());
	}
	
	public final String getName() {
		return this.name;
	}
	
	public final void setName(String name) {
		this.name = name;
		this.setChanged();
		this.notifyObservers("NameChange: "+name);
	}
	
	public boolean isEndBefore(IntervalAble other) {
		return getEnd().before(other.getEnd());
	}
	
	public Calendar getStart() {
		return start;
	}
	
	public Calendar getEnd() {
		return end;
	}
	
	public void setStart(Calendar startDate) {
		this.start = (Calendar) startDate.clone();
		this.setChanged();
		verifyTime();
		this.notifyObservers("StartDateChange: "+startDate);
	}

	public void setEnd(Calendar endDate) {
		this.end = (Calendar) endDate.clone();
		verifyTime();
		this.setChanged();
		this.notifyObservers("EndDateChange: "+endDate);
	}
	
	private void verifyTime() {
		if(start!=null && end!=null)
			if(end.before(start))
				throw new IllegalArgumentException("The end of interval should be before the beginning!");
	}
	
	@Override //TODO Assert that x.compareTo(y) == -y.compareTo(x) in all cases?
	public int compareTo(IntervalAble o) {
		//The order is:
		//  Null end values.
		//  Values with both start and end.
		//  Null start values.
		
		
		if(start==null && o.start != null)
			return 1;
		if(start!=null && o.start == null)
			return -1;
		
		if(end==null && o.end != null)
			return 1;
		if(end!=null && o.end == null)
			return -1;
		
		int result = 0;
		if(start != null && o.start != null)
			result = start.compareTo(o.start);
		if(result!=0) return result;
		
		if(end != null && o.end != null)
			result = end.compareTo(o.end);
		if(result!=0) return result;
		
		//This case both start or end fields were null.
		result = getName().compareTo(o.getName());
		if(result == 0 && !equals(o)) throw new AssertionError( //TODO: Should never happen.
				  "The intervals are nonunique."
				+ " (Same start and end-date, and name)");
		return result;
	}
	
	//TODO check if treeSet calls this when replacing values. If so, how to implement it.
	@Override public abstract boolean equals(Object other);
	
}