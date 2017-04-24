package dtu.student.pp.project;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import dtu.student.pp.Developer;
import dtu.student.pp.PPState;
import dtu.student.pp.activity.NormalActivity;
import dtu.student.pp.exception.NotProjectLeaderException;
import dtu.student.pp.interval.IntervalAble;
import dtu.student.pp.interval.IntervalSet;

public class Project extends IntervalAble implements Serializable {
	//private final static String DEFAULT_NAME = "UNNAMED PROJECT";
	//private ProjectPlanner planner;
	
	private Set<NormalActivity> activities = new HashSet<NormalActivity>();
	private Developer projectLeader;
	private final ProjectNumber number;
	//private String client = "";
	
	public Project(ProjectNumber projectNumber) {
		super(projectNumber.toString()); //Default name is just the project number.
		this.number = projectNumber;
	}
	
	public void addActivity(NormalActivity activity) {
		if(!this.equals(activity.getParent())) //Not generally possible as long as contract is kept.
			throw new AssertionError("An activity was added to the project, but it isn't it's parent.");
		activities.add(activity);
	}
	
	public boolean removeActivity(NormalActivity activity) {
		if(!this.equals(activity.getParent())) //Not generally possible as long as contract is kept.
			throw new AssertionError("An activity was removed from a project, that it wasn't part of.");
		return activities.remove(activity);
	}
	
	private void ensureActivityCanBeRemoved(NormalActivity activity) {
		//TODO If an activity has staffing with registered work hours, it should probably not be removed.
	}
	
	public boolean isLeader(Developer user) {
		if(projectLeader==null)
			return false;
		return projectLeader.equals(user);
	}
	
	@Override
	public int hashCode() {
		return number.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof Project))
			return false;
		Project other = (Project) obj;
		if (!number.equals(other.number))
			return false;
		return true;
	}

	public ProjectNumber getProjectNumber() {
		return number;
	}

	public Developer getLeader() {
		return projectLeader;
	}

	public Set<NormalActivity> getActivities() {
		return Collections.unmodifiableSet(activities);
	}

	public void setLeader(Developer user) {
		this.projectLeader = user;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}