package dtu.student.pp;

import java.io.Serializable;
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

import dtu.student.pp.activity.AbstractActivity;
import dtu.student.pp.activity.NormalActivity;
import dtu.student.pp.activity.SpecialActivity;
import dtu.student.pp.exception.UserNotStaffException;
import dtu.student.pp.interval.IntervalTree;
import dtu.student.pp.project.Project;
import dtu.student.pp.project.ProjectNumber;

public class PPState implements Serializable {
	/**
	 * A class to hold the state of the ProjectPlanner, so it can be stored easily.
	 */
	//private static final long serialVersionUID = -6074556352167281955L;
	
	private final Calendar theTime;
	//To create ID numbers for activities and projects.
	private final Map<Integer, Integer> projectCounts;
	private int activityCounter = 0; //Activities are not removed so activities.size() could be used instead.
	
	private final Set<AbstractActivity> activities;
	private final Set<Project> projects;
	private final Set<Developer> developers;
	
	PPState(Map<Integer, Integer> pCounts, int aCounts, Calendar time,
			Set<AbstractActivity> activities, Set<Project> projects,
			Set<Developer> developers) {
		this.projectCounts = pCounts;
		this.activityCounter = aCounts;
		this.theTime = time;
		this.activities = activities;
		this.projects = projects;
		this.developers = developers;
	}
	
	PPState() {
		//Default, fresh state.
		this(
				new HashMap<Integer, Integer>(), //Empty map of project counts for the year.
				0, //Activity counts at 0.
				GregorianCalendar.getInstance(), //Load the time right now.
				new HashSet<AbstractActivity>(), //Empty set of activities.
				new HashSet<Project>(),//Empty set of projects.
				new HashSet<Developer>()); //Empty set of developers.
	}
	
	private ProjectNumber getNewProjectNumber() {
		int year = theTime.get(Calendar.YEAR);
		
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
	public Project createProject() {
		//If the state was passed to the project, we could enable it to remove activities.
		Project p = new Project(getNewProjectNumber());
		addProject(p);
		return p;
	}
	public NormalActivity createActivity() {
		NormalActivity a = new NormalActivity(getNewActivityID());
		addActivity(a);
		return a;
	}
	public SpecialActivity createSpecialActivity(String name) {
		SpecialActivity a = new SpecialActivity(name, getNewActivityID());
		addActivity(a);
		return a;
	}
	public Developer createDeveloper(char[] initials) {
		Developer d = new Developer(initials);
		addDeveloper(d);
		return d;
	}
	private void addProject(Project p) {
		projects.add(p); //Should always return true btw.
	}
	
	private void addActivity(AbstractActivity a) {
		activities.add(a); //Should always return true btw.
	}
	private void addDeveloper(Developer d) {
		developers.add(d); //Should always return true btw.
	}
	public Set<Project> getProjects() {
		return Collections.unmodifiableSet(projects);
	}
	
	public Set<AbstractActivity> getActivities() {
		return Collections.unmodifiableSet(activities);
	}
	public void removeProject(Project p) {
		//When removing in projectplanner, ensure user is leader.
		
		//If this project had any activities that had no work registered, -
		// they're redundant and can be removed.
		for(NormalActivity act:p.getActivities())
			if(act.isNoWorkRegistered())
				activities.remove(act);
			else
				act.removeAllStaff();
		
		projects.remove(p);
	}
	public void removeActivity(Project p, NormalActivity act) {
		//When removing in projectplanner, ensure user is leader. and that this poroject containsv act
		
		p.removeActivity(act);
		if(act.isNoWorkRegistered())
			activities.remove(act);
		else
			act.removeAllStaff();
	}
		
	//public Calendar getTime() {
	//	return (Calendar) theTime.clone();
	//}
}


