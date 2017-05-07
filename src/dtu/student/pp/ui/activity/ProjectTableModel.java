package dtu.student.pp.ui.activity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import dtu.student.pp.ProjectPlanner;
import dtu.student.pp.data.activity.AbstractActivity;
import dtu.student.pp.data.activity.NormalActivity;
import dtu.student.pp.data.project.Project;

public class ProjectTableModel extends AbstractTableModel {
	
	//Formatting hour numbers the correct way.
	public static final NumberFormat nf = DecimalFormat.getInstance();
	public final Comparator<String> hoursComparator = new Comparator<String>() {
		@Override
		public int compare(String n0, String n1) {
			try {
				return Float.compare(
						nf.parse(n0).floatValue(),
						nf.parse(n1).floatValue());
			} catch(ParseException e) {
				e.printStackTrace();
			}
			
			return 0;
		}
	};
	
	String[] columnNames = new String[] {"#", "Name", "Start", "End"};//...
    private Project[] projects = new Project[0];
    
	public void setData(Set<Project> projects) {
    	this.projects = projects.toArray(new Project[projects.size()]);
    	this.fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int columnIndex) {
    	return columnNames[columnIndex];
    }
    
    @Override
    public int getRowCount() {
        return projects.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Project project = projects[rowIndex];
        switch (columnIndex) {
            case 0:
                return project.getProjectNumber();
            case 1:
                return project.getName();
            case 2:
            	return project.getStart();
            case 3:
            	return project.getEnd();
            default: return "???";
        }

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
    	switch(columnIndex) {
    	case 0: return Integer.class;
    	case 1: return String.class;
    	case 2:
    	case 3: return Calendar.class; 
    	default:return String.class;
    	}
    }
    
    public Project getProjectAt(int row) {
        return projects[row];
    }
    
    @Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false;
	}
}