package dtu.student.pp.data.activity;

import java.io.Serializable;



public class SpecialActivity extends AbstractActivity implements Serializable {
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	private static final long serialVersionUID = 4397948605960081469L;
	private boolean closed = false;
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public SpecialActivity(String name, int ID) {
		super(ID);
		this.setName(name);
		//SpecialActivity.specialActivites.add(this);
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	@Override
	public boolean isStaff(String developer) {
		//Everyone can register hours in specialActivities.
		return !closed;
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	@Override
	public void close() {
		closed = true;
	}
	
	//private final static Set<SpecialActivity> specialActivites = new HashSet<SpecialActivity>();
	//public static Set<AbstractActivity> getSpecialActivities() {
	//	return Collections.unmodifiableSet(specialActivites);
	//}
	
}