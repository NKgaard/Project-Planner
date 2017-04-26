package dtu.student.pp.data.comparators;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

public abstract class IntervalAble implements Serializable {
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
	
	@Override public abstract boolean equals(Object other);
	
}