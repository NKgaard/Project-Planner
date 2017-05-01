package dtu.student.pp.data.activity;

import java.io.Serializable;




public class SpecialActivity extends AbstractActivity implements Serializable {
	private boolean closed = false;
	
	public SpecialActivity(String name, int ID) {
		super(ID);
		this.setName(name);
		//SpecialActivity.specialActivites.add(this);
	}
	
	@Override
	public boolean isStaff(String developer) {
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