package dtu.student.pp.data.comparators;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

public abstract class Interval implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7398468513255847602L;
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
	
	public void setStart(int weeknumber, int year) {
		assert verifyTimeFormat(weeknumber,year);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, weeknumber);
		
		this.start = cal;
	}

	public void setEnd(int weeknumber, int year) {
		assert verifyTimeFormat(weeknumber,year);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, weeknumber);
		
		this.end = cal;
	}
	
	public static boolean verifyTime(int startweeknumber, int startyear, int endweeknumber, int endyear) {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.set(Calendar.YEAR, startyear);
		end.set(Calendar.YEAR, endyear);
		start.set(Calendar.WEEK_OF_YEAR, startweeknumber);
		end.set(Calendar.WEEK_OF_YEAR, endweeknumber);
		
		if(start!=null && end!=null)
			if(!end.after(start))
				return false;
		return true;
	}
	
	public static boolean verifyTimeFormat(int weeknumber, int year){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		return ( weeknumber <= cal.getActualMaximum(Calendar.WEEK_OF_YEAR) );
	}
	
	@Override public abstract boolean equals(Object other);
	
}