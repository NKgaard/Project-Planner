package dtu.student.pp.data.activity;

import java.io.Serializable;

public class WorkHours implements Serializable {
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	private static final long serialVersionUID = 7331508095428809497L;
	private float work = 0;
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public void registerHours(float hours) {
		work = hours;
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	@Override
	public String toString() {
		// %.## format. Automatic comma decimal seperator on Danish clients.
		return String.format("%.2f", work);
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public float getWork() {
		return work;
	}
	
}