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
import dtu.student.pp.data.project.ProjectNumber;

public class ProjectTableModel extends AbstractTableModel {
	String[] columnNames = new String[] {"#", "Name", "Start", "End", "Leader", "Activ.", "Sum"};//...
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
            case 4:
            	return project.getLeader();
            case 5:
            	return project.getActivities().size();
            case 6:
            	return project.getWorkSum();
            default: return "???";
        }

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
    	switch(columnIndex) {
    	case 0: return ProjectNumber.class;
    	case 1: return String.class;
    	case 2:
    	case 3: return Calendar.class;
    	case 4: return String.class;
    	case 5: return Integer.class;
    	case 6: return Float.class;
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