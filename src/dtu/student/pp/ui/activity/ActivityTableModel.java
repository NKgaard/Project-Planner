package dtu.student.pp.ui.activity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import dtu.student.pp.ProjectPlanner;
import dtu.student.pp.data.activity.AbstractActivity;
import dtu.student.pp.data.activity.NormalActivity;

public class ActivityTableModel extends AbstractTableModel {
	public final ProjectPlanner planner;
	
	//MB add a project/parent field?
	String[] columnNames = new String[] {"#", "Name", "Start", "End", "Staff.", "Assist.", "Sum", "Hours"};
    private AbstractActivity[] activities = new AbstractActivity[0];
	
    public ActivityTableModel(ProjectPlanner planner) {
		this.planner = planner;
	}

	public void setData(Set<? extends AbstractActivity> activities) {
    	this.activities = activities.toArray(new AbstractActivity[activities.size()]);
    	this.fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int columnIndex) {
    	return columnNames[columnIndex];
    }
    
    @Override
    public int getRowCount() {
        return activities.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AbstractActivity activity = activities[rowIndex];
        switch (columnIndex) {
            case 0:
                return activity.getActivityID();
            case 1:
                return activity.getName();
            case 2:
            	if(activity.getStart()!=null)
            		return activity.getStart().getTime();
            	else
            		return null;
            case 3:
            	if(activity.getEnd()!=null)
            		return activity.getEnd().getTime();
            	else return null;
            case 4:
            	if(activity instanceof NormalActivity) {
            		return ((NormalActivity) activity).getStaff().size();
            	} else return null;
            case 5:
            	if(activity instanceof NormalActivity) {
            		return ((NormalActivity) activity).getAssist().size();
            	} else return null;
            case 6:
            	return activity.getHoursSum();
            case 7:
            	if(activity.isStaff(planner.getUser())) {
            		return activity.getHours(planner.getUser());
            	} else return null;
            default: return "???";
        }

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
    	switch(columnIndex) {
    	case 0: return Integer.class;
    	case 1: return String.class;
    	case 2:
    	case 3: return Date.class; //Automatic sorting
    	case 4: 
    	case 5: return Integer.class;
    	case 6:
    	case 7: return Float.class;
    	default:return String.class;
    	}
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	if(columnIndex==7) {
			getActivityAt(rowIndex).registerHours(planner.getUser(), Math.abs((Float) aValue));
			fireTableCellUpdated(rowIndex, columnIndex);
			fireTableCellUpdated(rowIndex, columnIndex-1); //The sum has also changed
    	}
    }
    
    public AbstractActivity getActivityAt(int row) {
        return activities[row];
    }
    
    @Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return columnIndex == 7 && getActivityAt(rowIndex).isStaff(planner.getUser());
	}
}