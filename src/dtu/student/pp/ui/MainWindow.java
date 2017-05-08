package dtu.student.pp.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.*;

import dtu.student.pp.Main;
import dtu.student.pp.ProjectPlanner;
import dtu.student.pp.data.activity.AbstractActivity;
import dtu.student.pp.data.activity.NormalActivity;
import dtu.student.pp.data.comparators.Interval;
import dtu.student.pp.data.project.Project;
import dtu.student.pp.exception.NotProjectLeaderException;
import dtu.student.pp.exception.UserNotStaffException;
import dtu.student.pp.ui.activity.ActivityTable;
import dtu.student.pp.ui.activity.ActivityTableModel;
import dtu.student.pp.ui.activity.ProjectTable;
import dtu.student.pp.ui.activity.ProjectTableModel;

public class MainWindow extends JFrame implements ActionListener {
	private final static String noSelect = "No selection";
	public static enum UserType {
		LEADER, STAFF, ALL
	}
	public static enum Options {
		ACTIVITIES("Activities", "View all activities.", 0),
		MY_ACTIVITIES("My activities", "View activities that you staff.", 0),
		ASSISTANCE("Assistance", "Register another developer as an assistant.", 3, UserType.STAFF), //Staff
		STAFF("Staff", "Register a developer as staff.", 4, UserType.LEADER),
		VIEW_ACTIVITY("View activity",
				"View the activity. Only project leaders can modify.", 2),
		
		PROJECTS("Projects", "View all projects.", 1),
		MY_PROJECTS("My projects", "View all projects that you lead.", 1),
		VIEW_PROJECT("View project", "Edit the selected project, if you're the leader.", 2),
		NEW_PROJECT("New project", "Create a new project.", 3),
		NEW_ACTIVITY("New activity", "Create a new activity in this project.", 4, UserType.LEADER),
		REPORT("Report", "Generate a project report.", 8, UserType.LEADER),
		BECOME_LEADER("Become leader", "Become the project leader of the selected project.", 6),
		PROJECT_ACTIVITY("Project Activ.", "View the activities in your project", 7),
		START("Start", "Go back to the starting screen", 9),
		REG_HOURS("Register hours", "A faster way is to write directly in the table cell to the right.", 5),
		ERROR("Error", "Should not be added as a menu option", -1);
		
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
		public static Options fromString(String itemText) {
			for(Options val:Options.values())
				if(val.itemText.equals(itemText))
					return val;
			return ERROR;
		}
	}
	private final static List<Options> activityDefault =
			Arrays.asList(
					Options.MY_ACTIVITIES,
					Options.VIEW_ACTIVITY,
					Options.PROJECTS,
					Options.STAFF,
					Options.ASSISTANCE,
					Options.START,
					Options.REG_HOURS);
	private final static List<Options> projectDefault =
			Arrays.asList(
					Options.MY_PROJECTS,
					Options.VIEW_PROJECT,
					Options.ACTIVITIES,
					Options.NEW_PROJECT,
					Options.NEW_ACTIVITY,
					Options.REPORT,
					Options.BECOME_LEADER,
					Options.PROJECT_ACTIVITY,
					Options.START);
	Set<Options> options = new HashSet<Options>(10);
	
	JLabel currentMenuLabel = new JLabel("Start");
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
	private final static String helpViewName = "Intro";
	
