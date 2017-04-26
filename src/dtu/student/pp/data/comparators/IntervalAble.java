package dtu.student.pp.data.comparators;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

public abstract class IntervalAble implements Comparable<IntervalAble>, Serializable {
	private Calendar start, end; //Calendar, Date or Instant!
	private String name; //It is the responsibility of everyone implementing this to set a name.
	
	public IntervalAble(String name) {
		this.name = name;
	}
	
	public Duration getDuration() {
		if(start==null || end==null) return Duration.ZERO;
		return Duration.between(start.toInstant(), end.toInstant());
	}
	
	public final String getName() {
		return this.name;
	}
	
	public final void setName(String name) {
		this.name = name;
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
		verifyTime();
	}

	public void setEnd(Calendar endDate) {
		this.end = (Calendar) endDate.clone();
		verifyTime();
	}
	
	private void verifyTime() {
		if(start!=null && end!=null)
			if(!end.after(start))
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
	
	@Override public abstract boolean equals(Object other);
	
}