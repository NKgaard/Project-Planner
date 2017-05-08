package dtu.student.pp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Stream;

import dtu.student.pp.data.activity.AbstractActivity;
import dtu.student.pp.data.activity.NormalActivity;
import dtu.student.pp.data.activity.SpecialActivity;
import dtu.student.pp.data.project.Project;
import dtu.student.pp.exception.NotProjectLeaderException;
import dtu.student.pp.exception.UserNotStaffException;

public class ProjectPlanner {
	//As the state and data classes don't perform checks.
	//View of projects and activities.
	private final PPState state;
	private final String user;
	
	ProjectPlanner(String user, PPState ppState) {
		this.user = user;
		this.state = ppState;
	}
	
	public void removeStaff(NormalActivity act, String staff) throws NotProjectLeaderException {
		if(!act.getParent().isLeader(user))
			throw new NotProjectLeaderException();
		act.removeStaff(staff);
	}
	
	public void registerStaff(NormalActivity act, String staff) throws NotProjectLeaderException {
		if(!act.getParent().isLeader(user))
			throw new NotProjectLeaderException();
		act.registerStaff(staff);
	}
	
	
	public void registerAssistance(NormalActivity act, String assistant) throws UserNotStaffException {
		if(!act.isStaff(user)) 
			throw new UserNotStaffException();
		act.registerAssistance(assistant);
	}
	
	public Project createProject(){
		return state.createProject();
	}
	
	public void registerHours(AbstractActivity act, float hours) throws UserNotStaffException {
		if(!act.isStaff(user))
			throw new UserNotStaffException();
		act.registerHours(user, hours);
	}
	
	public void setLeader(Project project) {
		//Maybe make it so the leader must be null, before a new can be assigned.
		project.setLeader(user);
	}

    public NormalActivity createActivity(Project project) throws NotProjectLeaderException {
		if(!project.isLeader(user))
			throw new NotProjectLeaderException();
		return state.createActivity(project);
	}
	
	public void removeActivity(NormalActivity activity) throws NotProjectLeaderException {
		if(!activity.getParent().isLeader(user))
			throw new NotProjectLeaderException();
		state.removeActivity(activity);
	}
	
	public void removeActivity(SpecialActivity activity) {
		state.removeActivity(activity);
	}
	
	public Stream<Project> getProjectsLeading(String leader) {
		return state.getProjects().stream() //Grab the projects where this dev is leader.
		.filter( p -> p.isLeader(leader) );
	}
	
	public Stream<AbstractActivity> getActivitiesStaffing(String staff) {
		return state.getActivities().stream() //Grab the activities where this dev is staff.
				.filter( a -> a.isStaff(staff) );
	}

	public String getUser(){
		return this.user;
	}
	public PPState getState(){
		return this.state;
	}
	
	public Set<AbstractActivity> getAllActivities() {
		return state.getActivities();
	}
	

	public void editProject(Project project, String leader, String name, String startDate, String endDate) {
		if ( name.length() > 0 )
			project.setName(name);

		if ( leader.length() > 0 ) {

		}
	}

	public void editProject(Project project, String name, int startweeknumber, int startyear, int endweeknumber, int endyear) {
		project.setName(name);
		project.setStart(startweeknumber,startyear);
		project.setEnd(endweeknumber, endyear);
	}

	public void generateReport(Project project) throws NotProjectLeaderException {
		if(!project.isLeader(user))
			throw new NotProjectLeaderException();
		project.generateReport();
	}

	public Set<Project> getAllProjects() {
		return state.getProjects();
	}

	public Set<String> getDevelopers() {
		return state.getDevelopers();
	}
	
}