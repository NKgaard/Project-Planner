package dtu.student.pp.data.activity;

import java.io.Serializable;

public class WorkHours implements Serializable {
	private float work = 0;
	
	public void registerHours(float hours) {
		work = hours;
	}
	
	@Override
	public String toString() {
		// %.## format. Automatic comma decimal seperator on Danish clients.
		return String.format("%.2f", work);
	}

	public float getWork() {
		return work;
	}
	
}