package dtu.student.pp.data.activity;

import java.io.Serializable;

import dtu.student.pp.Developer;


public class SpecialActivity extends AbstractActivity implements Serializable {
	private boolean closed = false;
	
	public SpecialActivity(String name, int ID) {
		super(name, ID);
		//SpecialActivity.specialActivites.add(this);
	}
	
	@Override
	public boolean isStaff(Developer developer) {
		//Everyone can register hours in specialActivities.
		return !closed;
	}

	@Override
	public void close() {
		closed = true;
	}
	
	//private final static Set<SpecialActivity> specialActivites = new HashSet<SpecialActivity>();
	//public static Set<AbstractActivity> getSpecialActivities() {
	//	return Collections.unmodifiableSet(specialActivites);
	//}
	
}