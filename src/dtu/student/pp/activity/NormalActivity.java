package dtu.student.pp.activity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import dtu.student.pp.Developer;
import dtu.student.pp.project.Project;

public class NormalActivity extends AbstractActivity implements Serializable {
	private final Set<Developer> staffing = new HashSet<Developer>();
	private final Set<Developer> assistants = new HashSet<Developer>();
	private final Project parent;
	
	private float timeBudget = 0; //Positive number.
	
	public NormalActivity(int ID, Project parent) {
		super(Integer.toString(ID), ID); //Default name is just the ID.
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
	
	public void registerStaff(Developer developer) {
		assistants.remove(developer);//If they're already assistants, promote to staff.
		staffing.add(developer);
	}
	
	public void registerAssistance(Developer developer) {
		if(staffing.contains(developer))
			return; //If they're staff, don't demote them.
		assistants.add(developer);
	}
	
	public void removeStaff(Developer developer) {
		staffing.remove(developer);
		assistants.remove(developer);
	}
	
	@Override
	public boolean isStaff(Developer developer) {
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
}