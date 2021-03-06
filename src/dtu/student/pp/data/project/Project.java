package dtu.student.pp.data.project;

import java.awt.*;
import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


import dtu.student.pp.data.activity.NormalActivity;
import dtu.student.pp.data.comparators.Interval;

public class Project extends Interval implements Serializable {
	/**
	 * @Author Sebastian Pr�sius (s164198)
	 */
	private static final long serialVersionUID = 8746318181090942547L;
	private Set<NormalActivity> activities = new HashSet<NormalActivity>();
	private String projectLeader;
	private final ProjectNumber number;
	//private String client = "";
	
	/**
	 * @Author Sebastian Pr�sius (s164198)
	 */
	public Project(ProjectNumber projectNumber) {
		this.number = projectNumber;
	}
	
	/**
	 * @Author Sebastian Pr�sius (s164198)
	 */
	public void addActivity(NormalActivity activity) {
		assert this.equals(activity.getParent()) : //Not generally possible as long as contract is kept.
			"An activity was added to the project, but it isn't it's parent.";
		activities.add(activity);
	}
	
	/**
	 * @Author Sebastian Pr�sius (s164198)
	 */
	public boolean removeActivity(NormalActivity activity) {
		assert this.equals(activity.getParent()) ://Not generally possible as long as contract is kept.
			"An activity was removed from a project, that it wasn't part of.";
		return activities.remove(activity);
	}
	
	/**
	 * @Author Sebastian Pr�sius (s164198)
	 */
	public boolean isLeader(String user) {
		return projectLeader != null && projectLeader.equals(user);
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public void generateReport(){
		String reportName = this.getName()==null ? "Unnamed Project Report.html" : this.getName() +" Report.html";

		try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(reportName), "utf-8"))) {
			writer.println("<html><body>");
			writer.println("<h1>" + this.toString() + ": " + reportName + "</h1>");
			writer.println("Total amount of hours worked on project: "+this.getWorkSum());

			for (NormalActivity act:activities) {
				String actName = act.getName()==null ? "Unnamed Activity" : act.getName();
				writer.println("<h2>"+actName+"</h2>");
				Set<String> staff = act.getStaff();
				if (!staff.isEmpty()) {
                    writer.println("<h3>Staff - hours used:</h3>");
					for (String dev : staff) {
						writer.println("<p>" + dev + " - " + act.getHours(dev) + "</p>");
					}
				}
				Set<String> assist = act.getAssist();
				if (!assist.isEmpty()) {
					writer.println("<h3>Assistants - hours used:</h3>");
					for (String dev : assist) {
						writer.println("<p>" + dev + " - " + act.getHours(dev) + "</p>");
					}
				}
				writer.println("<p>Hours of work used on activity so far: "+act.getHoursSum()+"</p>");
				writer.println("<p>Estimated amount of hours left: "+act.workLeft()+"</p>");
				writer.println("</body></html>");
			}
			writer.flush();
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		try {
			File htmlFile = new File(reportName);
			Desktop.getDesktop().browse(htmlFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Author Sebastian Pr�sius (s164198)
	 */
	@Override
	public int hashCode() {
		return number.hashCode();
	}

	/**
	 * @Author Sebastian Pr�sius (s164198)
	 */
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

	/**
	 * @Author Sebastian Pr�sius (s164198)
	 */
	public ProjectNumber getProjectNumber() {
		return number;
	}

	/**
	 * @Author Sebastian Pr�sius (s164198)
	 */
	public String getLeader() {
		return projectLeader;
	}

	/**
	 * @Author Sebastian Pr�sius (s164198)
	 */
	public Set<NormalActivity> getActivities() {
		return Collections.unmodifiableSet(activities);
	}

	/**
	 * @Author Sebastian Pr�sius (s164198)
	 */
	public void setLeader(String user) {
		this.projectLeader = user;
	}

	/**
	 * @Author Sebastian Pr�sius (s164198)
	 */
	@Override
	public String toString() {
		//return getName();
		return this.number.toString();
		
	}

	/**
	 * @Author Sebastian Pr�sius (s164198)
	 */
	public float getWorkSum() {
		float sum = 0;
		for(NormalActivity activ:activities)
			sum+=activ.getHoursSum();
		return sum;
	}
	
}