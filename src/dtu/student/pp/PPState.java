package dtu.student.pp;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import dtu.student.pp.data.activity.AbstractActivity;
import dtu.student.pp.data.activity.NormalActivity;
import dtu.student.pp.data.activity.SpecialActivity;
import dtu.student.pp.data.comparators.IntervalSet;
import dtu.student.pp.data.project.Project;
import dtu.student.pp.data.project.ProjectNumber;
import dtu.student.pp.exception.UserNotStaffException;

public class PPState implements Serializable {
	/**
	 * A class to hold the state of the ProjectPlanner, so it can be stored easily.
	 */
	
	//private final Calendar theTime;
	//To create ID numbers for activities and projects.
	private final Map<Integer, Integer> projectCounts;
	private int activityCounter = 0; //Activities are not removed so activities.size() could be used instead.
	
	private final Set<AbstractActivity> activities;
	private final Set<Project> projects;
	private final Set<Developer> developers;
	
	PPState(Map<Integer, Integer> pCounts, int aCounts,
			Set<AbstractActivity> activities, Set<Project> projects,
			Set<Developer> developers) {
		this.projectCounts = pCounts;
		this.activityCounter = aCounts;
		//this.theTime = time;
		this.activities = activities;
		this.projects = projects;
		this.developers = developers;
	}
	
	PPState() {
		//Default, fresh state.
		this(
				new HashMap<Integer, Integer>(), //Empty map of project counts for the year.
				0, //Activity counts at 0.
				//GregorianCalendar.getInstance(), //Load the time right now.
				new HashSet<AbstractActivity>(), //Empty set of activities.
				new HashSet<Project>(), //Empty set of projects.
				new HashSet<Developer>()); //Empty set of developers.
	}
	
	private ProjectNumber getNewProjectNumber(Calendar time) {
		int year = time.get(Calendar.YEAR);
		
		Integer projectNr = projectCounts.get(year);
		
		if(projectNr==null) {
			//There is no projects registered this year. New counter.
			projectCounts.put(year, 0);
			return new ProjectNumber(year, 0);
		} else {
			//Increment counter;
			projectCounts.put(year, projectNr + 1);
			return new ProjectNumber(year, projectNr + 1);
		}
	}
	
	private int getNewActivityID() {
		return activityCounter++;
	}
	
	public SpecialActivity createSpecialActivity(String name) {
		SpecialActivity a = new SpecialActivity(name, getNewActivityID());
		activities.add(a);
		return a;
	}
	
	//TODO: Rewrite the assertion errors to assert (keyword) statements.
	//Contract 1 - parent has set of activities. Activities have a link to the parent.
	// So an activity may only have one parent. This is illustrated in the Project.addActivity assertions.
	
	//Contract 2 - All activities and projects must be unique.
	// Therefore throw an assertion error if it already is present.
	public NormalActivity createActivity(Project parent) {
		NormalActivity act = new NormalActivity(getNewActivityID(), parent);
		boolean alreadyExists = !activities.add(act);
		assert alreadyExists : "This activity already exists!";
		return act;
	}
	public void removeActivity(AbstractActivity act) {
		act.close();
		if(act.isNoWorkRegistered())
			activities.remove(act);
	}
	
	public Project createProject() {
		//If the state was passed to the project, we could enable it to remove activities.
		Project p = new Project(getNewProjectNumber(Calendar.getInstance()));
		boolean alreadyExists = !projects.add(p);
		assert alreadyExists : "This project already exists!";
		return p;
	}
	
	public void removeProject(Project p) {
		for(NormalActivity act:p.getActivities())
			removeActivity(act);
		projects.remove(p);
	}
	
	public Developer createDeveloper(char[] initials) {
		Developer d = new Developer(initials);
		developers.add(d);
		return d;
	}
	public Set<Project> getProjects() {
		return Collections.unmodifiableSet(projects);
	}
	
	public Set<AbstractActivity> getActivities() {
		return Collections.unmodifiableSet(activities);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activities == null) ? 0 : activities.hashCode());
		result = prime * result + activityCounter;
		result = prime * result + ((developers == null) ? 0 : developers.hashCode());
		result = prime * result + ((projectCounts == null) ? 0 : projectCounts.hashCode());
		result = prime * result + ((projects == null) ? 0 : projects.hashCode());
		//result = prime * result + ((theTime == null) ? 0 : theTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PPState other = (PPState) obj;
		if (activities == null) {
			if (other.activities != null)
				return false;
		} else if (!activities.equals(other.activities))
			return false;
		if (activityCounter != other.activityCounter)
			return false;
		if (developers == null) {
			if (other.developers != null)
				return false;
		} else if (!developers.equals(other.developers))
			return false;
		if (projectCounts == null) {
			if (other.projectCounts != null)
				return false;
		} else if (!projectCounts.equals(other.projectCounts))
			return false;
		if (projects == null) {
			if (other.projects != null)
				return false;
		} else if (!projects.equals(other.projects))
			return false;
//		if (theTime == null) {
//			if (other.theTime != null)
//				return false;
//		} else if (!theTime.equals(other.theTime))
//			return false;
		return true;
	}
		
	//public Calendar getTime() {
	//	return (Calendar) theTime.clone();
	//}
}


