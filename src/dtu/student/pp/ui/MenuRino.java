package dtu.student.pp.ui;

import javax.swing.JTable;


public interface MenuRino {
	public static enum UserType {
		LEADER, STAFF, ALL
	}
	public static enum Options {
		ACTIVITIES("Activities", "View all activities.", 0),
		MY_ACTIVITIES("My activities", "View activities that you staff.", 0),
		ASSISTANCE("Assistance", "Register another developer as an assistant.", 3, UserType.STAFF), //Staff
		STAFF("Staff", "Register a developer as staff.", 4, UserType.LEADER),
		VIEW_ACTIVITY("View activity",
				"View the activity. Only project leaders can modify.", 5),
		EDIT_ACTIVITY("Edit activity",
				"Edit the activity.", 5, UserType.LEADER),
		
		PROJECTS("Projects", "View all projects.", 1),
		MY_PROJECTS("My projects", "View all projects that you lead.", 1),
		VIEW_PROJECT("View project", "Edit the selected project, if you're the leader.", 3),
		NEW_PROJECT("New project", "Create a new project.", 4),
		NEW_ACTIVITY("New activity", "Create a new activity in this project.", 5, UserType.LEADER),
		REPORT("Report", "Generate a project report.", 7, UserType.LEADER),
		BECOME_LEADER("Become leader", "Become the project leader of the selected project.", 8),
		PROJECT_ACTIVITY("Proj. Activities", "View the activities in your project", 9);
		
		final String itemText;
		final String description;
		final int position;
		final UserType userType;
		Options(String itemText, String description, int pos, UserType user) {
			this.itemText = itemText;
			this.description = description;
			this.position = pos;
			this.userType = user;
		}
		Options(String itemText, String description, int pos) {
			this(itemText, description, pos, UserType.ALL);
		}
	}
	
	public JTable getTable();
	public MenuRino.Options[] getOptions();
	public void userTypeChange(UserType t);
	public void buttonPressed(String actionCommand);
}