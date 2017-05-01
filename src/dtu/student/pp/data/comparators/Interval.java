package dtu.student.pp.data.comparators;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

public abstract class Interval implements Serializable {
	private Calendar start, end; //Calendar, Date or Instant!
	private String name; //It is the responsibility of everyone implementing this to set a name.
	
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
	
	public boolean isEndBefore(Interval other) {
		return getEnd().before(other.getEnd());
	}
	
	public Calendar getStart() {
		return start;
	}
	
	public Calendar getEnd() {
		return end;
	}
	
	public void setStart(Calendar startDate) {
		assert verifyTime(startDate, this.end);
		this.start = (Calendar) startDate.clone();
	}

	public void setEnd(Calendar endDate) {
		assert verifyTime(this.start, endDate);
		this.end = (Calendar) endDate.clone();
	}
	
	public static boolean verifyTime(Calendar start, Calendar end) {
		if(start!=null && end!=null)
			if(!end.after(start))
				return false;
		return true;
	}
	
	@Override public abstract boolean equals(Object other);
	
}