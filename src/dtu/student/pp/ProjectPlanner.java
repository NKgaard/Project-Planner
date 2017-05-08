package dtu.student.pp;

import java.util.Set;
import java.util.stream.Stream;

import dtu.student.pp.data.activity.AbstractActivity;
import dtu.student.pp.data.activity.NormalActivity;
import dtu.student.pp.data.activity.SpecialActivity;
import dtu.student.pp.data.project.Project;
import dtu.student.pp.exception.NotProjectLeaderException;
import dtu.student.pp.exception.UserNotStaffException;

/**
 * @Author Noah Reinert Sturis (s154407)
 */
public class ProjectPlanner {
	//As the state and data classes don't perform checks.
	//View of projects and activities.
	private final PPState state;
	private final String user;
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	ProjectPlanner(String user, PPState ppState) {
		this.user = user;
		this.state = ppState;
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public void removeStaff(NormalActivity act, String staff) throws NotProjectLeaderException {
		if(!act.getParent().isLeader(user))
			throw new NotProjectLeaderException();
		act.removeStaff(staff);
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public void registerStaff(NormalActivity act, String staff) throws NotProjectLeaderException {
		if(!act.getParent().isLeader(user))
			throw new NotProjectLeaderException();
		act.registerStaff(staff);
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public void registerAssistance(NormalActivity act, String assistant) throws UserNotStaffException {
		if(!act.isStaff(user))
			throw new UserNotStaffException();
		act.registerAssistance(assistant);
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public Project createProject(){
		return state.createProject();
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public void registerHours(AbstractActivity act, float hours) throws UserNotStaffException {
		if(!act.isStaff(user))
			throw new UserNotStaffException();
		act.registerHours(user, hours);
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public void setLeader(Project project) {
		//Maybe make it so the leader must be null, before a new can be assigned.
		project.setLeader(user);
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
    public NormalActivity createActivity(Project project) throws NotProjectLeaderException {
		if(!project.isLeader(user))
			throw new NotProjectLeaderException();
		return state.createActivity(project);
	}
	
    /**
     * @Author Noah Reinert Sturis (s154407)
     */
	public void removeActivity(NormalActivity activity) throws NotProjectLeaderException {
		if(!activity.getParent().isLeader(user))
			throw new NotProjectLeaderException();
		state.removeActivity(activity);
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public void removeActivity(SpecialActivity activity) {
		state.removeActivity(activity);
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public Stream<Project> getProjectsLeading(String leader) {
		return state.getProjects().stream() //Grab the projects where this dev is leader.
		.filter( p -> p.isLeader(leader) );
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public Stream<AbstractActivity> getActivitiesStaffing(String staff) {
		return state.getActivities().stream() //Grab the activities where this dev is staff.
				.filter( a -> a.isStaff(staff) );
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public String getUser(){
		return this.user;
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public PPState getState(){
		return this.state;
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public Set<AbstractActivity> getAllActivities() {
		return state.getActivities();
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public void generateReport(Project project) throws NotProjectLeaderException {
		if(!project.isLeader(user))
			throw new NotProjectLeaderException();
		project.generateReport();
	}

	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public Set<Project> getAllProjects() {
		return state.getProjects();
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public Set<String> getDevelopers() {
		return state.getDevelopers();
	}
	
}