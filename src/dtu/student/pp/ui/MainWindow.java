package dtu.student.pp.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dtu.student.pp.ProjectPlanner;
import dtu.student.pp.data.activity.AbstractActivity;
import dtu.student.pp.data.activity.NormalActivity;
import dtu.student.pp.data.project.Project;
import dtu.student.pp.ui.activity.ActivityTable;
import dtu.student.pp.ui.activity.ActivityTableModel;
import dtu.student.pp.ui.activity.ProjectTableModel;

public class MainWindow extends JFrame implements ActionListener {
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
	private final static List<Options> activityDefault =
			Arrays.asList(
					Options.MY_ACTIVITIES,
					Options.VIEW_ACTIVITY,
					Options.PROJECTS,
					Options.STAFF,
					Options.ASSISTANCE);
	private final static List<Options> projectDefault =
			Arrays.asList(
					Options.MY_PROJECTS,
					Options.VIEW_PROJECT,
					Options.ACTIVITIES,
					Options.NEW_PROJECT,
					Options.NEW_ACTIVITY,
					Options.REPORT,
					Options.BECOME_LEADER,
					Options.PROJECT_ACTIVITY);
	Set<Options> options = new HashSet<Options>(10);
	
	JLabel currentMenuLabel = new JLabel("Menu");
	JButton[] menuButtons = new JButton[10];
	JPanel viewPane = new JPanel(new CardLayout());
	ActivityTableModel aTableModel;
	ProjectTableModel pTableModel;
	
	
	ProjectPlanner planner;
	AbstractActivity selectedActivity;
	Project selectedProject;
	UserType userType = UserType.ALL;
	private final static String projectViewName = "Project";
	private final static String activityViewName = "Activity";
	
