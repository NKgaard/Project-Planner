package dtu.student.pp;

import java.util.stream.Stream;

import dtu.student.pp.activity.AbstractActivity;
import dtu.student.pp.activity.NormalActivity;
import dtu.student.pp.activity.SpecialActivity;
import dtu.student.pp.exception.NotProjectLeaderException;
import dtu.student.pp.exception.UserNotStaffException;
import dtu.student.pp.project.Project;

public class ProjectPlanner {
	//As the state and data classes don't perform checks.
	//View of projects and activities.
	private final PPState state;
	private final Developer user;
	
	ProjectPlanner(Developer user, PPState ppState) {
		this.user = user;
		this.state = ppState;
	}
	
	public void registerStaff(NormalActivity act, Developer staff) throws NotProjectLeaderException {
		if(!act.getParent().isLeader(user))
			throw new NotProjectLeaderException();
		act.registerStaff(staff);
	}
	
	public void registerAssistance(NormalActivity act, Developer assistant) throws UserNotStaffException {
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
	
	public AbstractActivity createActivity(Project project) throws NotProjectLeaderException {
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
	
	public Stream<Project> getProjectsLeading(Developer leader) {
		return state.getProjects().stream() //Grab the projects where this dev is leader.
		.filter( p -> p.isLeader(leader) );
	}
	
	public Stream<AbstractActivity> getActivitiesStaffing(Developer staff) {
		return state.getActivities().stream() //Grab the activities where this dev is staff.
				.filter( a -> a.isStaff(staff) );
	}
	
}