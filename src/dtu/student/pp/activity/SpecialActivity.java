package dtu.student.pp.activity;

import java.io.Serializable;

import dtu.student.pp.Developer;


public class SpecialActivity extends AbstractActivity implements Serializable {
	
	
	public SpecialActivity(String name, int ID) {
		super(name, ID);
		//SpecialActivity.specialActivites.add(this);
	}
	
	public boolean isStaff(Developer developer) {
		//Everyone can register hours in specialActivities.
		return true;
	}
	
	//private final static Set<SpecialActivity> specialActivites = new HashSet<SpecialActivity>();
	//public static Set<AbstractActivity> getSpecialActivities() {
	//	return Collections.unmodifiableSet(specialActivites);
	//}
	
}