	public MainWindow(ProjectPlanner planner){
		this.planner = planner;
		this.aTableModel = new ActivityTableModel(planner);
		this.pTableModel = new ProjectTableModel();
		//TODO add selection support to the project list.
		//Use the system look. (Metal L&F is ugly)
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//After setting look and feel construct this object on the invokelater thread.
		
		//SET JFRAME OPTIONS
		setTitle("Project Planner");
		setMinimumSize(new Dimension(500, 370));
		
		//MAIN PANEL HOLDING MENU AND VIEW
		JPanel mainPane = new JPanel(new BorderLayout());
		setContentPane(mainPane);
		JPanel menuHolder = new JPanel(new BorderLayout());
		mainPane.add(menuHolder, BorderLayout.WEST); //Menuholder west
		JPanel menuPane = new JPanel(null);
		menuHolder.add(menuPane, BorderLayout.NORTH); //Menu north-west
		
		mainPane.add(viewPane, BorderLayout.CENTER); //View center
		//Flesh out the menu
		{
			GridBagLayout gbl_menuPane = new GridBagLayout();
			gbl_menuPane.columnWidths = new int[] {112, 0};
			gbl_menuPane.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
			gbl_menuPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_menuPane.rowWeights = new double[]{0.0};
			menuPane.setLayout(gbl_menuPane);
			
			currentMenuLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			currentMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_currentMenu = new GridBagConstraints();
			gbc_currentMenu.insets = new Insets(0, 0, 5, 0);
			gbc_currentMenu.fill = GridBagConstraints.HORIZONTAL;
			gbc_currentMenu.gridx = 0;
			gbc_currentMenu.gridy = 0;
			menuPane.add(currentMenuLabel, gbc_currentMenu);
			
			//Works for up to 10 buttons.
			for(int i=1; i<menuButtons.length+1; i++) {
				String num = Integer.toString(i%10);
				menuButtons[i-1] = new JButton(num);
				menuButtons[i-1].setMnemonic(num.charAt(0)); //Alt + number to press.
				menuButtons[i-1].setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_b = new GridBagConstraints();
				gbc_b.fill = GridBagConstraints.BOTH;
				gbc_b.gridx = 0;
				gbc_b.gridy = i;
				
				menuButtons[i-1].addActionListener(this);
				menuPane.add(menuButtons[i-1], gbc_b);
			}
		}
		//Flesh out the view pane
		JEditorPane introduction = new JEditorPane();
		introduction.setEditable(false);
		introduction.setOpaque(false); //Remove the white background.
		introduction.setBorder(UIManager.getBorder("ScrollPane.border")); //Same border as JScrollPane
		introduction.setContentType("text/html");
		introduction.setText(
				  "<html><body>"
				+ "<center><h1>Welcome to the project planner!</h1></center>"
				+ "</body></html>");
		
		viewPane.add(introduction, "Intro");
		viewPane.add(new JScrollPane(new ActivityTable(this, aTableModel),
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), activityViewName);
		viewPane.add(new JScrollPane(new JTable(pTableModel),
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), projectViewName);
		
		
		options.addAll(Arrays.asList(Options.ACTIVITIES, Options.PROJECTS));
		updateOptions();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void updateOptions() {
		//List<Integer> buttonsNotShown = new ArrayList<Integer>(10);
		boolean[] buttonShown = new boolean[menuButtons.length];
		
		for(Options option:options) {
			int pos = option.position;
			buttonShown[pos] = true;
			
			if(!option.userType.equals(UserType.ALL)) {
				menuButtons[pos].setEnabled(option.userType.equals(userType));
			} else {
				menuButtons[pos].setEnabled(true);
			}
			
			
			menuButtons[pos].setText((pos+1)%10+" "+option.itemText);
			menuButtons[pos].setToolTipText(option.description);
		}
		for(int i=0; i<menuButtons.length; i++)
			menuButtons[i].setVisible(buttonShown[i]);
	}

	public static void main(ProjectPlanner planner){
		MainWindow ctrlwindow = new MainWindow(planner);
		ctrlwindow.addWindowListener(new WindowListener(){
			@Override public void windowActivated(WindowEvent arg0) {}
			@Override
			public void windowClosed(WindowEvent arg0) {
				//Main.exit(state);
			}
			@Override public void windowClosing(WindowEvent arg0) {}
			@Override public void windowDeactivated(WindowEvent arg0) {}
			@Override public void windowDeiconified(WindowEvent arg0) {}
			@Override public void windowIconified(WindowEvent arg0) {}
			@Override public void windowOpened(WindowEvent arg0) {}
		});
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		command = command.substring(
				command.indexOf(" ") + 1, //Start after first space.
				command.length());
		
		if(command.equals(Options.ACTIVITIES.itemText)) {
			((CardLayout)viewPane.getLayout()).show(viewPane, activityViewName);
			options.clear();
			options.addAll(activityDefault);
			userType = UserType.ALL;
			aTableModel.setData(planner.getAllActivities());
			//userType = UserType.ALL;
			updateOptions();
			//activityTable.setData();
			
		} else if (command.equals(Options.MY_ACTIVITIES.itemText)) {
			options.remove(Options.MY_ACTIVITIES);
			options.add(Options.ACTIVITIES);
			aTableModel.setData(
					planner.getActivitiesStaffing(planner.getUser())
					.collect(Collectors.toSet()));
			//userType = UserType.ALL;
			updateOptions();
			
		} else if (command.equals(Options.PROJECTS.itemText)) {
			((CardLayout)viewPane.getLayout()).show(viewPane, projectViewName);
			options.clear();
			options.addAll(projectDefault);
			pTableModel.setData(planner.getAllProjects());
			updateOptions();
		} else if (command.equals(Options.MY_PROJECTS.itemText)) {
			options.remove(Options.MY_PROJECTS);
			options.add(Options.PROJECTS);
			pTableModel.setData(
					planner.getProjectsLeading(planner.getUser())
					.collect(Collectors.toSet()));
			updateOptions();
		} else if (command.equals(Options.VIEW_ACTIVITY.itemText) && selectedActivity != null) {
			//Open window to modify/view activity details.
		} else if (command.equals(Options.VIEW_PROJECT.itemText) && selectedProject != null) {
			//View/modify project
		} else if (command.equals(Options.BECOME_LEADER.itemText) && selectedProject!= null) {
			selectedProject.setLeader(planner.getUser());
			userType = UserType.LEADER;
			updateOptions();
		} else if (command.equals(Options.REPORT.itemText) && selectedProject != null) {
			//make project report of the selected project
		} else if (command.equals(Options.ASSISTANCE.itemText) && selectedActivity != null) {
			
		} else if (command.equals(Options.STAFF.itemText) && selectedActivity != null) {
			
		} else if (command.equals(Options.NEW_ACTIVITY.itemText)) {
			//Dialog box with text input
		} else if (command.equals(Options.NEW_PROJECT.itemText)) {
			
		} else if (command.equals(Options.PROJECT_ACTIVITY.itemText) && selectedProject!=null) {
			options.clear();
			options.addAll(activityDefault);
			aTableModel.setData(planner.getAllActivities()
			.stream().filter(a ->
			a instanceof NormalActivity && 
			((NormalActivity) a).getParent().equals(selectedProject))
			.collect(Collectors.toSet()));
			((CardLayout)viewPane.getLayout()).show(viewPane, activityViewName);
		}
	}
	
	public void setSelectedActivity(AbstractActivity activity) {
		selectedActivity = activity;
		userType = UserType.ALL;
		
		if(activity instanceof NormalActivity) {
			Project parent = ((NormalActivity) activity).getParent();
			if(parent != null && parent.isLeader(planner.getUser())) {
				userType = UserType.LEADER;
			} else if (activity.isStaff(planner.getUser())) {
				userType = UserType.STAFF;
			}
		}
		
		updateOptions();
	}
	
	public void setSelectedProject(Project project) {
		selectedProject = project;
		userType = UserType.ALL;
		if(project.isLeader(planner.getUser()))
			userType = UserType.LEADER;
		updateOptions();
	}

}
