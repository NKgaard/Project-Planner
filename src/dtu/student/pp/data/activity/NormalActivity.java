package dtu.student.pp.data.activity;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


import dtu.student.pp.data.project.Project;

public class NormalActivity extends AbstractActivity implements Serializable {
	private final Set<String> staffing = new HashSet<String>();
	private final Set<String> assistants = new HashSet<String>();
	private final Project parent;
	
	private float timeBudget = 0; //Positive number.
	
	public NormalActivity(int ID, Project parent) {
		super(ID);
		this.parent = parent;
		this.parent.addActivity(this);
	}
	
	public Project getParent() {
		return parent;
	}
	
	public float workEstimate() {
		//Get all workers.
		//Then for each activity in the time window get the time estimate
		// and add it to all workers matching staffing or assistants.
		float timeEstimate = timeBudget / (staffing.size() + assistants.size());
		
		//if(isSetInterval()) //Weigh time estimate by the duration to get work load estimate.
		//	timeEstimate *= getDuration().toMinutes() / 60f;
		
		return timeEstimate;
	}
	
	public float workLeft() {
		if(timeBudget == 0)
			return 0; //No budget set.
		
		return timeBudget - this.hoursRegistered();
	}
	
	public void registerStaff(String developer) {
		assistants.remove(developer);//If they're already assistants, promote to staff.
		staffing.add(developer);
	}
	
	public void registerAssistance(String developer) {
		if(staffing.contains(developer))
			return; //If they're staff, don't demote them.
		assistants.add(developer);
	}
	
	public void removeStaff(String developer) {
		staffing.remove(developer);
		assistants.remove(developer);
	}
	
	@Override
	public boolean isStaff(String developer) {
		return staffing.contains(developer) ||
				assistants.contains(developer);
	}
	
	public void setTimeEstimate(float time) {
		this.timeBudget = time;
	}

	public void close() {
		staffing.clear();
		assistants.clear();
		parent.removeActivity(this);
	}

	public Set<String> getStaff() {
		return Collections.unmodifiableSet(staffing);
	}
	public Set<String> getAssist() {
		return Collections.unmodifiableSet(assistants);
	}
}