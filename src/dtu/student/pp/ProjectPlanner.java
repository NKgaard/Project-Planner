package dtu.student.pp;

import java.util.stream.Stream;

import dtu.student.pp.activity.AbstractActivity;
import dtu.student.pp.project.Project;

public class ProjectPlanner {
	//View of projects and activities.
	private final PPState state;
	private final Developer user;
	
	ProjectPlanner(Developer user, PPState ppState) {
		this.user = user;
		this.state = ppState;
	}	
	
	public Project CreateProject(){
		return(state.createProject());
	}
	
	public Stream<Project> getProjectsLeading(Developer leader) {
		return state.getProjects().stream() //Grab the projects where this dev is leader.
		.filter( p -> p.isUserLeader(leader) );
	}
	
	public Stream<AbstractActivity> getActivitiesStaffing(Developer staff) {
		return state.getActivities().stream() //Grab the activities where this dev is staff.
				.filter( a -> a.isStaff(staff) );
	}
}