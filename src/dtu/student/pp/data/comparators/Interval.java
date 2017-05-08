package dtu.student.pp.data.comparators;

import java.io.Serializable;
import java.util.Calendar;

public abstract class Interval implements Serializable {
	/**
	 * @Author Jonas Schjønnemann (s151781)
	 */
	private static final long serialVersionUID = 7398468513255847602L;
	private Calendar start, end; //Calendar, Date or Instant!
	private String name; //It is the responsibility of everyone implementing this to set a name.
	
	/**
	 * @Author Jonas Schjønnemann (s151781)
	 */
	public final String getName() {
		return this.name;
	}
	
	/**
	 * @Author Jonas Schjønnemann (s151781)
	 */
	public final void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @Author Jonas Schjønnemann (s151781)
	 */
	public boolean isEndBefore(Interval other) {
		return getEnd().before(other.getEnd());
	}
	
	/**
	 * @Author Jonas Schjønnemann (s151781)
	 */
	public Calendar getStart() {
		return start;
	}
	
	/**
	 * @Author Jonas Schjønnemann (s151781)
	 */
	public Calendar getEnd() {
		return end;
	}
	
	/**
	 * @Author Jonas Schjønnemann (s151781)
	 */
	public void setStart(int weeknumber, int year) {
		assert verifyTimeFormat(weeknumber,year);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, weeknumber);
		
		this.start = cal;
	}
	
	/**
	 * @Author Jonas Schjønnemann (s151781)
	 */
	public void setEnd(int weeknumber, int year) {
		assert verifyTimeFormat(weeknumber,year);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, weeknumber);
		
		this.end = cal;
	}
	
	/**
	 * @Author Jonas Schjønnemann (s151781)
	 */
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
	
	/**
	 * @Author Jonas Schjønnemann (s151781)
	 */
	public static boolean verifyTimeFormat(int weeknumber, int year){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		return ( weeknumber <= cal.getActualMaximum(Calendar.WEEK_OF_YEAR) );
	}
	
	/**
	 * @Author Jonas Schjønnemann (s151781)
	 */
	@Override public abstract boolean equals(Object other);
	
}