package dtu.student.pp.ui.activity;

import java.util.Date;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import dtu.student.pp.data.project.Project;
import dtu.student.pp.data.project.ProjectNumber;

/**
 * 
 * @author Sebastian Præsius (s164198)
 *
 */
public class ProjectTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1670033627829009566L;
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
            	if(project.getStart()!=null)
            		return project.getStart().getTime();
            	else return null;
            case 3:
            	if(project.getEnd()!=null)
            		return project.getEnd().getTime();
            	else return null;
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
    	case 3: return Date.class;
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