	public MainWindow(ProjectPlanner planner){
		addWindowListener(new WindowListener(){
			@Override public void windowActivated(WindowEvent arg0) {}
			@Override
			public void windowClosed(WindowEvent arg0) {
				Main.exit(planner.getState());
			}
			@Override public void windowClosing(WindowEvent arg0) {}
			@Override public void windowDeactivated(WindowEvent arg0) {}
			@Override public void windowDeiconified(WindowEvent arg0) {}
			@Override public void windowIconified(WindowEvent arg0) {}
			@Override public void windowOpened(WindowEvent arg0) {}
		});
		
		this.planner = planner;
		this.aTableModel = new ActivityTableModel(planner);
		this.pTableModel = new ProjectTableModel();
		//SET JFRAME OPTIONS
		setTitle("Project Planner");
		setMinimumSize(new Dimension(500, 370));
		Icon image = UIManager.getIcon("FileChooser.detailsViewIcon");
		if (image instanceof ImageIcon) {
			this.setIconImage(((ImageIcon) image).getImage());
		}
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
		
		//Copy this line into another editor to modify
		introduction.setText(
				"<html><body><center><h1>Welcome to the project planner!</h1></center><p>To register your work hours <b>click <i>Activities</i> and edit the \"Hours\" field</b> of the activity you want to register it to.</p><p>Alternatively you can use Alt + the number of the button to navigate.</p><p>To create a new activity or project go to the <i>Projects</i> tab. In this tab you can also set yourself as leader of a project, which allows you to edit it in the <i>Edit Project</i> tab, and also edit it's activities back in the <i>activities</i> tab.</p><p>The date is in the format ww-YYYY, where ww is the week number.</p></body></html>");
		
		viewPane.add(introduction, helpViewName);
		viewPane.add(new JScrollPane(new ActivityTable(this, aTableModel),
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), activityViewName);
		viewPane.add(new JScrollPane(new ProjectTable(this, pTableModel),
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		command = command.substring(
				command.indexOf(" ") + 1, //Start after first space.
				command.length());
		Options selection = Options.fromString(command);
		
		//Misuse of enums? :/
		switch(selection) {
		case ACTIVITIES:
			setSelectedActivity(selectedActivity); //Go back to last selected activity
			aTableModel.setData(planner.getAllActivities());
			((CardLayout)viewPane.getLayout()).show(viewPane, activityViewName);
			options.clear();
			options.addAll(activityDefault);
			currentMenuLabel.setText(command);
			updateOptions();
			break;
		case MY_ACTIVITIES:
			options.remove(Options.MY_ACTIVITIES);
			options.add(Options.ACTIVITIES);
			aTableModel.setData(
					planner.getActivitiesStaffing(planner.getUser())
					.collect(Collectors.toSet()));
			currentMenuLabel.setText(command);
			updateOptions();
			break;
		case PROJECTS:
			((CardLayout)viewPane.getLayout()).show(viewPane, projectViewName);
			options.clear();
			options.addAll(projectDefault);
			pTableModel.setData(planner.getAllProjects());
			currentMenuLabel.setText(command);
			updateOptions();
			break;
		case MY_PROJECTS:
			//Switch out buttons
			options.remove(Options.MY_PROJECTS);
			options.remove(Options.BECOME_LEADER);
			options.add(Options.PROJECTS);
			pTableModel.setData(
					planner.getProjectsLeading(planner.getUser())
					.collect(Collectors.toSet()));
			currentMenuLabel.setText(command);
			updateOptions();
			break;
		case NEW_ACTIVITY:
			//MODAL DIALOG BOX WITH TEXT INPUT
			if(selectedProject!=null) {
				int input1 = JOptionPane.showConfirmDialog(this,
						"Are you sure, you want to create a new activity in project "
						+ selectedProject.getProjectNumber() + "?",
						"New activity", JOptionPane.YES_NO_OPTION);
				if(input1==JOptionPane.YES_OPTION) {
					try {
						NormalActivity newActivity = planner.createActivity(selectedProject);
						JOptionPane.showMessageDialog(this,
								"Successfully created activity "+newActivity.getActivityID()
								+ " in project "+selectedProject.getProjectNumber()+".",
								"New activity", JOptionPane.INFORMATION_MESSAGE);
						menuButtons[0].doClick(); //Go to the newly created activity
					} catch (NotProjectLeaderException e1) {
						JOptionPane.showMessageDialog(this, "You're not the project leader!",
								"Error!", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			} else currentMenuLabel.setText(noSelect); 
			break;
		case NEW_PROJECT:
			//Same
			int input1 = JOptionPane.showConfirmDialog(this,
					"Are you sure, you want to create a new project?",
					"New project", JOptionPane.YES_NO_OPTION);
			if(input1==JOptionPane.YES_OPTION) {
				Project newProject = planner.createProject();
				//Maybe open view/edit screen?
				JOptionPane.showMessageDialog(this,
						"Project number " + newProject.getProjectNumber() + " created.",
						"Success!", JOptionPane.INFORMATION_MESSAGE);
				options.remove(Options.MY_PROJECTS);
				options.add(Options.PROJECTS);
				updateOptions();
				menuButtons[1].doClick(); //Go back to projects view.
			}
			break;
		case ASSISTANCE:
			if(selectedActivity!=null && selectedActivity instanceof NormalActivity) {
				Set<String> developersNotStaff = planner.getDevelopers().stream()
				.filter(dev -> !selectedActivity.isStaff(dev))
				.collect(Collectors.toSet());
				Object input2 = JOptionPane.showInputDialog(this,
						"Select the developer you want to add as assistance",
						"Assistance", JOptionPane.QUESTION_MESSAGE,
						null, developersNotStaff.toArray(), null);
				if(input2!=null) {
					try {
						planner.registerAssistance((NormalActivity)selectedActivity, (String)input2);
						aTableModel.fireTableDataChanged();
					} catch (UserNotStaffException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else currentMenuLabel.setText(noSelect);
			break;
		case STAFF:
			if(selectedActivity!=null && selectedActivity instanceof NormalActivity) {
				//TODO
				ChooseStaff choose = new ChooseStaff(planner, (NormalActivity) selectedActivity);
				for(String dev:choose.selectedDevelopers) {
					try {
						planner.registerStaff((NormalActivity)selectedActivity, dev);
					} catch (NotProjectLeaderException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				aTableModel.fireTableDataChanged();
			} else currentMenuLabel.setText(noSelect); 
			break;
		case VIEW_ACTIVITY:
			if(selectedActivity!=null && selectedActivity instanceof NormalActivity) {
				NormalActivity act = (NormalActivity) selectedActivity;
				
				ActivityView view = new ActivityView(act, act.getParent().isLeader(planner.getUser()));
				if(!view.canceled) {
					act.setName(view.name);
					Calendar cal = new GregorianCalendar();
					if(view.startDate!=null) {
						cal.setTime(view.startDate);
						act.setStart(cal.get(Calendar.WEEK_OF_YEAR), cal.get(Calendar.YEAR));
					}
					if(view.endDate!=null) {
						cal.setTime(view.endDate);
						act.setEnd(cal.get(Calendar.WEEK_OF_YEAR), cal.get(Calendar.YEAR));
					}
					
					for(String staff:view.staffToRemove) {
						try {
							planner.removeStaff(act, staff);
						} catch (NotProjectLeaderException e1) {
							e1.printStackTrace();
						}
					}
				}
				
				aTableModel.fireTableDataChanged();
				
			} else currentMenuLabel.setText(noSelect); 
			break;
		case REG_HOURS:
			if(selectedActivity!=null) {
				String input = "";
				while(input!=null) {
					input = JOptionPane.showInputDialog(
							"Set the hours worked on activity " + selectedActivity.getActivityID(),
							selectedActivity.getHours(planner.getUser()));
					if(input!=null)
						try {
							float hours = Float.parseFloat(input);
							selectedActivity.registerHours(planner.getUser(), Math.abs(hours));
							aTableModel.fireTableDataChanged();
							break;
						} catch(NumberFormatException e1) {
							JOptionPane.showMessageDialog(this,
									"Could not parse number. Example input: 1.5", "Parsing error", JOptionPane.ERROR_MESSAGE);
						}
				}
			} else currentMenuLabel.setText(noSelect); 
			break;
		case VIEW_PROJECT:
			if(selectedProject!=null) {
				new ProjectControl(selectedProject, selectedProject.isLeader(planner.getUser()));
				pTableModel.fireTableDataChanged();
			} else currentMenuLabel.setText(noSelect);
			break;
		case BECOME_LEADER:
			if(selectedProject!=null) {
				selectedProject.setLeader(planner.getUser());
				setSelectedProject(selectedProject); //Update buttons
				pTableModel.fireTableDataChanged();
			} else currentMenuLabel.setText(noSelect);
			break;
		case REPORT:
			if(selectedProject!=null) {
				selectedProject.generateReport();
			} else currentMenuLabel.setText(noSelect);
			break;
		case PROJECT_ACTIVITY:
			if(selectedProject!=null) {
				options.clear();
				options.addAll(activityDefault);
				aTableModel.setData(selectedProject.getActivities());
				((CardLayout)viewPane.getLayout()).show(viewPane, activityViewName);
				currentMenuLabel.setText("Act.:"+selectedProject.getProjectNumber().toString());
			} else currentMenuLabel.setText(noSelect);
			break;
		case START:
			currentMenuLabel.setText("Start");
			((CardLayout)viewPane.getLayout()).show(viewPane, helpViewName);
			options.clear();
			options.addAll(Arrays.asList(Options.ACTIVITIES, Options.PROJECTS));
			updateOptions();
			break;
		default:
			System.err.println("Unhandled menu option!: " + command);//No selection or unhandled
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
