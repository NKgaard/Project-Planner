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

public class ActivityTableModel extends AbstractTableModel {
	
	//Formatting hour numbers the correct way.
	public static final NumberFormat nf = DecimalFormat.getInstance();
	public final ProjectPlanner planner;
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
	
	String[] columnNames = new String[] {"#", "Name", "Start", "End", "Staff.", "Assist.", "Hours"};
    private AbstractActivity[] activities = new AbstractActivity[0];
    
	
    public ActivityTableModel(ProjectPlanner planner) {
		this.planner = planner;
	}

	public void setData(Set<AbstractActivity> activities) {
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
            	return activity.getStart();
            case 3:
            	return activity.getEnd();
            case 4:
            	if(activity instanceof NormalActivity) {
            		return ((NormalActivity) activity).getStaff();
            	} else {
            		return null;
            	}
            case 5:
            	if(activity instanceof NormalActivity) {
            		return ((NormalActivity) activity).getAssist();
            	} else {
            		return null;
            	}
            case 6:
            	if(activity.isStaff(planner.getUser())) {
            		return nf.format(activity.getHours(planner.getUser()));
            	} else {
            		return null;
            	}
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
    	case 4: 
    	case 5: return Set.class;
    	case 6: return String.class; //Handle hours as string to preserve formatting
    	default:return String.class;
    	}
    }
    
    //Override this if you want the values to be editable...
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	
    	if(columnIndex==6) {
			try {
				float value = nf.parse(aValue.toString()).floatValue();
				//Do through ppstate
				getActivityAt(rowIndex).registerHours(planner.getUser(), Math.abs(value));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}
    	
    	fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    public AbstractActivity getActivityAt(int row) {
        return activities[row];
    }
    
    @Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return columnIndex == 6 && getActivityAt(rowIndex).isStaff(planner.getUser());
	}
}